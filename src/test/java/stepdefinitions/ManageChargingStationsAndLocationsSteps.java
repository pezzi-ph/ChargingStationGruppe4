package stepdefinitions;

import businessobjects.ChargingPoint;
import businessobjects.ChargingStation;
import businessobjects.Owner;
import enums.ChargingType;
import enums.Status;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManageChargingStationsAndLocationsSteps {
    private Owner owner;
    private ChargingStation chargingStation;

    @Given("I logged in as the Owner")
    public void iLoggedInAsTheOwner() {
        owner = new Owner();
        boolean loggedIn = owner.login("admin", "password123");
        assertTrue(loggedIn, "Owner should be logged in");
    }

    @When("I create a new Charging Station with location {string}")
    public void iCreateANewChargingStationWithLocation(String location) {
        chargingStation = new ChargingStation(location, owner);
    }

    @And("I add {int} Charging Points of type {string}")
    public void iAddChargingPointsOfType(int size, String chargingType) {
        for(int i = 0; i<size;i++){
            chargingStation.addChargingPoint(new ChargingPoint(ChargingType.valueOf(chargingType)));
        }
    }

    @Then("the Charging Station {string} is added with {int} Charging Points of type {string}")
    public void theChargingStationIsAddedWithChargingPointsOfType(String location, int size, String chargingType) {
        assertEquals(chargingStation.getLocation(), location);
        assertEquals(chargingStation.chargingPoints.size(), size);
        assertTrue(chargingStation.chargingPoints.stream().allMatch(x-> x.getChargingType().equals(ChargingType.valueOf(chargingType))));
    }

    @Given("the Charging Station {string} has {int} Charging Points")
    public void theChargingStationHasChargingPoints(String location, int size) {
        iLoggedInAsTheOwner();
        chargingStation = new ChargingStation(location, owner);
        for(int i = 0; i<size;i++){
            chargingStation.addChargingPoint(new ChargingPoint(ChargingType.AC));
        }
    }

    @When("I assign the Charging Mode {string} to Charging Point {int}")
    public void iAssignTheChargingModeToChargingPoint(String chargingType, int index) {
        chargingStation.chargingPoints.get(index).setChargingType(ChargingType.valueOf(chargingType));
    }

    @Then("Charging Point {int} at {string} has the Charging Mode {string}")
    public void chargingPointAtHasTheChargingMode(int index, String location, String chargingType) {
        assertEquals(chargingStation.getLocation(), location);
        assertEquals(chargingStation.chargingPoints.get(index).getChargingType(), ChargingType.valueOf(chargingType));
    }

    @Given("Charging Point {int} at {string} has the Operational Status {string}")
    public void chargingPointAtHasTheOperationalStatus(int index, String location, String status) {
        iLoggedInAsTheOwner();
        chargingStation = new ChargingStation(location, owner);
        for(int i = 0; i<index+1;i++){
            chargingStation.addChargingPoint(new ChargingPoint(ChargingType.AC));
        }
        chargingStation.chargingPoints.get(index).setStatus(Status.valueOf(status));
    }

    @When("I set the Operational Status of Charging Point {int} to {string}")
    public void iSetTheOperationalStatusOfChargingPointTo(int index, String status) {
        chargingStation.chargingPoints.get(index).setStatus(Status.valueOf(status));

    }

    @Then("Charging Point {int} at {string} has the new Operational Status {string}")
    public void chargingPointAtHasTheNewOperationalStatus(int index, String location, String status) {
        assertEquals(chargingStation.getLocation(), location);
        assertEquals(chargingStation.chargingPoints.get(index).getStatus(), Status.valueOf(status));

    }
}

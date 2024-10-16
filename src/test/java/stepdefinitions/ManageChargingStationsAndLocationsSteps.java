package stepdefinitions;

import businessobjects.ChargingStation;
import businessobjects.Owner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManageChargingStationsAndLocationsSteps {
    private Owner owner;
    private ChargingStation chargingStation;

    @Given("I am logged in as the Owner")
    public void iAmLoggedInAsTheOwner() {
        owner = new Owner();
        owner.setUsername("admin");
        owner.setPassword("password123");
        boolean loggedIn = owner.login();
        assertTrue(loggedIn, "Owner should be logged in");
    }

    @When("I create a new Charging Station with location {string}")
    public void iCreateANewChargingStationWithLocation(String location) {
        chargingStation = new ChargingStation(location);
    }

    @And("I add {int} Charging Points of type {string}")
    public void iAddChargingPointsOfType(int arg0, String arg1) {

    }

    @Then("the Charging Station {string} is added with {int} Charging Points of type {string}")
    public void theChargingStationIsAddedWithChargingPointsOfType(String arg0, int arg1, String arg2) {
    }

    @Given("the Charging Station {string} has {int} Charging Points")
    public void theChargingStationHasChargingPoints(String arg0, int arg1) {
    }

    @When("I assign the Charging Mode {string} to Charging Point {int}")
    public void iAssignTheChargingModeToChargingPoint(String arg0, int arg1) {
    }

    @Then("Charging Point {int} at {string} has the Charging Mode {string}")
    public void chargingPointAtHasTheChargingMode(int arg0, String arg1, String arg2) {
    }

    @Given("Charging Point {int} at {string} has the Operational Status {string}")
    public void chargingPointAtHasTheOperationalStatus(int arg0, String arg1, String arg2) {
    }

    @When("I set the Operational Status of Charging Point {int} to {string}")
    public void iSetTheOperationalStatusOfChargingPointTo(int arg0, String arg1) {
    }
}

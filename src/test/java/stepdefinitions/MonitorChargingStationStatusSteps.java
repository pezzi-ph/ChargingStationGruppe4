package stepdefinitions;

import businessobjects.*;
import enums.ChargingType;
import enums.Status;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MonitorChargingStationStatusSteps {
    private Owner owner;
    private ChargingStation chargingStation;
    private List<Status> statuses;
    private String errorMessage; // Store error message

    @Given("I logged in as the Station Owner.")
    public void iLoggedInAsTheStationOwner() {
        owner = new Owner();
        owner.setUsername("admin");
        owner.setPassword("password123");
        boolean loggedIn = owner.login("admin", "password123");
        assertTrue(loggedIn, "Owner should be logged in");
    }

    @When("I view the Status of all Charging Points")
    public void iViewTheStatusOfAllChargingPoints() {
        // Initialize a list of ChargingPoints
        chargingStation = new ChargingStation("123 Main Street", owner);

        // For testing purposes, we'll create some charging points
        ChargingPoint point1 = new ChargingPoint(ChargingType.AC);
        point1.setStatus(Status.AVAILABLE);

        ChargingPoint point2 = new ChargingPoint(ChargingType.DC);
        point2.setStatus(Status.IN_USE);

        ChargingPoint point3 = new ChargingPoint(ChargingType.AC);
        point3.setStatus(Status.OUT_OF_ORDER);

        // Add points to the list
        chargingStation.addChargingPoint(point1);
        chargingStation.addChargingPoint(point2);
        chargingStation.addChargingPoint(point3);

        // Collect the operational statuses
        statuses = new ArrayList<>();
        for (ChargingPoint point : chargingStation.chargingPoints) {
            statuses.add(point.getStatus());
        }
    }

    @Then("I see the Status \\({string}, {string}, {string}) of each Charging Point at every Location")
    public void iSeeTheStatusOfEachChargingPointAtEveryLocation(String arg0, String arg1, String arg2) {
        assertEquals(chargingStation.chargingPoints.get(0).getStatus(), Status.valueOf(arg0));
        assertEquals(chargingStation.chargingPoints.get(1).getStatus(), Status.valueOf(arg1));
        assertEquals(chargingStation.chargingPoints.get(2).getStatus(), Status.valueOf(arg2));
    }

    private List<ChargingType> chargingTypes;

    @Given("I am viewing the Charging Points")
    public void iAmViewingTheChargingPoints() {
        chargingStation = new ChargingStation("123 Main Street", owner);
        chargingStation.addChargingPoint(new ChargingPoint(ChargingType.AC));
        chargingStation.addChargingPoint(new ChargingPoint(ChargingType.DC));
        chargingStation.addChargingPoint(new ChargingPoint(ChargingType.AC));
        chargingStation.addChargingPoint(new ChargingPoint(ChargingType.AC));
        chargingStation.addChargingPoint(new ChargingPoint(ChargingType.DC));
        chargingStation.addChargingPoint(new ChargingPoint(ChargingType.DC));
        chargingStation.addChargingPoint(new ChargingPoint(ChargingType.AC));
    }

    @When("I check the Charging Type of each Charging Point")
    public void iCheckTheChargingTypeOfEachChargingPoint() {
        chargingTypes = chargingStation.chargingPoints.stream().map(ChargingPoint::getChargingType).collect(Collectors.toList());
    }

    @Then("I see whether each Charging Point has the Charging Type {string} or {string}")
    public void iSeeWhetherEachChargingPointHasTheChargingTypeOr(String arg0, String arg1) {
        try {
            ChargingType type1 = ChargingType.valueOf(arg0);
            ChargingType type2 = ChargingType.valueOf(arg1);
            chargingTypes.forEach(x -> assertTrue(type1 == x || type2 == x));
        } catch (Exception e) {
            Assertions.fail("Invalid Charging Type");
        }
    }

    // Error case for Charging Point Status Unavailable
    @When("I try to view the Status of a Charging Point with no available data")
    public void iTryToViewTheStatusOfAChargingPointWithNoAvailableData() {
        try {
            ChargingPoint pointWithoutStatus = new ChargingPoint(ChargingType.AC);
            pointWithoutStatus.setStatus(null); // No status available

            // Simulating fetching status that doesn't exist
            if (pointWithoutStatus.getStatus() == null) {
                throw new Exception("Charging Point Status Unavailable.");
            }
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("I receive an error message saying {string}")
    public void iReceiveAnErrorMessageSaying(String expectedMessage) {
        assertEquals(expectedMessage, errorMessage);
    }

    @When("I try to view the Status of all Charging Points")
    public void iTryToViewTheStatusOfAllChargingPoints() {
    }
}

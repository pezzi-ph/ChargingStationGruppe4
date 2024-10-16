package stepdefinitions;

import businessobjects.*;
import enums.ChargingType;
import enums.Status;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class MonitorChargingStationStatusSteps {
    private Owner owner;
    private ChargingStation chargingStation;
    private List<Status> statuses;

    @Given("I logged in as the Station Owner.")
    public void iLoggedInAsTheStationOwner() {
        owner = new Owner();
        owner.setUsername("admin");
        owner.setPassword("password123");
        boolean loggedIn = owner.login();
        assertTrue(loggedIn, "Owner should be logged in");
    }

    @When("I view the Status of all Charging Points")
    public void iViewTheStatusOfAllChargingPoints() {
        // Initialize a list of ChargingPoints
        chargingStation = new ChargingStation("123 Main Street",owner);

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
}

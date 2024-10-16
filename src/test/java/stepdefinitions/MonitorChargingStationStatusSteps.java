package stepdefinitions;

import businessobjects.*;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class MonitorChargingStationStatusSteps {
    private Owner owner;
    private ChargingStation chargingStation;
    private List<ChargingStation> chargingStations;
    private List<String> operationalStatuses;
    private List<String> chargingModes;

    @Given("I am logged in as the Owner.")
    public void iAmLoggedInAsTheOwner() {
        owner = new Owner();
        owner.setUsername("admin");
        owner.setPassword("password123");
        boolean loggedIn = owner.login();
        assertTrue(loggedIn, "Owner should be logged in");
    }

    @When("I view the Operational Status of all Charging Stations")
    public void iViewTheOperationalStatusOfAllChargingStations() {
        // Initialize a list of ChargingStations
        chargingStations = new ArrayList<>();

        // For testing purposes, we'll create some charging stations
        ChargingStation station1 = new ChargingStation("CS001", owner);
        station1.setOperationalStatus(OperationalStatus.AVAILABLE);

        ChargingStation station2 = new ChargingStation("CS002", owner);
        station2.setOperationalStatus(OperationalStatus.IN_USE);

        ChargingStation station3 = new ChargingStation("CS003", owner);
        station3.setOperationalStatus(OperationalStatus.OUT_OF_ORDER);

        // Add stations to the list
        chargingStations.add(station1);
        chargingStations.add(station2);
        chargingStations.add(station3);

        // Collect the operational statuses
        operationalStatuses = new ArrayList<>();
        for (ChargingStation station : chargingStations) {
            operationalStatuses.add(station.getOperationalStatus().toString());
        }
    }

    @Then("I see the Operational Status \\({string}, {string}, {string}) of each Charging Station at every Location")
    public void iSeeTheOperationalStatusOfEachChargingStationAtEveryLocation(String arg0, String arg1, String arg2) {
    }
}

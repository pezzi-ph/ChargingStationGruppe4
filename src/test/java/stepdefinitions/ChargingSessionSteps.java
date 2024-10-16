package stepdefinitions;

import io.cucumber.java.en.*;
import businessobjects.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ChargingSessionSteps {

    private Customer customer;
    private PrepaidAccount prepaidAccount;
    private ChargingStation chargingStation;
    private ChargingSession chargingSession;
    private Notification notification;
    private List<ChargingSession> chargingHistory;

    // Step definitions will be implemented below
    @Given("I have sufficient balance in my Prepaid Account")
    public void iHaveSufficientBalanceInMyPrepaidAccount() {
        // Initialize customer and prepaid account
        customer = new Customer("John Doe", "john.doe@example.com", "password123");
        boolean accountCreated = customer.registerAccount();
        assertTrue(accountCreated, "Customer account should be created");

        // Top up the prepaid account
        prepaidAccount = customer.getPrepaidAccount();
        prepaidAccount.topUpBalance(50.0); // Top up with $50

        // Assert that balance is sufficient
        assertTrue(prepaidAccount.getBalance() >= 20.0, "Prepaid account balance should be sufficient");
    }
    @And("I am at a Charging Station with Operational Status {string}")
    public void iAmAtAChargingStationWithOperationalStatus(String status) {
        // Initialize a ChargingStation
        chargingStation = new ChargingStation();
        chargingStation.setStationId("CS123");
        chargingStation.setLocation(new Location("123 Main Street"));
        chargingStation.setOperationalStatus(OperationalStatus.valueOf(status.toUpperCase()));

        // Assert that the status is as expected
        assertEquals(OperationalStatus.valueOf(status.toUpperCase()), chargingStation.getOperationalStatus(), "Charging station should have status " + status);
    }
}





package stepdefinitions;

import enums.ChargingType;
import enums.Status;
import io.cucumber.java.en.*;
import businessobjects.*;

import javax.xml.stream.Location;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ChargingSessionSteps {

    private Owner owner= new Owner();
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
    @And("I am at a Charging Point with Operational Status {string}")
    public void iAmAtAChargingPointWithStatus(String status) {
        owner.login();
        // Initialize a ChargingStation
        chargingStation = new ChargingStation("123 Main Street", owner);
        chargingStation.addChargingPoint(new ChargingPoint(ChargingType.AC));
        chargingStation.chargingPoints.get(0).setStatus(Status.valueOf(status.toUpperCase()));

        // Assert that the status is as expected
        assertEquals(Status.valueOf(status.toUpperCase()), chargingStation.chargingPoints.get(0).getStatus(), "Charging station should have status " + status);
    }
    @When("I start a Charging Session using my Customer ID at the Charging Point")
    public void iStartAChargingSessionUsingMyCustomerIDAtTheChargingPoint() {
        // Start a ChargingSession
        chargingSession = new ChargingSession();
        boolean sessionStarted = chargingSession.startSession(customer, chargingStation, 0);
        assertTrue(sessionStarted, "Charging session should start successfully");
    }
    @Then("the Charging Session begins")
    public void theChargingSessionBegins() {
        // Assert that the charging session is in progress
        assertTrue(chargingSession.isInProgress(), "Charging session should be in progress");
    }
    @And("the Operational Status of the Charging Point changes to {string}")
    public void theStatusOfTheChargingPointChangesTo(String status) {
        // Check the operational status of the charging station
        assertEquals(Status.valueOf(status.toUpperCase()), chargingStation.chargingPoints.get(0).getStatus(), "Charging station status should change to " + status);
    }
    @And("I receive a Notification that the Charging Session has started")
    public void iReceiveANotificationThatTheChargingSessionHasStarted() {
        // Get the latest notification
        notification = customer.getLastNotification();
        assertNotNull(notification, "Customer should receive a notification");
        assertEquals("Charging Session started.", notification.getMessage(), "Notification message should indicate charging session started");
    }
    @Given("I logged into my Customer account")
    public void iLoggedIntoMyCustomerAccount() {
        // Initialize and log in customer
        customer = new Customer("John Doe", "john.doe@example.com", "password123");
        boolean accountCreated = customer.registerAccount();
        assertTrue(accountCreated, "Customer account should be created");
        // Simulate login (assuming login is always successful)
    }
    @When("I view my charging history")
    public void iViewMyChargingHistory() {
        // Assume the customer has some charging sessions
        chargingHistory = customer.getChargingSessions();

        // If no sessions exist, add one for testing purposes
        if (chargingHistory.isEmpty()) {
            ChargingSession session = new ChargingSession();
            session.setCustomerId(customer.getCustomerId());
            session.setChargingStationId("CS123");
            session.setChargingType(ChargingType.AC);
            session.setLocation("123 Main Street");
            session.setUsedEnergy(20.0); // kWh
            session.setCost(6.0); // $0.30 per kWh
            session.setDuration(60); // minutes
            session.setInvoice(new Invoice(session));
            customer.addChargingSession(session);
            chargingHistory = customer.getChargingSessions();
        }
    }
    @Then("I see details of each Charging Session including Location, Charging Mode, energy used \\(in kWh), cost, duration, and Invoice details")
    public void iSeeDetailsOfEachChargingSession() {
        // Retrieve the charging sessions
        assertFalse(chargingHistory.isEmpty(), "Charging sessions should not be empty");

        for (ChargingSession session : chargingHistory) {
            // Verify the details
            assertNotNull(session.getChargingType(), "Charging type should not be null");
            assertTrue(session.getUsedEnergy() > 0, "Used energy should be greater than zero");
            assertTrue(session.getCost() > 0, "Cost should be greater than zero");
            assertTrue(session.getDuration() > 0, "Duration should be greater than zero");
            assertNotNull(session.getInvoice(), "Invoice details should not be null");
        }
    }








}





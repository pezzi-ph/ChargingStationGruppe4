package stepdefinitions;

import businessobjects.Owner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import businessobjects.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BillingAndRevenueManagementSteps {
    private Owner owner;

    @Given("I am logged in as the Station Owner")
    public void iAmLoggedInAsTheStationOwner() {
        owner = new Owner();
        owner.setUsername("admin");
        owner.setPassword("password123");
        boolean loggedIn = owner.login();
        assertTrue(loggedIn, "Owner should be logged in");
    }

    @When("I generate a Revenue Report for Location {string} for the last month")
    public void iGenerateARevenueReportForLocationForTheLastMonth(String arg0) {
    }

    @Then("I see the total billing for each Charging Station at that Location")
    public void iSeeTheTotalBillingForEachChargingStationAtThatLocation() {
        
    }

    @Given("I need to review revenue")
    public void iNeedToReviewRevenue() {
        
    }

    @When("I generate a Revenue Report")
    public void iGenerateARevenueReport() {
        
    }

    @Then("I receive a report summarizing total revenue by Location, Charging Mode, and time period")
    public void iReceiveAReportSummarizingTotalRevenueByLocationChargingModeAndTimePeriod() {
    }
}

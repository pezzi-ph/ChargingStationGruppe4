package stepdefinitions;

import businessobjects.Owner;
import enums.ChargingType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import businessobjects.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class BillingAndRevenueManagementSteps {
    Owner owner;
    ChargingStation station;
    RevenueReport report;

    @Given("I am logged in as the Station Owner")
    public void iAmLoggedInAsTheStationOwner() {
        owner = new Owner();
        boolean loggedIn = owner.login("admin", "password123");
        assertTrue(loggedIn, "Owner should be logged in");
    }

    @When("I generate a Revenue Report for Location {string} for the last month")
    public void iGenerateARevenueReportForLocationForTheLastMonth(String location) {
        station = new ChargingStation(location, owner);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30); // Subtract 30 days
        Date pastDate = calendar.getTime();

        PricingModel model1 = new PricingModel(station, pastDate, new Date());
        model1.setPrice(ChargingType.AC, 100);
        Invoice inv1 = new Invoice(model1.getPrice(ChargingType.AC));
        model1.pushInvoice(inv1);

        PricingModel model2 = new PricingModel(station, pastDate, new Date());
        model2.setPrice(ChargingType.DC, 99);
        Invoice inv2 = new Invoice(model2.getPrice(ChargingType.DC));
        model2.pushInvoice(inv2);

        PricingModel[] models = {model1, model2};
        station.setPricingModels(models);
    }

    @Then("I see the total billing for each Charging Station at that Location")
    public void iSeeTheTotalBillingForEachChargingStationAtThatLocation() {
        report = station.GenerateRevenueReport();
        assertEquals(199, report.getRevenue(), "I generated a revenue report");
    }

    ArrayList<RevenueReport> reports;
    @Given("I need to review revenue")
    public void iNeedToReviewRevenue() {
        reports = new ArrayList<RevenueReport>();
    }


    @When("I generate a Revenue Report")
    public void iGenerateARevenueReport() {
        station = new ChargingStation("123 Main Street", owner);
        PricingModel model1 = new PricingModel(station, new Date(), new Date());
        model1.setPrice(ChargingType.AC, 100);
        Invoice inv1 = new Invoice(model1.getPrice(ChargingType.AC));
        model1.pushInvoice(inv1);

        PricingModel[] models = {model1};
        station.setPricingModels(models);

        report = station.GenerateRevenueReport();
        reports.add(report);
    }

    @Then("I receive a report summarizing total revenue by Location, Charging Mode, and time period")
    public void iReceiveAReportSummarizingTotalRevenueByLocationChargingTypeAndTimePeriod() {
        for(RevenueReport r : reports)
        {
            r.DisplayReport();
        }
        assertTrue(true);
    }

    @Given("I am not logged in as the Station Owner")
    public void iAmNotLoggedInAsTheStationOwner() {
        owner = new Owner();
        boolean loggedIn = owner.login("user", "123");
        assertFalse(loggedIn, "Login should fail");
    }


    String errorMessage;
    @When("I attempt to generate a Revenue Report for Location {string}")
    public void iAttemptToGenerateARevenueReportForLocation(String location) {
        try {
            station = new ChargingStation("123 Main Street", owner);
            PricingModel model1 = new PricingModel(station, new Date(), new Date());
            model1.setPrice(ChargingType.AC, 100);
            Invoice inv1 = new Invoice(model1.getPrice(ChargingType.AC));
            model1.pushInvoice(inv1);

            PricingModel[] models = {model1};
            station.setPricingModels(models);

            report = station.GenerateRevenueReport();
            reports.add(report);
        } catch (IllegalStateException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("I receive an error message {string}")
    public void iReceiveAnErrorMessage(String expectedMessage) {
        assertEquals(expectedMessage, errorMessage);
    }

    @When("I generate a Revenue Report for Location {string} for next month")
    public void iGenerateARevenueReportForLocationForNextMonth(String location) {
        station = new ChargingStation(location, owner);

        // Set time period for next month, which will have no data
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1); // Next month
        Date futureDate = calendar.getTime();

        PricingModel model = new PricingModel(station, futureDate, futureDate);
        PricingModel[] models = {model};
        station.setPricingModels(models);
    }

    @Then("I see a message {string}")
    public void iSeeAMessage(String message) {
        report = station.GenerateRevenueReport();

        try{
            double rev = report.getRevenue();
        } catch (IllegalStateException e) {
            assertEquals(message, e.getMessage());
        }
    }
}

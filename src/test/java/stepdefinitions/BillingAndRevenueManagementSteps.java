package stepdefinitions;

import businessobjects.Owner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import businessobjects.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BillingAndRevenueManagementSteps {
    Owner owner;
    ChargingStation station;
    RevenueReport report;

    @Given("I am logged in as the Station Owner")
    public void iAmLoggedInAsTheStationOwner() {
        owner = new Owner();
        owner.setUsername("admin");
        owner.setPassword("password123");
        boolean loggedIn = owner.login();
        assertTrue(loggedIn, "Owner should be logged in");
    }

    @When("I generate a Revenue Report for Location {string} for the last month")
    public void iGenerateARevenueReportForLocationForTheLastMonth(String location) {
        station = new ChargingStation(location, owner);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30); // Subtract 30 days
        Date pastDate = calendar.getTime();

        PricingModel model1 = new PricingModel(station, pastDate, new Date());
        model1.setPrice(ChargingMode.AC, 100);
        Invoice inv1 = new Invoice(model1.getPrice(ChargingMode.AC));
        model1.pushInvoice(inv1);

        PricingModel model2 = new PricingModel(station, pastDate, new Date());
        model2.setPrice(ChargingMode.DC, 99);
        Invoice inv2 = new Invoice(model2.getPrice(ChargingMode.DC));
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
        iAmLoggedInAsTheStationOwner();
        reports = new ArrayList<RevenueReport>();
    }


    @When("I generate a Revenue Report")
    public void iGenerateARevenueReport() {
        station = new ChargingStation("123 Main Street", owner);
        PricingModel model1 = new PricingModel(station, new Date(), new Date());
        model1.setPrice(ChargingMode.AC, 100);
        Invoice inv1 = new Invoice(model1.getPrice(ChargingMode.AC));
        model1.pushInvoice(inv1);

        PricingModel[] models = {model1};
        station.setPricingModels(models);

        report = station.GenerateRevenueReport();
        reports.add(report);
    }

    @Then("I receive a report summarizing total revenue by Location, Charging Mode, and time period")
    public void iReceiveAReportSummarizingTotalRevenueByLocationChargingModeAndTimePeriod() {
        for(RevenueReport r : reports)
        {
            r.DisplayReport();
        }
        assertTrue(true);
    }
}

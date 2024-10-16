package stepdefinitions;

import enums.ChargingType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import businessobjects.*;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ManagePricesSteps {
    private Owner owner;
    private ChargingStation chargingStation;
    private PricingModel pricingModel;

    @Given("I am logged in as the Owner")
    public void iAmLoggedInAsTheOwner() {
        owner = new Owner();
        owner.setUsername("admin");
        owner.setPassword("password123");
        boolean loggedIn = owner.login();
        assertTrue(loggedIn, "Owner should be logged in");
    }

    @When("I set the price at Charging Station {string} during {string} for Charging Type {string} to ${double} per kWh")
    public void iSetThePriceAtChargingStationDuringForChargingModeTo$PerKWh(String locationName, String timePeriod, String chargingMode, double price) {
        // Initialize the Charging Station and PricingModel
        chargingStation = new ChargingStation(locationName,owner);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30); // Subtract 30 days
        Date pastDate = calendar.getTime();
        pricingModel = new PricingModel(chargingStation, pastDate, new Date());

        // Set the price for the specified charging type
        pricingModel.setPrice(ChargingType.valueOf(chargingMode), price);
    }


    @And("I set the price for Charging Type {string} to ${double} per kWh")
    public void iSetThePriceForChargingModeTo$PerKWh(String chargingMode, double price) {
        // Set the price for the specified charging type
        pricingModel.setPrice(ChargingType.valueOf(chargingMode), price);
    }

    @Then("the PricingModel for Charging Station {string} during {string} is ${double} for {string}")
    public void thePricingModelForChargingStationDuringIsFor(String locationName, String timePeriod, double price, String type) {
        // Verify that the pricing type has been updated
        assertEquals(locationName, pricingModel.getLocation().getLocation());
        assertEquals(timePeriod, pricingModel.getTimePeriod());
        assertEquals(pricingModel.getPrices().get(ChargingType.valueOf(type)),price);
    }

    @Given("I have a PricingModel for Charging Station {string}")
    public void iHaveAPricingModelForChargingStation(String locationName) {
        iAmLoggedInAsTheOwner();
        // Initialize the Charging Station and PricingModel
        chargingStation = new ChargingStation(locationName, owner);
        pricingModel = new PricingModel(chargingStation, "Default Time");
    }

    @When("I assign the price to Charging Type {string}")
    public void iAssignThePriceToChargingMode(String chargingMode) {
        // Assign a price to the charging type
        // For example, set a default price
        pricingModel.setPrice(ChargingType.valueOf(chargingMode), 0.35);
    }

    @And("I assign a different price to Charging Type {string}")
    public void iAssignADifferentPriceToChargingMode(String chargingMode) {
        // Assign a different price to the charging type
        pricingModel.setPrice(ChargingType.valueOf(chargingMode), 0.55);
    }

    @Then("the PricingModel reflects the assigned prices for Charging Types {string} and {string}")
    public void thePricingModelReflectsTheAssignedPricesForChargingModesAnd(String chargingMode1, String chargingMode2) {
        double price1 = pricingModel.getPrice(ChargingType.valueOf(chargingMode1));
        double price2 = pricingModel.getPrice(ChargingType.valueOf(chargingMode2));

        // Verify that the prices are correctly set
        assertEquals(0.35, price1, 0.001, "Price for " + chargingMode1 + " should be 0.35");
        assertEquals(0.55, price2, 0.001, "Price for " + chargingMode2 + " should be 0.55");
    }
}

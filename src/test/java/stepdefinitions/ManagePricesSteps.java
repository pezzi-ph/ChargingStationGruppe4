package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import businessobjects.*;

public class ManagePricesSteps {
    private Owner owner;
    private ChargingStation location;
    private PricingModel pricingModel;

    @Given("I am logged in as the Owner")
    public void iAmLoggedInAsTheOwner() {
        owner = new Owner();
        owner.setUsername("admin");
        owner.setPassword("password123");
        boolean loggedIn = owner.login();
        assertTrue(loggedIn, "Owner should be logged in");
    }

    @When("I set the price at Location {string} during {string} for Charging Mode {string} to ${double} per kWh")
    public void iSetThePriceAtLocationDuringForChargingModeToPerKWh(String locationName, String timePeriod, String chargingMode, double price) {
        // Initialize the Location and PricingModel
        location = new ChargingStation(locationName);
        pricingModel = new PricingModel(location, timePeriod);

        // Set the price for the specified charging mode
        pricingModel.setPrice(ChargingMode.valueOf(chargingMode), price);
    }


    @And("I set the price for Charging Mode {string} to ${double} per kWh")
    public void iSetThePriceForChargingModeTo$PerKWh(String chargingMode, double price) {
        // Set the price for the specified charging mode
        pricingModel.setPrice(ChargingMode.valueOf(chargingMode), price);
    }

    @Then("the PricingModel for Location {string} during {string} is updated")
    public void thePricingModelForLocationDuringIsUpdated(String locationName, String timePeriod) {
        // Verify that the pricing model has been updated
        assertEquals(locationName, pricingModel.getLocation().getLocation());
        assertEquals(timePeriod, pricingModel.getTimePeriod());
        assertNotNull(pricingModel.getPrices());
    }

    @Given("I have a PricingModel for Location {string}")
    public void iHaveAPricingModelForLocation(String locationName) {
        // Initialize the Location and PricingModel
        location = new ChargingStation(locationName);
        pricingModel = new PricingModel(location, "Default Time");
    }

    @When("I assign the price to Charging Mode {string}")
    public void iAssignThePriceToChargingMode(String chargingMode) {
        // Assign a price to the charging mode
        // For example, set a default price
        pricingModel.setPrice(ChargingMode.valueOf(chargingMode), 0.35);
    }

    @And("I assign a different price to Charging Mode {string}")
    public void iAssignADifferentPriceToChargingMode(String chargingMode) {
        // Assign a different price to the charging mode
        pricingModel.setPrice(ChargingMode.valueOf(chargingMode), 0.55);
    }

    @Then("the PricingModel reflects the assigned prices for Charging Modes {string} and {string}")
    public void thePricingModelReflectsTheAssignedPricesForChargingModesAnd(String chargingMode1, String chargingMode2) {
        double price1 = pricingModel.getPrice(ChargingMode.valueOf(chargingMode1));
        double price2 = pricingModel.getPrice(ChargingMode.valueOf(chargingMode2));

        // Verify that the prices are correctly set
        assertEquals(0.35, price1, 0.001, "Price for " + chargingMode1 + " should be 0.35");
        assertEquals(0.55, price2, 0.001, "Price for " + chargingMode2 + " should be 0.55");
    }
}

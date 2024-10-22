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

    @When("I set the price at Charging Station {string} from {string} to {string} for Charging Type {string} to ${double} per kWh")
    public void iSetThePriceAtChargingStationFromToForChargingTypeTo$PerKWh(String locationName, String fromDate, String toDate, String chargingMode, double price) {
        // Initialize the Charging Station and PricingModel
        chargingStation = new ChargingStation(locationName,owner);
        Date from = new Date(Integer.parseInt(fromDate.split("\\.")[2]), Integer.parseInt(fromDate.split("\\.")[1]), Integer.parseInt(fromDate.split("\\.")[0]));
        Date to = new Date(Integer.parseInt(toDate.split("\\.")[2]), Integer.parseInt(toDate.split("\\.")[1]), Integer.parseInt(toDate.split("\\.")[0]));
        pricingModel = new PricingModel(chargingStation, from, to);

        // Set the price for the specified charging type
        pricingModel.setPrice(ChargingType.valueOf(chargingMode), price);
    }


    @And("I set the price for Charging Type {string} to ${double} per kWh")
    public void iSetThePriceForChargingModeTo$PerKWh(String chargingMode, double price) {
        // Set the price for the specified charging type
        pricingModel.setPrice(ChargingType.valueOf(chargingMode), price);
    }

    @Then("the PricingModel for Charging Station {string} from {string} to {string} is ${double} for {string}")
    public void thePricingModelForChargingStationFromToIs$For(String locationName, String fromDate, String toDate, double price, String chargingType) {
        Date from = new Date(Integer.parseInt(fromDate.split("\\.")[2]), Integer.parseInt(fromDate.split("\\.")[1]), Integer.parseInt(fromDate.split("\\.")[0]));
        Date to = new Date(Integer.parseInt(toDate.split("\\.")[2]), Integer.parseInt(toDate.split("\\.")[1]), Integer.parseInt(toDate.split("\\.")[0]));
        // Verify that the pricing model has been updated
        assertEquals(locationName, pricingModel.getLocation().getLocation());
        assertEquals(from, pricingModel.getValidFrom());
        assertEquals(to, pricingModel.getValidTo());
        assertEquals(price, pricingModel.getPrices().get(ChargingType.valueOf(chargingType)));
        assertNotNull(pricingModel.getPrices());
    }

    @Given("I have a PricingModel for Charging Station {string}")
    public void iHaveAPricingModelForChargingStation(String locationName) {
        iAmLoggedInAsTheOwner();
        // Initialize the Charging Station and PricingModel
        chargingStation = new ChargingStation(locationName,owner);
        pricingModel = new PricingModel(chargingStation, new Date(), new Date());
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

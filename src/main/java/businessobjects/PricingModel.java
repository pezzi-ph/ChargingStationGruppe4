package businessobjects;

import enums.ChargingType;

import java.util.HashMap;
import java.util.Map;

public class PricingModel {

    private ChargingStation chargingStation;
    private String timePeriod;
    private Map<ChargingType, Double> prices;

    // Constructor
    public PricingModel(ChargingStation chargingStation, String timePeriod) {
        this.chargingStation = chargingStation;
        this.timePeriod = timePeriod;
        this.prices = new HashMap<>();
    }

    // Set price for a charging mode
    public void setPrice(ChargingType chargingType, double price) {
        prices.put(chargingType, price);
    }

    // Get price for a charging mode
    public double getPrice(ChargingType chargingType) {
        return prices.getOrDefault(chargingType, 0.0);
    }

    // Getters
    public ChargingStation getLocation() {
        return chargingStation;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public Map<ChargingType, Double> getPrices() {
        return prices;
    }
}

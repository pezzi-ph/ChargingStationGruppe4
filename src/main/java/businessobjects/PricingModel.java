package businessobjects;

import java.util.HashMap;
import java.util.Map;

public class PricingModel {

    private ChargingStation chargingStation;
    private String timePeriod;
    private Map<ChargingMode, Double> prices;

    // Constructor
    public PricingModel(ChargingStation chargingStation, String timePeriod) {
        this.chargingStation = chargingStation;
        this.timePeriod = timePeriod;
        this.prices = new HashMap<>();
    }

    // Set price for a charging mode
    public void setPrice(ChargingMode mode, double price) {
        prices.put(mode, price);
    }

    // Get price for a charging mode
    public double getPrice(ChargingMode mode) {
        return prices.getOrDefault(mode, 0.0);
    }

    // Getters
    public ChargingStation getLocation() {
        return chargingStation;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public Map<ChargingMode, Double> getPrices() {
        return prices;
    }
}

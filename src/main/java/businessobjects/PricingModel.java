package businessobjects;

import java.util.*;
import enums.ChargingType;

public class PricingModel {

    private ChargingStation chargingStation;
    private Date validFrom;
    private Date validTo;
    private Map<ChargingType, Double> chargingPrices;
    private Map<ChargingType, Double> parkingPrices;
    private ArrayList<Invoice> invoices;


    // Constructor
    public PricingModel(ChargingStation chargingStation, Date validFrom, Date validTo) {
        this.chargingStation = chargingStation;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.chargingPrices = new HashMap<>();
        this.parkingPrices = new HashMap<>();
        this.invoices = new ArrayList<>();
    }

    // Set price for a charging mode
    public void setPrice(ChargingType chargingType, double price) {
        if (price<0){
            throw new IllegalArgumentException("Price cannot be negative");
        }
        chargingPrices.put(chargingType, price);
    }

    // Get price for a charging mode
    public double getPrice(ChargingType chargingType) {
        return chargingPrices.getOrDefault(chargingType, 0.0) + parkingPrices.getOrDefault(chargingType, 0.0);
    }

    // Getters
    public ChargingStation getLocation() {
        return chargingStation;
    }

    public Map<ChargingType, Double> getChargingPrices() {
        return chargingPrices;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void pushInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

}

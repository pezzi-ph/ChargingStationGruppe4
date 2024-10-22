package businessobjects;

import java.util.*;
import enums.ChargingType;

public class PricingModel {

    private ChargingStation chargingStation;
    private Date validFrom;
    private Date validTo;
    private Map<ChargingType, Double> prices;
    private ArrayList<Invoice> invoices;


    // Constructor
    public PricingModel(ChargingStation chargingStation, Date validFrom, Date validTo) {
        this.chargingStation = chargingStation;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.prices = new HashMap<>();
        this.invoices = new ArrayList<>();
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

    public Map<ChargingType, Double> getPrices() {
        return prices;
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

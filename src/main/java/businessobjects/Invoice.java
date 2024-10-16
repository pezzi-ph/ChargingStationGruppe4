package businessobjects;

import java.util.UUID;

public class Invoice {
    String invoiceId;
    double amount;

    private String invoiceId;
    private ChargingSession chargingSession;
    private double totalAmount;

    public Invoice(ChargingSession session) {
        this.invoiceId = java.util.UUID.randomUUID().toString();
        this.chargingSession = session;
        this.totalAmount = session.getCost();
        // Additional invoice generation logic can be added here
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public double getAmount() {
        return amount;
    }

    public ChargingSession getChargingSession() {
        return chargingSession;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

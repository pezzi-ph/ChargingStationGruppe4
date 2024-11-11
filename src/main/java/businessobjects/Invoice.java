package businessobjects;

import java.util.UUID;

public class Invoice {
    String invoiceId;
    double amount;
    private ChargingSession chargingSession;

    public Invoice(ChargingSession session) {
        this.invoiceId = java.util.UUID.randomUUID().toString();
        this.chargingSession = session;
        this.amount = session.getCost();
    }

    public Invoice(double amount) {
        this.invoiceId = UUID.randomUUID().toString();
        this.amount = amount;
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

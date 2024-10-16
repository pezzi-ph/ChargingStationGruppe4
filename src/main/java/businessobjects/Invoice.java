package businessobjects;

public class Invoice {

    private String invoiceId;
    private ChargingSession chargingSession;
    private double totalAmount;

    public Invoice(ChargingSession session) {
        this.invoiceId = java.util.UUID.randomUUID().toString();
        this.chargingSession = session;
        this.totalAmount = session.getCost();
        // Additional invoice generation logic can be added here
    }

    // Getters

    public String getInvoiceId() {
        return invoiceId;
    }

    public ChargingSession getChargingSession() {
        return chargingSession;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}

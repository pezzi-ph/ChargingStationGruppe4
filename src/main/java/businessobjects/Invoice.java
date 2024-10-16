package businessobjects;

import java.util.UUID;

public class Invoice {
    String invoiceId;
    double amount;

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

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

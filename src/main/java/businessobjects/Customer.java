package businessobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer {
    private String customerId;
    private String name;
    private String email;
    private String password;
    private PrepaidAccount prepaidAccount;
    private List<ChargingSession> chargingSessions;
    private Notification lastNotification;


    public Customer() {
        this.prepaidAccount = new PrepaidAccount();
        this.chargingSessions = new ArrayList<>();
    }

    public Customer(String name, String email, String password) {
        this();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean registerAccount() {
        if (name != null && email != null && password != null) {
            this.customerId = UUID.randomUUID().toString();
            return true;
        } else {
            return false;
        }
    }

    public boolean linkPaymentMethod(String cardType, String cardNumber, String expiryDate, String cvv) throws Exception {
        // Regular expression to match the card number format: "1234-5678-9101-1121"
        String cardNumberPattern = "\\d{4}-\\d{4}-\\d{4}-\\d{4}";

        if (!cardNumber.matches(cardNumberPattern)) {
            throw new Exception("Payment method could not be linked");
        }

        // Simulate linking a payment method if the card number format is valid
        return true;
    }

    public void topUpBalance(double amount) throws Exception {
        prepaidAccount.topUpBalance(amount);
    }

    public void receiveNotification(Notification notification) {
        this.lastNotification = notification;
    }

    public Notification getLastNotification() {
        return lastNotification;
    }

    public void addChargingSession(ChargingSession session) {
        if (chargingSessions == null) {
            chargingSessions = new ArrayList<>();
        }
        this.chargingSessions.add(session);
    }

    public List<ChargingSession> getChargingSessions() {
        if (chargingSessions == null) {
            chargingSessions = new ArrayList<>();
        }
        return chargingSessions;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        // In a real application, passwords should be hashed and stored securely.
        this.password = password;
    }

    public PrepaidAccount getPrepaidAccount() {
        return prepaidAccount;
    }

    public void setPrepaidAccount(PrepaidAccount prepaidAccount) {
        this.prepaidAccount = prepaidAccount;
    }


    @Override
    public String toString() {
        return "\nCustomer{\n" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", customerId='" + customerId + '\'' +
                "\n}";
    }
}

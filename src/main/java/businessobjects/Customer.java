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

    public void linkPaymentMethod(String cardType, String cardNumber, String expiryDate, String cvv) {
        // Simulate linking a payment method.
    }

    public void topUpBalance(double amount) {
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
        // You may add email validation logic here.
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
}

package businessobjects;

import enums.ChargingType;
import enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public class ChargingSession {

    private String sessionId;
    private String customerId;
    private String chargingStationId;
    private ChargingType chargingType;
    private String location;
    private double usedEnergy; // in kWh
    private double cost; // in currency units
    private int duration; // in minutes
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String invoiceId;
    private boolean inProgress;
    private Invoice invoice;

    public ChargingSession() {
        this.sessionId = UUID.randomUUID().toString();
        this.startTime = LocalDateTime.now();
    }

    public boolean startSession(Customer customer, ChargingStation chargingStation, int chargingPointIndex) {
        ChargingPoint chargingPoint = chargingStation.chargingPoints.get(chargingPointIndex);

        // Check if the charging point is OUT_OF_ORDER
        if (chargingPoint.getStatus() == Status.OUT_OF_ORDER) {
            customer.receiveNotification(new Notification("Charging Point is out of order."));
            return false; // The session should not start
        }

        // Check if the charging point is IN_USE
        if (chargingPoint.getStatus() == Status.IN_USE) {
            customer.receiveNotification(new Notification("Charging Point is already in use."));
            return false; // The session should not start
        }

        // Check if the customer has sufficient balance (assuming $20 is the minimum balance required)
        if (customer.getPrepaidAccount().getBalance() < 20.0) {
            customer.receiveNotification(new Notification("Insufficient funds to start the charging session."));
            return false; // The session should not start due to insufficient funds
        }

        // If the charging point is available and the customer has sufficient balance, start the session
        chargingPoint.setStatus(Status.IN_USE); // Set the status to IN_USE
        this.customerId = customer.getCustomerId();
        this.chargingStationId = chargingPoint.getPointId();
        this.chargingType = chargingPoint.getChargingType();
        this.startTime = LocalDateTime.now();
        this.location = chargingStation.getLocation();
        this.inProgress = true;

        // Send a notification to the customer that the session has started
        customer.receiveNotification(new Notification("Charging Session started."));
        return true;
    }

    public void endSession() {
        this.endTime = LocalDateTime.now();
        this.duration = (int) java.time.Duration.between(startTime, endTime).toMinutes();
        // Simulate used energy and cost calculations
        this.usedEnergy = duration * 0.5; // Assuming 0.5 kWh per minute
        this.cost = usedEnergy * 0.30; // Assuming $0.30 per kWh
        this.inProgress = false;

        // Generate invoice
        this.invoice = new Invoice(this);
    }
    // Getters and Setters

    public String getSessionId() {
        return sessionId;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getChargingStationId() {
        return chargingStationId;
    }

    public ChargingType getChargingType() {
        return chargingType;
    }

    public String getLocation() {
        return location;
    }

    public double getUsedEnergy() {
        return usedEnergy;
    }

    public double getCost() {
        return cost;
    }

    public int getDuration() {
        return duration;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setChargingStationId(String chargingStationId) {
        this.chargingStationId = chargingStationId;
    }

    public void setChargingType(ChargingType chargingType) {
        this.chargingType = chargingType;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setUsedEnergy(double usedEnergy) {
        this.usedEnergy = usedEnergy;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

}

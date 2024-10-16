package businessobjects;

import java.time.LocalDateTime;
import java.util.UUID;

public class ChargingSession {

    private String sessionId;
    private String customerId;
    private String chargingStationId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double usedEnergy; // in kWh
    private String invoiceId;

    public ChargingSession() {
        this.sessionId = UUID.randomUUID().toString();
        this.startTime = LocalDateTime.now();
    }

    // Getters and Setters

    public String getSessionId() {
        return sessionId;
    }

}

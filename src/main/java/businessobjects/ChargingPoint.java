package businessobjects;

import enums.ChargingType;
import enums.Status;

import java.util.UUID;

public class ChargingPoint {
    private String pointId;
    private ChargingType chargingType;
    private Status status;
    private String label;

    public ChargingPoint(ChargingType chargingType, String label) {
        this.pointId = UUID.randomUUID().toString();
        this.chargingType = chargingType;
        this.status = Status.AVAILABLE;
        this.label = label;
    }

    public ChargingPoint(ChargingType chargingType) {
        this.pointId = UUID.randomUUID().toString();
        this.chargingType = chargingType;
        this.status = Status.AVAILABLE;
    }

    public String getPointId() {
        return pointId;
    }


    public ChargingType getChargingType() {
        return chargingType;
    }

    public void setChargingType(ChargingType chargingType) {
        this.chargingType = chargingType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\nChargingPoint{\n" +
                "pointId='" + pointId + '\'' +
                ", chargingType=" + chargingType +
                ", status=" + status +
                ", label='" + label + '\'' +
                "\n}";
    }
}

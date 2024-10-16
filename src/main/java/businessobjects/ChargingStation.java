package businessobjects;

import java.util.UUID;

public class ChargingStation {

    private String stationId;
    private String location;
    private ChargingPoint[] chargingPoints;

    // Constructor
    public ChargingStation(String location) {
        location = UUID.randomUUID().toString();
        this.location = location;
    }

    // Getter
    public String getLocation() {
        return location;
    }
}

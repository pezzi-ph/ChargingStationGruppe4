package businessobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import businessobjects.*;

public class ChargingStation {

    private String stationId;
    private String location;
    public ArrayList<ChargingPoint> chargingPoints;

    // Constructor
    public ChargingStation(String location, Owner owner) {
        if(!owner.isLoggedIn()){
            return;
        }
        this.stationId = UUID.randomUUID().toString();
        this.location = location;
        this.chargingPoints = new ArrayList<>();
    }

    // Getter
    public String getLocation() {
        return location;
    }

    public void addChargingPoint(ChargingPoint chargingPoint){
        this.chargingPoints.add(chargingPoint);
    }


}

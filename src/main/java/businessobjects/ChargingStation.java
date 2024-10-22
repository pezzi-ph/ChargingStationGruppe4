package businessobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import businessobjects.*;

public class ChargingStation {

    private String stationId;
    private String location;
    private PricingModel[] pricingModels;
    public ArrayList<ChargingPoint> chargingPoints = new ArrayList<>();

    // Constructor
    public ChargingStation(String location, Owner owner) {
        if(!owner.isLoggedIn()){
            return;
        }
        this.stationId = UUID.randomUUID().toString();
        this.location = location;
    }

    // Getter
    public String getLocation() {
        return location;
    }

    public void addChargingPoint(ChargingPoint chargingPoint){
        this.chargingPoints.add(chargingPoint);
    }

    public void setPricingModels(PricingModel[] pricingModels) {
        this.pricingModels = pricingModels;
    }

    public RevenueReport GenerateRevenueReport() {
        RevenueReport report = new RevenueReport();

        double revenue = 0;
        for(PricingModel model : pricingModels)
        {
            double wholeAmount = 0;
            for(Invoice inv : model.getInvoices())
            {
                wholeAmount += inv.amount;
            }
            revenue += wholeAmount;
        }

        report.setRevenue(revenue);
        return report;
    }
}

import businessobjects.*;
import enums.ChargingType;

import java.util.ArrayList;
import java.util.Date;

public class Main {
    ArrayList<Customer> Customers;
    ArrayList<Owner> owners;
    ArrayList<ChargingStation> stations;
    public static void main(String[] args) {
        ArrayList<Customer> Customers = new ArrayList<Customer>(){{
            add(new Customer("Anna A","Anna@gmail.com", "pass1"));
            add(new Customer("Ben B","Ben@gmail.com", "123"));
            add(new Customer("Chris C","Chris@gmail.com", "2012"));
            add(new Customer("Dave D","Dave@gmail.com", "asdf"));
            add(new Customer("Elfi E","Elfi@gmail.com", "0000"));
        }};
        Customers.get(0).registerAccount();
        try {
            Customers.get(0).topUpBalance(200);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ArrayList<Owner> owners = new ArrayList<Owner>(){{
            add(new Owner("Franz F", "passwort1234"));
            add(new Owner("Günther G", "123456789"));
        }};

        owners.get(0).login("Franz F", "passwort1234");
        owners.get(1).login("Günther G", "123456789");

        ArrayList<ChargingStation> stations = new ArrayList<ChargingStation>(){{
            add(new ChargingStation("Wienerberg", owners.get(0)));
            add(new ChargingStation("Wien Hbf", owners.get(1)));
            add(new ChargingStation("Bad Vöslau", owners.get(0)));
        }};

        stations.get(0).chargingPoints.add(new ChargingPoint(ChargingType.AC, "AC Nr 1"));
        stations.get(0).chargingPoints.add(new ChargingPoint(ChargingType.AC, "AC Nr 2"));
        stations.get(0).chargingPoints.add(new ChargingPoint(ChargingType.DC, "DC Nr 1"));
        stations.get(1).chargingPoints.add(new ChargingPoint(ChargingType.DC, "DC Nr 1"));
        stations.get(1).chargingPoints.add(new ChargingPoint(ChargingType.DC, "DC Nr 2"));
        stations.get(1).chargingPoints.add(new ChargingPoint(ChargingType.DC, "DC Nr 3"));
        stations.get(2).chargingPoints.add(new ChargingPoint(ChargingType.DC, "DC Nr 1"));
        stations.get(2).chargingPoints.add(new ChargingPoint(ChargingType.AC, "AC Nr 1"));
        stations.get(2).chargingPoints.add(new ChargingPoint(ChargingType.AC, "AC Nr 2"));
        stations.get(2).chargingPoints.add(new ChargingPoint(ChargingType.AC, "AC Nr 3"));
        stations.get(2).chargingPoints.add(new ChargingPoint(ChargingType.AC, "AC Nr 4"));


        stations.get(0).setPricingModels(new PricingModel[]{new PricingModel(new Date(System.currentTimeMillis()-24*60*60*1000), new Date(System.currentTimeMillis()+24*60*60*1000)) });
        stations.get(0).pricingModels[0].parkingPrices.put(ChargingType.AC, 3.0);
        stations.get(0).pricingModels[0].chargingPrices.put(ChargingType.AC, 0.2);

        ViewStationNetwork(Customers,owners,stations);

        ChargingSession session = new ChargingSession();
        session.startSession(Customers.get(0), stations.get(0), 0);
        session.endSession(800);

        System.out.println(session.getInvoice().toString());


    }


    public static void ViewStationNetwork(ArrayList<Customer> customers, ArrayList<Owner> owners, ArrayList<ChargingStation> stations){
        System.out.println(customers.toString());
        System.out.println(owners.toString());
        System.out.println(stations.toString());
    }

}

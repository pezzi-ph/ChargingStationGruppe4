import businessobjects.ChargingPoint;
import businessobjects.ChargingStation;
import businessobjects.Customer;
import businessobjects.Owner;
import enums.ChargingType;

import java.util.ArrayList;

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


        System.out.println(Customers.toString());
        System.out.println(owners.toString());
        System.out.println(stations.toString());
    }


}

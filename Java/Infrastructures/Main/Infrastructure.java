package FX_W20PKG;

import CargoAirport.CargoAirport;
import CommercialAirport.CommercialAirport;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Infrastructure {

    public static void main(String[] args) {
        System.out.println("Starting task 3A");
        // Creating all objects to be manipulated, not creating any Structure type objects since they all are structures
        // and it wouldn't make sense.
        Building building1 = new Building(1945, 200000, 1000, "concrete");
        Building building2 = new Building(1950, 250000, 1250, "concrete");
        Building building3 = new Building(1935, 300000, 1500, "wood");
        Building building4 = new Building(1980, 600000, 3000, "concrete");
        Building building5 = new Building(1960, 350000, 1850, "concrete");
        Building building6 = new Building(1982, 9000000, 3000, "glass");
        Bridge bridge1 = new Bridge(1990, 600000, 900, 100);
        Bridge bridge2 = new Bridge(1991, 650000, 600, 300);
        Bridge bridge3 = new Bridge(1992, 680000, 800, 250);
        Bridge bridge4 = new Bridge(1995, 900000, 1200, 400);
        Bridge bridge5 = new Bridge(2000, 600000, 2200, 600);
        Bridge bridge6 = new Bridge(2005, 600000, 3000, 500);
        Overpass overpass1 = new Overpass(2001, 700000, 300, 100, 400);
        Overpass overpass2 = new Overpass(2008, 800000, 300, 200, 400);
        Overpass overpass3 = new Overpass(2010, 1200000, 3500, 400, 400);
        Overpass overpass4 = new Overpass(2015, 900000, 1200, 350, 400);
        Overpass overpass5 = new Overpass(2020, 800000, 600, 500, 400);
        Overpass overpass6 = new Overpass(2019, 2500000, 4000, 300, 400);
        HighRise highRise1 = new HighRise(1999, 800000, 1250, "concrete", 150);
        HighRise highRise2 = new HighRise(2003, 900000, 1250, "steel", 200);
        HighRise highRise3 = new HighRise(2005, 1000000, 1250, "metal", 250);
        HighRise highRise4 = new HighRise(2009, 1100000, 1250, "concrete", 350);
        HighRise highRise5 = new HighRise(2012, 1200000, 1250, "steel", 400);
        HighRise highRise6 = new HighRise(2017, 1300000, 1250, "concrete", 450);
        ResidentialBuilding residentialBuilding1 = new ResidentialBuilding(1950, 380000, 1000, "concrete", 4);
        ResidentialBuilding residentialBuilding2 = new ResidentialBuilding(1945, 300000, 1200, "steel", 4);
        ResidentialBuilding residentialBuilding3 = new ResidentialBuilding(1984, 280000, 1000, "concrete", 5);
        ResidentialBuilding residentialBuilding4 = new ResidentialBuilding(1992, 300000, 1100, "steel", 6);
        ResidentialBuilding residentialBuilding5 = new ResidentialBuilding(1971, 250000, 1000, "cement", 4);
        ResidentialBuilding residentialBuilding6 = new ResidentialBuilding(1962, 410000, 2400, "concrete", 8);
        House house1 = new House(1945, 200000, 1000, "concrete", 4, 3.5, 1);
        House house2 = new House(1969, 280000, 1500, "steel", 4, 3, 1);
        House house3 = new House(1982, 265000, 2000, "concrete", 5, 4.5, 2);
        House house4 = new House(1995, 400000, 2000, "steel", 5, 4, 2);
        House house5 = new House(2012, 500000, 2250, "concrete", 5, 5, 2);
        House house6 = new House(2009, 750000, 2500, "metal", 6, 7, 3);
        CondoBuilding condoBuilding1 = new CondoBuilding(2000, 800000, 1000, "bricks", 8, 4);
        CondoBuilding condoBuilding2 = new CondoBuilding(2000, 1200000, 1250, "cement", 8, 4);
        CondoBuilding condoBuilding3 = new CondoBuilding(2000, 1800000, 1200, "bricks", 18, 6);
        CondoBuilding condoBuilding4 = new CondoBuilding(2000, 2000000, 1180, "concrete", 12, 4);
        CondoBuilding condoBuilding5 = new CondoBuilding(2000, 1870000, 1350, "bricks", 10, 5);
        CondoBuilding condoBuilding6 = new CondoBuilding(2018, 3000000, 1500, "glass", 20, 10);
        Airport airport1 = new Airport(1937, 2000000, 4, "YYZ");
        Airport airport2 = new Airport(1960, 3000000, 6, "YUL");
        Airport airport3 = new Airport(1975, 5000000, 4, "LAX");
        Airport airport4 = new Airport(1980, 6000000, 3, "DXB");
        Airport airport5 = new Airport(1950, 4000000, 5, "HND");
        Airport airport6 = new Airport(1965, 1800000, 6, "BZL");
        CommercialAirport commercialAirport1 = new CommercialAirport(1950, 4000000, 5, "ORD", 4);
        CommercialAirport commercialAirport2 = new CommercialAirport(1971, 5000000, 3, "RCY", 5);
        CommercialAirport commercialAirport3 = new CommercialAirport(1981, 3000000, 2, "LEA", 3);
        CommercialAirport commercialAirport4 = new CommercialAirport(1965, 4000000.18, 4, "LCY", 6);
        CommercialAirport commercialAirport5 = new CommercialAirport(1950, 4000005.80, 5, "SUX", 7);
        CommercialAirport commercialAirport6 = new CommercialAirport(1972, 4001000, 3, "LZI", 10);
        CargoAirport cargoAirport1 = new CargoAirport(1980, 9000000, 3, "AAA", 100000000);
        CargoAirport cargoAirport2 = new CargoAirport(1990, 12000000, 3, "BBB", 135000000);
        CargoAirport cargoAirport3 = new CargoAirport(1982, 9000000, 3, "CCC", 120000000);
        CargoAirport cargoAirport4 = new CargoAirport(1965, 3000000, 6, "YUL", 110000000);
        CargoAirport cargoAirport5 = new CargoAirport(1980, 6000000, 3, "DXB", 150000000);
        CargoAirport cargoAirport6 = new CargoAirport(1950, 4000000, 5, "HND", 170000000);
        System.out.println("*************End of task 3A*************");
        System.out.println("*************Start of Task 3B*************");
        // put them all in an array not following eachother
        Structure[] strArr = {building1, bridge1, overpass1, highRise1, residentialBuilding1, house1, condoBuilding1, airport1, commercialAirport1, cargoAirport1, cargoAirport2, commercialAirport2, airport2,
                condoBuilding2, house2, residentialBuilding2, highRise2, overpass2, bridge2, building2, building3, bridge3, overpass3, highRise3, residentialBuilding3, house3, condoBuilding3, airport3, commercialAirport3,
                cargoAirport3, cargoAirport4, commercialAirport4, airport4, condoBuilding4, house4, residentialBuilding4, highRise4, overpass4, bridge4, building4, building5, building6, bridge5, bridge6, overpass5,
                overpass6, highRise5, highRise6, residentialBuilding5, residentialBuilding6, house5, house6, condoBuilding5, condoBuilding6, airport5, airport6, commercialAirport5, commercialAirport6, cargoAirport5, cargoAirport6};
        System.out.println("Structure array of size " + strArr.length + " has been created");
        System.out.println("*************End of Task 3B*************");
        System.out.println("*************Start of Task 3C*************");
        int i = findTallestHighRise(strArr);
        if (i != -1) {
            System.out.println("Tallest high rise was found at index: " + i + " here is the info of that object");
            System.out.println(strArr[i]);
        } else System.out.println("No high rise objects were found in the array");
        System.out.println("*************End of Task 3.C*************");
        System.out.println("*************Start of Task 3.D*************");
        showBuildingInfo(strArr);
        System.out.println("*************End of Task 3.D*************");
        System.out.println("*************Start of Task 3.E*************");
        displayAllObjects(strArr, strArr.length - 1);
        System.out.println("*************End of Task 3.E*************");
        System.out.println("*************Start of Task 3.F*************");
        Structure[] copiedArray = copyStructures(strArr);
        displayAllObjects(copiedArray, copiedArray.length - 1);
        System.out.println("*************End of Task 3.F*************");
        System.out.println("*************Start of Task 4*************");
        ArrayList<Structure> strArrLst = new ArrayList<Structure>(30);
        Collections.addAll(strArrLst, strArr);    // could also use Arrays.aslist here
        strArrLst.remove(10);
        strArrLst.remove(12);
        strArrLst.remove(25);
        strArrLst.remove(29);
        strArrLst.remove(34);
        if (strArrLst.contains(house1))
            System.out.println("House 1 was found at index " + strArrLst.indexOf(house1));
        System.out.println("is cargoAirport2 still in the list? " + strArrLst.contains(cargoAirport2)); // removed index 10
        System.out.println("*************End of Task 4*************");
        System.out.println("*************Start of Task 5*************");
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream("AirportCodes.txt"))) {
            findExistingAirportCodes(printWriter, strArr);
        } catch (FileNotFoundException e) {
            System.out.println("Could not create AirportCodes.txt");
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("AirportCodes.txt"))) {
            displayAirportCodes(bufferedReader);

        } catch (FileNotFoundException e) {
            System.out.println("Could not open AirportCodes.txt");

        } catch (IOException e) {
            System.out.println("Error occurred while inputting file.");
        }
        System.out.println("*************End of Task 5*************");
        System.out.println("*************Start of Task 6*************");

        StructureList list = new StructureList();
        list.addToStart(airport2);
        list.addToStart(house3);
        list.addAtEnd(highRise6);
        list.addToStart(bridge2);
        list.showListContents();
        StructureList list2 = new StructureList();
        list2.addToStart(highRise1);
        list2.addToStart(overpass1);
        list2.addToStart(bridge2);
        list.append(list2);
        list.addAtEnd(condoBuilding1);
        System.out.println("APPENDED LIST");
        list.showListContents();
        System.out.println("EMPTY LIST");
        list2.showListContents();
        StructureList newList = (StructureList) list.clone();
        System.out.println("CLONED LIST");
        newList.addAtEnd(residentialBuilding1);
        newList.showListContents();
        System.out.println("*************End of Task 6*************");


    }


    public static void findExistingAirportCodes(PrintWriter pw, Structure[] arr) {
        for (Structure structure : arr) {
            if (structure instanceof Airport) {
                String code = ((Airport) structure).getCode();
                pw.println(code);
            }
        }
        pw.close();
    }

    public static void displayAirportCodes(BufferedReader br) throws IOException {
        int i;
        while ((i = br.read()) != -1) {
            System.out.print((char) i);
        }
        br.close();
    }

    public static int findTallestHighRise(Structure[] arr) {
        double max = 0.0;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            Class s = arr[i].getClass();
            if (s.getSimpleName().equals("HighRise")) {
                HighRise h = (HighRise) arr[i];
                if (h.getHighRise() > max) {
                    max = h.getHighRise();
                    index = i;
                }
            }
        }
        if (max == 0.0)
            return -1;
        return index;
    }


    public static void showBuildingInfo(Structure[] arr) {
        for (Structure structure : arr) {
            if (structure instanceof Building)
                System.out.println(structure + "\n");
        }
    }


    public static void displayAllObjects(Structure[] arr, int i) {
        if (i >= 0) {
            System.out.println(arr[i] + "\n");
            displayAllObjects(arr, i - 1);
        }
    }

    public static Structure[] copyStructures(Structure[] arr) {
        Structure[] copy = arr.clone();
        return copy;
    }
}

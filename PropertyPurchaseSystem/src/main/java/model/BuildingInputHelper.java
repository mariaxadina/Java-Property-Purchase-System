package model;

import java.util.*;

public class BuildingInputHelper {
    public static StudioData readStudioData(Scanner scanner) {
        System.out.print("Enter seller ID (int): ");
        int sellerId = Integer.parseInt(scanner.nextLine());

        boolean soldStatus = false;

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter surface area (double): ");
        double surfaceArea = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter price (double): ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter floor number (int): ");
        int floor = Integer.parseInt(scanner.nextLine());

        System.out.println("Has balcony (true/false): ");
        boolean balcony = Boolean.parseBoolean(scanner.nextLine());

        return new StudioData(sellerId, soldStatus, address, surfaceArea, price, floor, balcony);
    }
    public static class StudioData {
        public final int sellerId;
        public final boolean soldStatus;
        public final String address;
        public final double surfaceArea;
        public final double price;
        public final int floorNumber;
        public final boolean hasBalcony;

        public StudioData(int sellerId, boolean soldStatus, String address, double surfaceArea, double price,
                          int floorNumber, boolean hasBalcony) {
            this.sellerId = sellerId;
            this.soldStatus = soldStatus;
            this.address = address;
            this.surfaceArea = surfaceArea;
            this.price = price;
            this.floorNumber = floorNumber;
            this.hasBalcony = hasBalcony;
        }
    }
    public static HouseData readHouseData(Scanner scanner) {
        System.out.print("Enter seller ID (int): ");
        int sellerId = Integer.parseInt(scanner.nextLine());

        boolean soldStatus = false;

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter surface area (double): ");
        double surfaceArea = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter price (double): ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter number of floors (int): ");
        int numberOfFloors = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter number of rooms (int): ");
        int numberOfRooms = Integer.parseInt(scanner.nextLine());

        System.out.print("Has garden? (true/false): ");
        boolean hasGarden = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Has garage? (true/false): ");
        boolean hasGarage = Boolean.parseBoolean(scanner.nextLine());

        return new HouseData(sellerId, soldStatus, address, surfaceArea, price,
                numberOfFloors, numberOfRooms, hasGarden, hasGarage);
    }
    public static class HouseData {
        public final int sellerId;
        public final boolean soldStatus;
        public final String address;
        public final double surfaceArea;
        public final double price;
        public int numberOfFloors;
        public int numberOfRooms;
        public boolean hasGarden;
        public boolean hasGarage;


        public HouseData(int sellerId, boolean soldStatus, String address, double surfaceArea, double price,
                          int numberOfFloors, int numberOfRooms, boolean hasGarden, boolean hasGarage) {
            this.sellerId = sellerId;
            this.soldStatus = soldStatus;
            this.address = address;
            this.surfaceArea = surfaceArea;
            this.price = price;
            this.numberOfFloors = numberOfFloors;
            this.numberOfRooms = numberOfRooms;
            this.hasGarden = hasGarden;
            this.hasGarage = hasGarage;
        }
    }

    public static void printBuildingDetails(List<Building> buildings) {
        for (Building b : buildings) {
            System.out.printf("ID: %d | Seller: %d | Type: %s | Address: %s | Surface: %.2f | Price: %.2f | Sold: %b",
                    b.getBuildingID(),b.getSellerId(), b.getType().name(), b.getAddress(), b.getSurfaceArea(), b.getPrice(), b.getSoldStatus());
            if (b instanceof Apartment a) {
                System.out.printf(" | Floor: %d | Balcony: %b | Rooms: %d",
                        a.getFloorNumber(), a.getHasBalcony(), a.getNumberOfRooms());
            } else if (b instanceof Studio s) {
                System.out.printf(" | Floor: %d | Balcony: %b", s.getFloorNumber(), s.getHasBalcony());
            } else if (b instanceof Villa v) {
                System.out.printf(" | Rooms: %d | Floors: %d | Garden: %b | Garage: %b | Pool: %b | Terrace: %b | View: %s",
                        v.getNumberOfFloors(), v.getNumberOfRooms(), v.hasGarden(), v.hasGarage(),
                        v.getHasPool(), v.getHasTerrace(), v.getViewType());
            }else if (b instanceof House h) {
                System.out.printf(" | Rooms: %d | Floors: %d | Garden: %b | Garage: %b",
                        h.getNumberOfFloors(), h.getNumberOfRooms(), h.hasGarden(), h.hasGarage());
            }

            System.out.println();
        }
    }

    public static Map<Integer, List<Building>> groupBuildingsBySeller(List<Building> buildings) {
        Map<Integer, List<Building>> buildingsBySeller = new HashMap<>();
        for (Building b : buildings) {
            buildingsBySeller.computeIfAbsent(b.getSellerId(),k -> new ArrayList<>()).add(b);
        }
        return buildingsBySeller;
    }


}

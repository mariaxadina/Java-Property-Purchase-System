package org.example;

import config.ConnectionProvider;
import model.*;
import repository.*;
import service.BuildingService;
import service.SellerService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;

import java.util.*;

import static model.BuildingInputHelper.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Connection connection = ConnectionProvider.getConnection()) {
            while (true) {
                System.out.println("Property Purchase System\n");
                System.out.println("1. Display all properties");
                System.out.println("2. Display available properties");
                System.out.println("3. Display sold properties");
                System.out.println("4. Add Property to the database");
                System.out.println("5. Add new seller into the system");
                System.out.println("6. Show properties from seller");
                System.out.println("7. Buy property");
                System.out.println("8. Exit");
                System.out.println("Type desired option: ");
                String key = scanner.nextLine();

                BuildingRepository buildingRepository = BuildingRepositoryImpl.getInstance(connection);
                BuildingService buildingService = new BuildingService(buildingRepository);

                SellerRepository sellerRepository = SellerRepositoryImpl.getInstance(connection);
                SellerService sellerService = new SellerService(sellerRepository);

                switch (key) {
                    case "1":
                        List<Building> allBuildings = buildingService.getAllProperties();
                        System.out.println("Properties:");
                            printBuildingDetails(allBuildings);

                        break;
                    case "2":
                        List<Building> availableBuildings = buildingService.getAvailableProperties();
                        System.out.println("Available Properties:");
                        printBuildingDetails(availableBuildings);

                        break;
                    case "3":
                        List<Building> unavailableBuildings = buildingService.getUnavailableProperties();
                        System.out.println("Available Properties:");
                        printBuildingDetails(unavailableBuildings);

                        break;
                    case "4":
                        System.out.println("What property would you like to add?");
                        System.out.println("1. STUDIO");
                        System.out.println("2. APARTMENT");
                        System.out.println("3. HOUSE");
                        System.out.println("4. VILLA");
                        System.out.println("Type desired option: ");
                        String option = scanner.nextLine();

                        switch (option) {
                            case "1":
                                Studio studio = readStudioFromKeyboard(scanner);
                                buildingService.addBuilding(studio);
                                AuditService.getInstance().logAction("INSERT_STUDIO");
                                System.out.println("Studio added successfully!");
                                break;
                            case "2":
                                Apartment apartment = readApartmentFromKeyboard(scanner);
                                buildingService.addBuilding(apartment);
                                AuditService.getInstance().logAction("INSERT_APARTMENT");
                                System.out.println("Apartment added successfully!");
                                break;
                            case "3":
                                House house = readHouseFromKeyboard(scanner);
                                buildingService.addBuilding(house);
                                AuditService.getInstance().logAction("INSERT_HOUSE");
                                System.out.println("Apartment added successfully!");
                                break;
                            case "4":
                                Villa villa = readVillaFromKeyboard(scanner);
                                buildingService.addBuilding(villa);
                                AuditService.getInstance().logAction("INSERT_VILLA");
                                System.out.println("Villa added successfully!");
                                break;
                            default:
                                System.out.println("Invalid option!");
                        }
                        break;
                    case "5":
                        Seller seller = readSellerFromKeyboard(scanner);
                        sellerService.addSeller(seller);
                        AuditService.getInstance().logAction("INSERT_SELLER");
                        break;
                    case "6":
                        List<Building> all_buildings = buildingService.getAllProperties();
                        Map<Integer, List<Building>> buildings_by_id = groupBuildingsBySeller(all_buildings);

                        System.out.println("Introduce seller ID: ");
                        int sellerId = scanner.nextInt();
                        List<Building> buildingsFromSeller = buildings_by_id.get(sellerId);
                        if (buildingsFromSeller == null) {
                            System.out.println("Seller not found!");
                        } else {
                            printBuildingDetails(buildingsFromSeller);
                        }
                        break;
                    case "7":
                        System.out.println("Buy");

                        break;
                    case "8":
                        System.out.println("Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private static Seller readSellerFromKeyboard(Scanner scanner) {
        System.out.print("Enter seller ID (int): ");
        int idSeller = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter seller name: ");
        String name = scanner.nextLine();

        System.out.println("Enter seller phone number: ");
        String phoneNumber = scanner.nextLine();

        return new Seller(idSeller, name, phoneNumber);
    }

    private static Studio readStudioFromKeyboard(Scanner scanner) {
        var data = BuildingInputHelper.readStudioData(scanner);
        return new Studio(data.sellerId, data.soldStatus, data.address, data.surfaceArea, data.price, data.floorNumber, data.hasBalcony);
    }

    private static Apartment readApartmentFromKeyboard(Scanner scanner) {
        var data = BuildingInputHelper.readStudioData(scanner);

        System.out.print("Enter number of rooms (int): ");
        int numberOfRooms = Integer.parseInt(scanner.nextLine());

        return new Apartment(data.sellerId, data.soldStatus, data.address, data.surfaceArea, data.price, data.floorNumber, data.hasBalcony, numberOfRooms);
    }

    private static House readHouseFromKeyboard(Scanner scanner) {
        var data = BuildingInputHelper.readHouseData(scanner);
        return new House(data.sellerId, data.soldStatus, data.address, data.surfaceArea, data.price, data.numberOfFloors, data.numberOfRooms, data.hasGarden, data.hasGarage);
    }

    private static Villa readVillaFromKeyboard(Scanner scanner) {
        var data = BuildingInputHelper.readHouseData(scanner);

        System.out.print("Has pool? (true/false): ");
        boolean hasPool = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Has terrace? (true/false): ");
        boolean hasTerrace = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Enter view type (SEA, MOUNTAIN, CITY, etc.): ");
        ViewType viewType = ViewType.valueOf(scanner.nextLine().toUpperCase());

        return new Villa(data.sellerId, data.soldStatus, data.address, data.surfaceArea, data.price,
                data.numberOfFloors, data.numberOfRooms, data.hasGarden, data.hasGarage, hasPool,
                hasTerrace, viewType);
    }
}

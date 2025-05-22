package org.example;
import config.ConnectionProvider;
import model.*;
import repository.BuildingRepository;
import repository.BuildingRepositoryImpl;
import repository.SellerRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = ConnectionProvider.getConnection()) {
            System.out.println("Property Purchase System\n");
            System.out.println("1. Display available properties");
            System.out.println("2. Display sold properties");
            System.out.println("3. Add Property to the database");
            System.out.println("4. Add new seller into the system\n");
            System.out.println("Type desired option: ");
            String key = scanner.nextLine();
            switch (key) {
                case "1":
                    System.out.println("1. Display available properties");
                    break;
                case "2":
                    System.out.println("2. Display sold properties");
                    break;
                case "3":
                    System.out.println("3. Add Property to the database\n");
                    System.out.println("What property would you like to add?");
                    System.out.println("1. STUDIO");
                    System.out.println("2. APARTMENT");
                    System.out.println("3. HOUSE");
                    System.out.println("4. VILLA");
                    System.out.println("Type desired option: ");
                    String option = scanner.nextLine();

                    BuildingRepository buildingRepository = new BuildingRepositoryImpl(connection);

                    switch (option) {
                        case "1":
                            Studio studio = readStudioFromKeyboard(scanner);
                            buildingRepository.addBuilding(studio);
                            System.out.println("Studio added successfully!");
                            break;
                        case "2":
                            Apartment apartment = readApartmentFromKeyboard(scanner);
                            buildingRepository.addBuilding(apartment);
                            System.out.println("Apartment added successfully!");
                            break;
                        case "4":
                            Villa villa = readVillaFromKeyboard(scanner);
                            buildingRepository.addBuilding(villa);
                            System.out.println("Villa added successfully!");
                            break;
                    }
                    break;
                case "4":
                    System.out.println("4. Add new seller into the system");
                    break;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        scanner.close();
    }
    private static Studio readStudioFromKeyboard(Scanner scanner) {
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

        return new Studio(sellerId, soldStatus, address, surfaceArea, price, floor, balcony);
    }

    private static Apartment readApartmentFromKeyboard(Scanner scanner) {
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

        System.out.print("Enter number of rooms (int): ");
        int numberOfRooms = Integer.parseInt(scanner.nextLine());

        return new Apartment(sellerId, soldStatus, address, surfaceArea, price, floor, balcony, numberOfRooms);
    }

    private static Villa readVillaFromKeyboard(Scanner scanner) {
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

        System.out.print("Has pool? (true/false): ");
        boolean hasPool = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Has terrace? (true/false): ");
        boolean hasTerrace = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Enter view type (SEA, MOUNTAIN, CITY, etc.): ");
        ViewType viewType = ViewType.valueOf(scanner.nextLine().toUpperCase());

        return new Villa(sellerId, soldStatus, address, surfaceArea, price,
                numberOfFloors, numberOfRooms, hasGarden, hasGarage, hasPool,
                hasTerrace, viewType);
    }
}

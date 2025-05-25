package repository;

import model.*;
import java.sql.*;
import java.util.*;

public class BuildingRepositoryImpl implements BuildingRepository {
    private static BuildingRepositoryImpl instance;
    private final Connection connection;

    public BuildingRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    public static BuildingRepositoryImpl getInstance(Connection connection) {
        if (instance == null) {
            instance = new BuildingRepositoryImpl(connection);
        }
        return instance;
    }

    @Override
    public void addBuilding(Building building) {
        String sql = """
            INSERT INTO building 
            (seller_id, sold_status, building_type, address, surface_area, price,
             floor_number, has_balcony, number_of_rooms, number_of_floors,
             has_garden, has_garage, has_pool, has_terrace, view_type)
            VALUES (?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, building.getSellerId());
            preparedStatement.setBoolean(2, building.getSoldStatus());
            preparedStatement.setString(3, building.getType().name());
            preparedStatement.setString(4, building.getAddress());
            preparedStatement.setDouble(5, building.getSurfaceArea());
            preparedStatement.setDouble(6, building.getPrice());

            preparedStatement.setNull(7, Types.INTEGER); // floor_number
            preparedStatement.setNull(8, Types.BOOLEAN); // has_balcony
            preparedStatement.setNull(9, Types.INTEGER); // number_of_rooms
            preparedStatement.setNull(10, Types.INTEGER); // number_of_floors
            preparedStatement.setNull(11, Types.BOOLEAN); // has_garden
            preparedStatement.setNull(12, Types.BOOLEAN); // has_garage
            preparedStatement.setNull(13, Types.BOOLEAN); // has_pool
            preparedStatement.setNull(14, Types.BOOLEAN); // has_terrace
            preparedStatement.setNull(15, Types.VARCHAR); // view_type

            if (building instanceof Apartment apartment) {
                preparedStatement.setInt(7, apartment.getFloorNumber());
                preparedStatement.setBoolean(8, apartment.getHasBalcony());
                preparedStatement.setInt(9, apartment.getNumberOfRooms());
            } else if (building instanceof Studio studio) {
                preparedStatement.setInt(7, studio.getFloorNumber());
                preparedStatement.setBoolean(8, studio.getHasBalcony());
            } else if (building instanceof Villa villa) {
                preparedStatement.setNull(7, Types.INTEGER); // floor_number
                preparedStatement.setNull(8, Types.BOOLEAN); // has_balcony
                preparedStatement.setInt(9, villa.getNumberOfRooms());
                preparedStatement.setInt(10, villa.getNumberOfFloors());
                preparedStatement.setBoolean(11, villa.hasGarden());
                preparedStatement.setBoolean(12, villa.hasGarage());
                preparedStatement.setBoolean(13, villa.getHasPool());
                preparedStatement.setBoolean(14, villa.getHasTerrace());
                preparedStatement.setString(15, villa.getViewType().name());
            } else if (building instanceof House house) {
                preparedStatement.setNull(7, Types.INTEGER); // floor_number
                preparedStatement.setNull(8, Types.BOOLEAN); // has_balcony
                preparedStatement.setInt(9, house.getNumberOfRooms());
                preparedStatement.setInt(10, house.getNumberOfFloors());
                preparedStatement.setBoolean(11, house.hasGarden());
                preparedStatement.setBoolean(12, house.hasGarage());
            }
            System.out.println("Adding building: " + building.getAddress());
            int rows = preparedStatement.executeUpdate();
            System.out.println("Rows inserted: " + rows);
        } catch(SQLException e){
            e.printStackTrace();
            System.err.println("Failed to insert: " + e.getMessage());
        }
    }

    @Override
    public List<Building> getAllProperties() {
        String sql = "SELECT * FROM building";
        List<Building> buildings = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                //common fields
                int buildingID = rs.getInt("id");
                int sellerId = rs.getInt("seller_id");
                boolean soldStatus = rs.getBoolean("sold_status");
                String address = rs.getString("address");
                double surfaceArea = rs.getDouble("surface_area");
                double price = rs.getDouble("price");
                String type = rs.getString("building_type");

                int floorNumber = rs.getInt("floor_number");
                boolean hasBalcony = rs.getBoolean("has_balcony");
                int numberOfRooms = rs.getInt("number_of_rooms");
                int numberOfFloors = rs.getInt("number_of_floors");
                boolean hasGarden = rs.getBoolean("has_garden");
                boolean hasGarage = rs.getBoolean("has_garage");
                boolean hasPool = rs.getBoolean("has_pool");
                boolean hasTerrace = rs.getBoolean("has_terrace");
                String viewTypeStr = rs.getString("view_type");

                Building building = null;
                switch (type) {
                    case "STUDIO":
                        building = new Studio(buildingID, sellerId, soldStatus, address, surfaceArea, price,
                                floorNumber, hasBalcony);
                        break;
                    case "APARTMENT":
                        building = new Apartment(buildingID, sellerId, soldStatus, address, surfaceArea, price,
                                floorNumber, hasBalcony, numberOfRooms);
                        break;
                    case "HOUSE":
                        building = new House(buildingID, sellerId, soldStatus, address, surfaceArea, price,
                                numberOfRooms, numberOfFloors, hasGarden, hasGarage);
                        break;
                    case "VILLA":
                        ViewType viewType = viewTypeStr != null ? ViewType.valueOf(viewTypeStr) : null;
                        building = new Villa(buildingID, sellerId, soldStatus, address, surfaceArea, price,
                                numberOfFloors, numberOfRooms, hasGarden, hasGarage, hasPool,
                                hasTerrace, viewType);
                        break;
                }
                if (building != null) {
                    buildings.add(building);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to fetch all properties: " + e.getMessage());
        }
        return buildings;
    }

    @Override
    public List<Building> getAvailableProperties() {
        String sql = "SELECT * FROM building WHERE sold_status = false";
        List<Building> buildings = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                //common fields
                int buildingID = rs.getInt("id");
                int sellerId = rs.getInt("seller_id");
                boolean soldStatus = rs.getBoolean("sold_status");
                String address = rs.getString("address");
                double surfaceArea = rs.getDouble("surface_area");
                double price = rs.getDouble("price");
                String type = rs.getString("building_type");

                int floorNumber = rs.getInt("floor_number");
                boolean hasBalcony = rs.getBoolean("has_balcony");
                int numberOfRooms = rs.getInt("number_of_rooms");
                int numberOfFloors = rs.getInt("number_of_floors");
                boolean hasGarden = rs.getBoolean("has_garden");
                boolean hasGarage = rs.getBoolean("has_garage");
                boolean hasPool = rs.getBoolean("has_pool");
                boolean hasTerrace = rs.getBoolean("has_terrace");
                String viewTypeStr = rs.getString("view_type");

                Building building = null;
                switch (type) {
                    case "STUDIO":
                        building = new Studio(buildingID, sellerId, soldStatus, address, surfaceArea, price,
                                floorNumber, hasBalcony);
                        break;
                    case "APARTMENT":
                        building = new Apartment(buildingID, sellerId, soldStatus, address, surfaceArea, price,
                                floorNumber, hasBalcony, numberOfRooms);
                        break;
                    case "HOUSE":
                        building = new House(buildingID, sellerId, soldStatus, address, surfaceArea, price,
                                numberOfRooms, numberOfFloors, hasGarden, hasGarage);
                        break;
                    case "VILLA":
                        ViewType viewType = viewTypeStr != null ? ViewType.valueOf(viewTypeStr) : null;
                        building = new Villa(buildingID, sellerId, soldStatus, address, surfaceArea, price,
                                numberOfFloors, numberOfRooms, hasGarden, hasGarage, hasPool,
                                hasTerrace, viewType);
                        break;
                }
                if (building != null) {
                    buildings.add(building);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to fetch available properties: " + e.getMessage());
        }
        return buildings;
    }

    @Override
    public List<Building> getUnavailableProperties() {
        String sql = "SELECT * FROM building WHERE sold_status = true";
        List<Building> buildings = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                //common fields
                int buildingID = rs.getInt("id");
                int sellerId = rs.getInt("seller_id");
                boolean soldStatus = rs.getBoolean("sold_status");
                String address = rs.getString("address");
                double surfaceArea = rs.getDouble("surface_area");
                double price = rs.getDouble("price");
                String type = rs.getString("building_type");

                int floorNumber = rs.getInt("floor_number");
                boolean hasBalcony = rs.getBoolean("has_balcony");
                int numberOfRooms = rs.getInt("number_of_rooms");
                int numberOfFloors = rs.getInt("number_of_floors");
                boolean hasGarden = rs.getBoolean("has_garden");
                boolean hasGarage = rs.getBoolean("has_garage");
                boolean hasPool = rs.getBoolean("has_pool");
                boolean hasTerrace = rs.getBoolean("has_terrace");
                String viewTypeStr = rs.getString("view_type");

                Building building = null;
                switch (type) {
                    case "STUDIO":
                        building = new Studio(buildingID,sellerId, soldStatus, address, surfaceArea, price,
                                floorNumber, hasBalcony);
                        break;
                    case "APARTMENT":
                        building = new Apartment(buildingID, sellerId, soldStatus, address, surfaceArea, price,
                                floorNumber, hasBalcony, numberOfRooms);
                        break;
                    case "HOUSE":
                        building = new House(buildingID, sellerId, soldStatus, address, surfaceArea, price,
                                numberOfRooms, numberOfFloors, hasGarden, hasGarage);
                        break;
                    case "VILLA":
                        ViewType viewType = viewTypeStr != null ? ViewType.valueOf(viewTypeStr) : null;
                        building = new Villa(buildingID, sellerId, soldStatus, address, surfaceArea, price,
                                numberOfFloors, numberOfRooms, hasGarden, hasGarage, hasPool,
                                hasTerrace, viewType);
                        break;
                }
                if (building != null) {
                    buildings.add(building);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to fetch unavailable properties: " + e.getMessage());
        }
        return buildings;
    }

    @Override
    public Building getBuildingById(int key) {
        String sql = "SELECT * FROM building WHERE id = ?";
        Building building = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, key);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Common fields
                    int sellerId = rs.getInt("seller_id");
                    boolean soldStatus = rs.getBoolean("sold_status");
                    String address = rs.getString("address");
                    double surfaceArea = rs.getDouble("surface_area");
                    double price = rs.getDouble("price");
                    String type = rs.getString("building_type");

                    int floorNumber = rs.getInt("floor_number");
                    boolean hasBalcony = rs.getBoolean("has_balcony");
                    int numberOfRooms = rs.getInt("number_of_rooms");
                    int numberOfFloors = rs.getInt("number_of_floors");
                    boolean hasGarden = rs.getBoolean("has_garden");
                    boolean hasGarage = rs.getBoolean("has_garage");
                    boolean hasPool = rs.getBoolean("has_pool");
                    boolean hasTerrace = rs.getBoolean("has_terrace");
                    String viewTypeStr = rs.getString("view_type");

                    switch (type) {
                        case "STUDIO":
                            building = new Studio(key, sellerId, soldStatus, address, surfaceArea, price,
                                    floorNumber, hasBalcony);
                            break;
                        case "APARTMENT":
                            building = new Apartment(key, sellerId, soldStatus, address, surfaceArea, price,
                                    floorNumber, hasBalcony, numberOfRooms);
                            break;
                        case "HOUSE":
                            building = new House(key, sellerId, soldStatus, address, surfaceArea, price,
                                    numberOfRooms, numberOfFloors, hasGarden, hasGarage);
                            break;
                        case "VILLA":
                            ViewType viewType = viewTypeStr != null ? ViewType.valueOf(viewTypeStr) : null;
                            building = new Villa(key, sellerId, soldStatus, address, surfaceArea, price,
                                    numberOfFloors, numberOfRooms, hasGarden, hasGarage, hasPool,
                                    hasTerrace, viewType);
                            break;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to fetch building: " + e.getMessage());
        }

        return building;
    }

    public void markAsSold(int buildingId) {
        String sql = "UPDATE building SET sold_status = TRUE WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, buildingId);
            int updated = stmt.executeUpdate();
            System.out.println("Building marked as sold. Rows affected: " + updated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBuilding(int key) {
        String sql = "DELETE FROM building WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, key);
            int rowsDeleted = preparedStatement.executeUpdate();
            System.out.println("Deleted building: " + rowsDeleted);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error deleting building: " + e.getMessage());
        }
    }
}

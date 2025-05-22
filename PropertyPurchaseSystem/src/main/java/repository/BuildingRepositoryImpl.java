package repository;

import model.*;
import java.sql.*;

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
}

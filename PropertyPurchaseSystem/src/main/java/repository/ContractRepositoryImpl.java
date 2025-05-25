package repository;

import model.*;

import java.sql.*;
import java.util.*;

public class ContractRepositoryImpl implements ContractRepository {
    private static ContractRepositoryImpl instance;
    private final Connection connection;

    public ContractRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    public static ContractRepositoryImpl getInstance(Connection connection) {
        if (instance == null) {
            instance = new ContractRepositoryImpl(connection);
        }
        return instance;
    }

    @Override
    public void addContract(Contract contract) {
        String sql = """
        INSERT INTO contract (
            building_id, seller_id, seller_name, phone_number, 
            buyer_name, buyer_phone_number, price
        ) VALUES (?, ?, ?, ?, ?, ?, ?)
    """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, contract.getBuildingID());
            preparedStatement.setInt(2, contract.getSellerID());
            preparedStatement.setString(3, contract.getSellerName());
            preparedStatement.setString(4, contract.getSellerPhoneNumber());
            preparedStatement.setString(5, contract.getBuyerName());
            preparedStatement.setString(6, contract.getBuyerPhoneNumber());
            preparedStatement.setDouble(7, contract.getPrice());

            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println("Rows inserted: " + rowsInserted);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to insert: " + e.getMessage());
        }
    }

}

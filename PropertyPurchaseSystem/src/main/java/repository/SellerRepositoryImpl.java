package repository;

import model.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SellerRepositoryImpl implements SellerRepository {
    private static SellerRepositoryImpl instance;
    private final Connection connection;

    public SellerRepositoryImpl(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public static SellerRepositoryImpl getInstance(Connection connection) throws SQLException {
        if (instance == null) {
            instance = new SellerRepositoryImpl(connection);
        }
        return instance;
    }
    @Override
    public void addSeller(Seller seller) {
        String sql = """
        INSERT INTO seller (name, phone_number) VALUES (?, ?)
    """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, seller.getName());
            preparedStatement.setString(2, seller.getPhoneNumber());

            int rows = preparedStatement.executeUpdate();
            System.out.println("Rows inserted: " + rows);
        } catch(SQLException e){
            e.printStackTrace();
            System.err.println("Failed to insert: " + e.getMessage());
        }
    }


}

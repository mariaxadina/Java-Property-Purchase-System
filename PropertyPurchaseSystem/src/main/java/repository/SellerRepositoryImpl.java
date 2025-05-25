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

    @Override
    public Seller getSellerById(int key) {
        String sql = "SELECT * FROM seller WHERE id_seller = ?";
        Seller seller = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, key);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String phoneNumber = rs.getString("phone_number");

                    seller = new Seller(key, name, phoneNumber);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to fetch seller: " + e.getMessage());
        }

        return seller;
    }

}

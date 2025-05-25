package repository;

import model.Building;
import model.Seller;

public interface SellerRepository {
    void addSeller(Seller seller);
    Seller getSellerById(int id);
}

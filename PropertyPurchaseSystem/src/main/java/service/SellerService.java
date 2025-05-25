package service;
import repository.SellerRepository;
import java.util.*;
import model.*;

public class SellerService {
    private final SellerRepository sellerRepository;
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }
    public void addSeller(Seller seller) {

        if (seller.getPhoneNumber().length() != 10) {
            throw new IllegalArgumentException("Phone number must have exactly 10 characters");
        }

        sellerRepository.addSeller(seller);
    }
    public Seller getSellerById(int id) {return sellerRepository.getSellerById(id);}

}

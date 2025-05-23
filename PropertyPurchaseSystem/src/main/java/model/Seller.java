package model;

import java.util.ArrayList;
import java.util.List;

public class Seller {
    private int idSeller;
    private String name;
    private String phoneNumber;

    protected List<Building> buildings;
    public Seller(
            int idSeller,
            String name,
            String phoneNumber
    ) {
        this.idSeller = idSeller;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.buildings = new ArrayList<Building>();
    }
    public int getIdSeller() {
        return idSeller;
    }
    public void setIdSeller(int idSeller) {
        this.idSeller = idSeller;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public List<Building> getBuildings() {
        return buildings;
    }
    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }
}

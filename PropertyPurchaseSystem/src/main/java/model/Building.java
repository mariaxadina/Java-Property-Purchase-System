package model;

public abstract class Building {
    private int buildingID;
    private int sellerId;
    private boolean soldStatus;
    private String address;
    private double surfaceArea;
    private double price;
    public abstract BuildingType getType();

    // constructor without building id
    public Building(
            int sellerId,
            boolean soldStatus,
            String address,
            double surfaceArea,
            double price
    ) {
        this.buildingID = buildingID;
        this.sellerId = sellerId;
        this.soldStatus = soldStatus;
        this.address = address;
        this.surfaceArea = surfaceArea;
        this.price = price;
    }

    // constructor with building id
    public Building(
            int buildingID,
            int sellerId,
            boolean soldStatus,
            String address,
            double surfaceArea,
            double price
    ) {
        this.buildingID = buildingID;
        this.sellerId = sellerId;
        this.soldStatus = soldStatus;
        this.address = address;
        this.surfaceArea = surfaceArea;
        this.price = price;
    }

    public int getBuildingID() {return buildingID;}
    public int getSellerId() {
        return sellerId;
    }
    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
    public boolean getSoldStatus() {
        return soldStatus;
    }
    public void setSoldStatus(boolean soldStatus) {
        this.soldStatus = soldStatus;
    }
    public String getAddress() {
        return address;
    }
    public double getSurfaceArea() {
        return surfaceArea;
    }
    public double getPrice() {
        return price;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}

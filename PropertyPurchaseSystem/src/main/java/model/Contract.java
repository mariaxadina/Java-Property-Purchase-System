package model;

public class Contract {
    private int contractID;
    private int buildingID;
    private int sellerID;
    private String sellerName;
    private String sellerPhoneNumber;
    private String buyerName;
    private String buyerPhoneNumber;
    private double price;

    public Contract (
            int buildingID,
            int sellerID,
            String sellerName,
            String sellerPhoneNumber,
            String buyerName,
            String buyerPhoneNumber,
            double price
    ) {
        this.buildingID = buildingID;
        this.sellerID = sellerID;
        this.sellerName = sellerName;
        this.sellerPhoneNumber = sellerPhoneNumber;
        this.buyerName = buyerName;
        this.buyerPhoneNumber = buyerPhoneNumber;
        this.price = price;
    }
    public String getSellerName() {
        return sellerName;
    }
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
    public String getSellerPhoneNumber() {
        return sellerPhoneNumber;
    }
    public void setSellerPhoneNumber(String sellerPhoneNumber) {
        this.sellerPhoneNumber = sellerPhoneNumber;
    }
    public String getBuyerName() {
        return buyerName;
    }
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
    public String getBuyerPhoneNumber() {
        return buyerPhoneNumber;
    }
    public void setBuyerPhoneNumber(String buyerPhoneNumber) {
        this.buyerPhoneNumber = buyerPhoneNumber;
    }
    public int getBuildingID() {
        return buildingID;
    }
    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }
    public int getSellerID() {
        return sellerID;
    }
    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}

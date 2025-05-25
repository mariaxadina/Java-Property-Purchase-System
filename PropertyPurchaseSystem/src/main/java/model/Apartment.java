package model;

public class Apartment extends Studio {
    private int numberOfRooms;

    public Apartment(
            int sellerId,
            boolean soldStatus,
            String address,
            double surfaceArea,
            double price,
            int floorNumber,
            boolean hasBalcony,
            int numberOfRooms
    ) {
        super(sellerId, soldStatus, address, surfaceArea, price, floorNumber, hasBalcony);
        this.numberOfRooms = numberOfRooms;
    }

    public Apartment(
            int buildingID,
            int sellerId,
            boolean soldStatus,
            String address,
            double surfaceArea,
            double price,
            int floorNumber,
            boolean hasBalcony,
            int numberOfRooms
    ) {
        super(buildingID, sellerId, soldStatus, address, surfaceArea, price, floorNumber, hasBalcony);
        this.numberOfRooms = numberOfRooms;
    }

    @Override
    public BuildingType getType() {
        return BuildingType.APARTMENT;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }
}

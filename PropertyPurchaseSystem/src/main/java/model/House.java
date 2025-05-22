package model;

public class House extends Building{
    private int numberOfFloors;
    private int numberOfRooms;
    private boolean hasGarden;
    private boolean hasGarage;

    public House(int sellerId, boolean soldStatus, String address, double surfaceArea, double price, int numberOfFloors, int numberOfRooms, boolean hasGarden, boolean hasGarage) {
        super(sellerId, soldStatus, address, surfaceArea, price);
        this.numberOfFloors = 0;
        this.numberOfRooms = 0;
        this.hasGarden = false;
        this.hasGarage = false;
    }

    @Override
    public BuildingType getType() {
        return BuildingType.HOUSE;
    }
    public int getNumberOfFloors() {
        return numberOfFloors;
    }
    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }
    public int getNumberOfRooms() {
        return numberOfRooms;
    }
    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }
    public boolean hasGarden() {
        return hasGarden;
    }
    public boolean hasGarage() {
        return hasGarage;
    }
    public void setHasGarage(boolean hasGarage) {
        this.hasGarage = hasGarage;
    }
    public void setHasGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
    }
}

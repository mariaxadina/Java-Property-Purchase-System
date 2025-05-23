package model;

public class Studio extends Building{
    private int floorNumber;
    private boolean hasBalcony;

    public Studio(int sellerId,
                  boolean soldStatus,
                  String address,
                  double surfaceArea,
                  double price,
                  int floorNumber,
                  boolean hasBalcony
    ) {
        super(sellerId,soldStatus,address, surfaceArea, price);
        this.floorNumber = floorNumber;
        this.hasBalcony = hasBalcony;
    }
    @Override
    public BuildingType getType() {
        return BuildingType.STUDIO;
    }
    public int getFloorNumber() {
        return floorNumber;
    }
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
    public boolean getHasBalcony(){
        return hasBalcony;
    }
    public void setHasBalcony() {
        this.hasBalcony = true;
    }
}

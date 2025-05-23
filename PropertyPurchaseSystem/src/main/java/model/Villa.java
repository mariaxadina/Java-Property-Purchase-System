package model;

public class Villa extends House{
    private boolean hasPool;
    private boolean hasTerrace;
    public ViewType viewType;

    public Villa(int sellerId,
                 boolean soldStatus,
                 String address,
                 double surfaceArea,
                 double price,
                 int numberOfFloors,
                 int numberOfRooms,
                 boolean hasGarden,
                 boolean hasGarage,
                 boolean hasPool,
                 boolean hasTerrace,
                 ViewType viewType
    ) {
        super(sellerId, soldStatus, address, surfaceArea, price, numberOfFloors, numberOfRooms, hasGarden, hasGarage);
        this.hasPool = hasPool;
        this.hasTerrace = hasTerrace;
        this.viewType = viewType;
    }
    @Override
    public BuildingType getType(){
        return BuildingType.VILLA;
    }
    public ViewType getViewType(){
        return viewType;
    }
    public boolean getHasPool(){
        return hasPool;
    }
    public boolean getHasTerrace(){
        return hasTerrace;
    }
    public void setHasPool() {
        this.hasPool = true;
    }
    public void setHasTerrace() {
        this.hasTerrace = true;
    }
}

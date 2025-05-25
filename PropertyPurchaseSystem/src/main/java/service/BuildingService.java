package service;
import model.*;
import repository.BuildingRepository;
import java.util.*;


public class BuildingService {
    private final BuildingRepository buildingRepository;

    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public void addBuilding(Building building) {
        if (building.getSurfaceArea() <= 0 || building.getPrice() <= 0) {
            throw new IllegalArgumentException("Invalid surface area or price");
        }

        buildingRepository.addBuilding(building);
    }

    public List<Building> getAvailableProperties() {
        return buildingRepository.getAvailableProperties();
    }
    public List<Building> getUnavailableProperties() {
        return buildingRepository.getUnavailableProperties();
    }
    public List<Building> getAllProperties() {
        return buildingRepository.getAllProperties();
    }
    public Building getBuildingById(int id) {return buildingRepository.getBuildingById(id);}
}

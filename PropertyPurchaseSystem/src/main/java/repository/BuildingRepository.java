package repository;
import java.util.List;
import model.Building;

public interface BuildingRepository {
    void addBuilding(Building building);
    List<Building> getAvailableProperties();
    List<Building> getUnavailableProperties();
    List<Building> getAllProperties();

}

package DAO;

import models.Location;

import java.sql.SQLException;
import java.util.List;

public class LocationDAO {
    private static LocationDAO instance;
    private GenericDAO<Location> genericDAO;

    private LocationDAO() {
        genericDAO = new GenericDAO<>(Location.class);
    }

    public static LocationDAO getInstance() {
        if (instance == null) {
            instance = new LocationDAO();
        }
        return instance;
    }

    public void addLocation(Location location) throws SQLException, IllegalAccessException {
        genericDAO.add(location);
    }

    public List<Location> getLocations() throws SQLException, IllegalAccessException, InstantiationException {
        return genericDAO.getAll();
    }

    public void updateLocation(Location location) throws SQLException, IllegalAccessException {
        genericDAO.update(location);
    }

    public void deleteLocation(int locationId) throws SQLException {
        genericDAO.delete(locationId);
    }
}

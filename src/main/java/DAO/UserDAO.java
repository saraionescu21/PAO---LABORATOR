package DAO;

import models.User;

import java.sql.SQLException;
import java.util.List;

public class UserDAO {
    private static UserDAO instance;
    private GenericDAO<User> genericDAO;

    public UserDAO() {
        genericDAO = new GenericDAO<>(User.class);
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public void addUser(User user) throws SQLException, IllegalAccessException {
        genericDAO.add(user);
    }

    public List<User> getUsers() throws SQLException, IllegalAccessException, InstantiationException {
        return genericDAO.getAll();
    }

    public void updateUser(User user) throws SQLException, IllegalAccessException {
        genericDAO.update(user);
    }

    public void deleteUser(int userId) throws SQLException {
        genericDAO.delete(userId);
    }
}

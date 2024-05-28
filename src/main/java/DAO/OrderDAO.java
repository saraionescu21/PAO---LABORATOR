package DAO;

import models.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderDAO {
    private static OrderDAO instance;
    private GenericDAO<Order> genericDAO;

    private OrderDAO() {
        genericDAO = new GenericDAO<>(Order.class);
    }

    public static OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO();
        }
        return instance;
    }

    public void addOrder(Order order) throws SQLException, IllegalAccessException {
        genericDAO.add(order);
    }

    public List<Order> getOrders() throws SQLException, IllegalAccessException, InstantiationException {
        return genericDAO.getAll();
    }

    public void updateOrder(Order order) throws SQLException, IllegalAccessException {
        genericDAO.update(order);
    }

    public void deleteOrder(int orderId) throws SQLException {
        genericDAO.delete(orderId);
    }
}

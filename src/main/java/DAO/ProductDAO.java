package DAO;

import models.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductDAO {
    private static ProductDAO instance;
    private GenericDAO<Product> genericDAO;

    public ProductDAO() {
        genericDAO = new GenericDAO<>(Product.class);
    }

    public static ProductDAO getInstance() {
        if (instance == null) {
            instance = new ProductDAO();
        }
        return instance;
    }

    public void addProduct(Product product) throws SQLException, IllegalAccessException {
        genericDAO.add(product);
    }

    public List<Product> getProducts() throws SQLException, IllegalAccessException, InstantiationException {
        return genericDAO.getAll();
    }

    public void updateProduct(Product product) throws SQLException, IllegalAccessException {
        genericDAO.update(product);
    }

    public void deleteProduct(int productId) throws SQLException {
        genericDAO.delete(productId);
    }
}

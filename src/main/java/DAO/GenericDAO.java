package DAO;

import database.config.DatabaseConnection;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenericDAO<T> {
    private Class<T> type;

    public GenericDAO(Class<T> type) {
        this.type = type;
    }

    public void add(T entity) throws SQLException, IllegalAccessException {
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(type.getSimpleName()).append("s (");

        Field[] fields = type.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            query.append(fields[i].getName());
            if (i < fields.length - 1) query.append(", ");
        }

        query.append(") VALUES (");
        for (int i = 0; i < fields.length; i++) {
            query.append("?");
            if (i < fields.length - 1) query.append(", ");
        }
        query.append(")");

        try (Connection conn = DatabaseConnection.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                stmt.setObject(i + 1, fields[i].get(entity));
            }
            stmt.executeUpdate();
        }
    }

    public List<T> getAll() throws SQLException, IllegalAccessException, InstantiationException {
        List<T> entities = new ArrayList<>();
        String query = "SELECT * FROM " + type.getSimpleName() + "s";

        try (Connection conn = DatabaseConnection.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                T entity = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    field.setAccessible(true);
                    field.set(entity, rs.getObject(field.getName()));
                }
                entities.add(entity);
            }
        }
        return entities;
    }

    public void update(T entity) throws SQLException, IllegalAccessException {
        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(type.getSimpleName()).append("s SET ");

        Field[] fields = type.getDeclaredFields();
        String idField = fields[0].getName();
        for (int i = 1; i < fields.length; i++) {
            query.append(fields[i].getName()).append(" = ?");
            if (i < fields.length - 1) query.append(", ");
        }
        query.append(" WHERE ").append(idField).append(" = ?");

        try (Connection conn = DatabaseConnection.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {
            for (int i = 1; i < fields.length; i++) {
                fields[i].setAccessible(true);
                stmt.setObject(i, fields[i].get(entity));
            }
            fields[0].setAccessible(true);
            stmt.setObject(fields.length, fields[0].get(entity));
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM " + type.getSimpleName() + "s WHERE " + type.getDeclaredFields()[0].getName() + " = ?";

        try (Connection conn = DatabaseConnection.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

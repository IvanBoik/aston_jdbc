package com.boiko.aston_jdbc.repository;

import org.springframework.beans.factory.annotation.Value;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepository<T> {
    @Value("${spring.datasource.url}")
    protected String url;
    @Value("${spring.datasource.username}")
    protected String username;
    @Value("${spring.datasource.password}")
    protected String password;

    protected final String tableName;

    public BaseRepository(String tableName) {
        this.tableName = tableName;
    }

    public boolean delete(Long id) {
        try(var connection = DriverManager.getConnection(url, username, password)) {
            String sql = "DELETE FROM " + tableName + " WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            return statement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public T getByID(Long id) {
        try(var connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            return resultSetToModel(resultSet);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> getAll() {
        try(var connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM " + tableName;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<T> models = new ArrayList<>();

            while (resultSet.next()) {
                models.add(resultSetToModel(resultSet));
            }
            return models;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract boolean insert(T model);
    public abstract boolean update(T model);
    protected abstract T resultSetToModel(ResultSet resultSet);
}

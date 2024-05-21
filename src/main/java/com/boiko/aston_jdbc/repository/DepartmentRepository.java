package com.boiko.aston_jdbc.repository;

import com.boiko.aston_jdbc.model.Department;
import org.springframework.stereotype.Repository;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DepartmentRepository extends BaseRepository<Department> {
    public DepartmentRepository() {
        super("departments");
    }

    @Override
    public boolean insert(Department department) {
        try(var connection = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO departments (name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, department.getName());
            return statement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Department department) {
        try(var connection = DriverManager.getConnection(url, username, password)) {
            String sql = "UPDATE departments SET name = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, department.getName());
            statement.setLong(2, department.getId());
            return statement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Department resultSetToModel(ResultSet resultSet) {
        try {
            Department department = new Department();
            department.setId(resultSet.getLong("id"));
            department.setName(resultSet.getString("name"));
            return department;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

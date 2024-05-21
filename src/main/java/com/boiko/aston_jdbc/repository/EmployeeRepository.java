package com.boiko.aston_jdbc.repository;

import com.boiko.aston_jdbc.model.Employee;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository extends BaseRepository<Employee> {
    public EmployeeRepository() {
        super("employees");
    }

    @Override
    public boolean insert(Employee model) {
        try {
            String sql = "INSERT INTO employees (id_passport, id_department, id_position, name, surname, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = fillStatement(sql, model);
            return statement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement fillStatement(String sql, Employee model) {
        try(var connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, model.getPassportID());
            statement.setLong(2, model.getDepartmentID());
            statement.setLong(3, model.getPositionID());
            statement.setString(4, model.getName());
            statement.setString(5, model.getSurname());
            statement.setString(6, model.getEmail());
            statement.setString(7, model.getPhone());
            return statement;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Employee model) {
        try {
            String sql = "UPDATE employees SET id_passport=?, id_department=?, id_position=?, name=?, surname=?, email=?, phone=? WHERE id=?";
            PreparedStatement statement = fillStatement(sql, model);
            statement.setLong(8, model.getId());
            return statement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Employee resultSetToModel(ResultSet resultSet) {
        try {
            Employee employee = new Employee();
            employee.setId(resultSet.getLong("id"));
            employee.setPassportID(resultSet.getLong("id_passport"));
            employee.setDepartmentID(resultSet.getLong("id_department"));
            employee.setPositionID(resultSet.getLong("id_position"));
            employee.setName(resultSet.getString("name"));
            employee.setSurname(resultSet.getString("surname"));
            employee.setEmail(resultSet.getString("email"));
            employee.setPhone(resultSet.getString("phone"));
            return employee;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Employee> getAllByProjectID(Long id) {
        try(var connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT e.* FROM employees AS e JOIN employees_projects AS ep ON e.id = ep.id_employee WHERE ep.id_project = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            List<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                employees.add(resultSetToModel(resultSet));
            }
            return employees;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

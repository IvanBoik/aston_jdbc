package com.boiko.aston_jdbc.repository;

import com.boiko.aston_jdbc.model.Project;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepository extends BaseRepository<Project> {
    public ProjectRepository() {
        super("projects");
    }

    @Override
    public boolean insert(Project model) {
        try(var connection = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO projects (name, start_date, end_date) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, model.getName());
            statement.setDate(2, Date.valueOf(model.getStartDate()));
            statement.setDate(3, Date.valueOf(model.getEndDate()));
            return statement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Project model) {
        try(var connection = DriverManager.getConnection(url, username, password)) {
            String sql = "UPDATE projects SET name=?, start_date=?, end_date=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, model.getName());
            statement.setDate(2, Date.valueOf(model.getStartDate()));
            statement.setDate(3, Date.valueOf(model.getEndDate()));
            statement.setLong(4, model.getId());
            return statement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Project resultSetToModel(ResultSet resultSet) {
        try {
            Project project = new Project();
            project.setId(resultSet.getLong("id"));
            project.setName(resultSet.getString("name"));
            project.setStartDate(resultSet.getDate("start_date").toLocalDate());
            project.setEndDate(resultSet.getDate("end_date").toLocalDate());
            return project;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Project> getAllByEmployeeID(Long id) {
        try(var connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT p.* FROM projects AS p JOIN employees_projects AS ep ON p.id = ep.id_project WHERE ep.id_employee = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            List<Project> projects = new ArrayList<>();
            while (resultSet.next()) {
                projects.add(resultSetToModel(resultSet));
            }
            return projects;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

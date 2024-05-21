package com.boiko.aston_jdbc.repository;

import com.boiko.aston_jdbc.model.Position;
import org.springframework.stereotype.Repository;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class PositionRepository extends BaseRepository<Position> {
    public PositionRepository() {
        super("positions");
    }

    @Override
    public boolean insert(Position position) {
        try(var connection = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO positions (name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position.getName());
            return statement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Position position) {
        try(var connection = DriverManager.getConnection(url, username, password)) {
            String sql = "UPDATE positions SET name = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position.getName());
            statement.setLong(2, position.getId());
            return statement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Position resultSetToModel(ResultSet resultSet) {
        try {
            Position position = new Position();
            position.setId(resultSet.getLong("id"));
            position.setName(resultSet.getString("name"));
            return position;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

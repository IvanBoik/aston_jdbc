package com.boiko.aston_jdbc.repository;

import com.boiko.aston_jdbc.model.PassportData;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class PassportRepository extends BaseRepository<PassportData> {
    public PassportRepository() {
        super("passports_data");
    }

    @Override
    public boolean insert(PassportData passportData) {
        try {
            String sql = "INSERT INTO passports_data (series, number, date_of_issue, department_code, issuing_authority) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = fillStatement(sql, passportData);
            return statement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(PassportData passportData) {
        try {
            String sql = "UPDATE passports_data SET series=?, number=?, date_of_issue=?, department_code=?, issuing_authority=? WHERE id=?";
            PreparedStatement statement = fillStatement(sql, passportData);
            statement.setLong(6, passportData.getId());
            return statement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement fillStatement(String sql, PassportData passportData) {
        try(var connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, passportData.getSeries());
            statement.setString(2, passportData.getNumber());
            statement.setDate(3, Date.valueOf(passportData.getDateOfIssue()));
            statement.setString(4, passportData.getDepartmentCode());
            statement.setString(5, passportData.getIssuingAuthority());
            return statement;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected PassportData resultSetToModel(ResultSet resultSet) {
        try {
            PassportData passportData = new PassportData();
            passportData.setId(resultSet.getLong("id"));
            passportData.setSeries(resultSet.getString("series"));
            passportData.setNumber(resultSet.getString("number"));
            passportData.setDateOfIssue(resultSet.getDate("date_of_issue").toLocalDate());
            passportData.setDepartmentCode(resultSet.getString("department_code"));
            passportData.setIssuingAuthority(resultSet.getString("issuing_authority"));

            return passportData;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

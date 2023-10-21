package org.example.dao.impl;

import org.example.config.Config;
import org.example.dao.JobDao;
import org.example.model.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {

    Connection connection = Config.getConnection();


    @Override
    public void addJob(Job job) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("""
                                        insert into job (id, position, profession, description,experience)
                                        values (?,?,?,?, ?)
                             """)) {

            preparedStatement.setLong(1, job.getId());
            preparedStatement.setString(2, job.getPosition());
            preparedStatement.setString(3, job.getProfession());
            preparedStatement.setString(4, job.getDescription());
            preparedStatement.setInt(5, job.getExperience());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Job getJobById(Long jobId) {
        Job job = new Job();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement =
                    connection.prepareStatement("""
                              select * from job where id = ?                        
                            """);
            preparedStatement.setLong(1, jobId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new RuntimeException("Not found");
            } else {
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return job;
    }


    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        return null;
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        Job job = new Job();
        PreparedStatement preparedStatement = null;
        try {
            String sql = "select * from job where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new RuntimeException("Not founded");
            } else {
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return job;
    }

    @Override
    public void deleteDescriptionColumn() {
        String sql = "ALTER TABLE job DROP COLUMN description"; // Replace your_table_name with the actual table name.

        try (Connection connection = Config.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
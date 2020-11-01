package com.vz89.hometask.repository.db;

import com.vz89.hometask.model.Skill;
import com.vz89.hometask.repository.SkillRepository;
import com.vz89.hometask.service.DbService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbSkillRepositoryImpl implements SkillRepository {
    private static final String ID = "id";
    private static final String SKILL_NAME = "skill_name";
    private DbService dbService = new DbService();
    private Connection connection;

    @Override
    public Skill getById(Long id) {
        Skill skill = null;
        try {
            connection = dbService.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM skill WHERE id='" + id + "'");
            while (resultSet.next()) {
                Long skillId = resultSet.getLong(ID);
                String name = resultSet.getString(SKILL_NAME);
                skill = new Skill(skillId, name);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skill;
    }

    @Override
    public List<Skill> findAll() {
        List<Skill> skills = new ArrayList<>();
        try {
            connection = dbService.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM skill");
            while (resultSet.next()) {
                Long id = resultSet.getLong(ID);
                String name = resultSet.getString(SKILL_NAME);
                skills.add(new Skill(id, name));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }

    @Override
    public Skill save(Skill skill) {
        try {
            connection = dbService.getConnection();
            Statement statement = connection.createStatement();
            statement.addBatch("Insert Into skill (skill_name) VALUES('" + skill.getName() + "')");
            statement.executeBatch();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        try {
            connection = dbService.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE skill SET skill_name='" + skill.getName() + "' WHERE id='" + skill.getId() + "'");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skill;
    }

    @Override
    public void deleteById(Long id) {
        try {
            connection = dbService.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM skill WHERE id='" + id + "'");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

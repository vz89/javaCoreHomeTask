package com.vz89.hometask.repository.db;

import com.vz89.hometask.model.Developer;
import com.vz89.hometask.model.Skill;
import com.vz89.hometask.repository.AccountRepository;
import com.vz89.hometask.repository.DeveloperRepository;
import com.vz89.hometask.repository.SkillRepository;
import com.vz89.hometask.service.DbService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DbDeveloperRepositoryImpl implements DeveloperRepository {
    private DbService dbService = new DbService();
    private Connection connection;
    private AccountRepository accountRepository = new DbAccountRepositoryImpl();
    private SkillRepository skillRepository = new DbSkillRepositoryImpl();

    @Override
    public Developer getById(Long id) {
        Developer developer = null;
        try {
            connection = dbService.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM developer WHERE id='" + id + "'");
            while (resultSet.next()) {
                long developerId = resultSet.getLong("id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                long account_id = resultSet.getLong("account_id");
                developer = new Developer(developerId, first_name, last_name, accountRepository.getById(account_id));
            }
            resultSet = statement.executeQuery("SELECT * FROM developer_skill WHERE developer_id='" + id + "'");
            Set<Skill> skills = new HashSet<>();
            developer.setSkills(skills);
            while (resultSet.next()) {
                long skillId = resultSet.getLong("skill_id");
                developer.getSkills().add(skillRepository.getById(skillId));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public List<Developer> findAll() {
        return null;
    }

    @Override
    public Developer save(Developer developer) {
        try {
            connection = dbService.getConnection();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate("INSERT INTO developer (first_name, last_name, account_id) VALUES('" + developer.getFirstName()
                    + "','" + developer.getLastName() + "','" + developer.getAccount().getId() + "')");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM developer where account_id='" + developer.getAccount().getId() + "'");
            resultSet.last();
            developer.setId(resultSet.getLong("id"));

            for (Skill skill : developer.getSkills()) {
                statement.addBatch("INSERT INTO developer_skill (developer_id, skill_id) VALUES ('"
                        + developer.getId() + "','" + skill.getId() + "')");
            }
            statement.executeBatch();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}

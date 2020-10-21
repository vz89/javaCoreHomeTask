package com.vz89.hometask.repository.db;

import com.vz89.hometask.config.PropertiesReader;
import com.vz89.hometask.model.Skill;
import com.vz89.hometask.repository.SkillRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DbSkillRepositoryImpl implements SkillRepository {
    PropertiesReader propertiesReader;
    {
        try {
            propertiesReader = new PropertiesReader("app.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Override
    public Skill getById(Long aLong) {
        return null;
    }

    @Override
    public List<Skill> findAll() {
        return null;
    }

    @Override
    public Skill save(Skill skill) {
        try {
            System.out.println("Устанавливаем соединение");
            Connection connection = DriverManager.getConnection(propertiesReader.getProperty("db.url"),propertiesReader.getProperty("db.username"),propertiesReader.getProperty("db.password"));
            System.out.println("Соединение установлено");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Skill update(Skill skill) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}

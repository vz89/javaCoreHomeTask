package com.vz89.hometask.service;

import com.vz89.hometask.config.PropertiesReader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbService {
    private PropertiesReader propertiesReader;

    private static final String PROPERTY_FILE_NAME = "app.properties";

    {
        try {
            propertiesReader = new PropertiesReader(PROPERTY_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(propertiesReader.getProperty("db.url"), propertiesReader.getProperty("db.username"), propertiesReader.getProperty("db.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

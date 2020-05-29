package com.safetynet.safetynetalert.configuration;

import com.safetynet.safetynetalert.interfaces.DatabaseConfigInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseConfig implements DatabaseConfigInterface {
    private static final Logger logger = LogManager.getLogger("DatabaseConfig");

    String data;

    @Override
    public void openConnection() {
        logger.info("Connection open");
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public void closeConnection() {
        logger.info("Connection close");
    }
}

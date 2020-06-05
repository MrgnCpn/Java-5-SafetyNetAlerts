package com.safetynet.safetynetalert.configuration;

import com.safetynet.safetynetalert.interfaces.DatabaseConfigInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class DatabaseConfig implements DatabaseConfigInterface {
    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger("DatabaseConfig");

    /**
     * Data converted into JSONObject from data.json file
     */
    private JSONObject data;


    /**
     * Constructor
     */
    public DatabaseConfig() { }

    /**
     * @see com.safetynet.safetynetalert.interfaces.DatabaseConfigInterface {@link #openConnection()}
     */
    @Override
    public JSONObject openConnection() {
        try (FileReader fileData = new FileReader("src/main/resources/static/data.json")){
            this.data = (JSONObject) new JSONParser().parse(fileData);
            logger.info("Connection open");
            return this.data;
        } catch (Exception e) {
            logger.error("Connection error");
            return null;
        }
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.DatabaseConfigInterface {@link #openConnection(String)}
     */
    @Override
    public JSONObject openConnection(String filePath){
        try (FileReader fileData = new FileReader(filePath)){
            this.data = (JSONObject) new JSONParser().parse(fileData);
            logger.info("Connection open");
            return this.data;
        } catch (Exception e) {
            logger.error("Connection error");
            return null;
        }
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.DatabaseConfigInterface {@link #getData()}
     */
    @Override
    public JSONObject getData() {
        return data;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.DatabaseConfigInterface {@link #closeConnection()}
     */
    @Override
    public void closeConnection() {
        this.data = null;
        logger.info("Connection close");
    }
}

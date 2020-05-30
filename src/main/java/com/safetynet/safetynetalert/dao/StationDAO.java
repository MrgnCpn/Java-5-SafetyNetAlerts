package com.safetynet.safetynetalert.dao;

import com.safetynet.safetynetalert.configuration.DatabaseConfig;
import com.safetynet.safetynetalert.interfaces.StationDAOInterface;
import com.safetynet.safetynetalert.models.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class StationDAO implements StationDAOInterface {
    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger("StationDAO");

    /**
     * Database configuration
     */
    private DatabaseConfig databaseConfig;

    /**
     * List of all stations from database
     */
    private List<Station> allStations;

    /**
     * Constructor and load data
     * @param databaseConfig
     * @throws IOException
     * @throws ParseException
     */
    public StationDAO(DatabaseConfig databaseConfig) throws IOException, ParseException {
        this.databaseConfig = databaseConfig;
        loadData();
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationDAOInterface {@link #setDatabaseConfig(DatabaseConfig)}
     */
    @Override
    public void setDatabaseConfig(DatabaseConfig dataBaseConfig) {

    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationDAOInterface {@link #getStation(Integer)}
     */
    @Override
    public Station getStation(Integer number) {
        return null;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationDAOInterface {@link #getListStations(String, String)}
     */
    @Override
    public List<Station> getListStations(String key, String value) {
        return null;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationDAOInterface {@link #addNewStation(Station)}
     */
    @Override
    public void addNewStation(Station station) {

    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationDAOInterface {@link #updateStation(Station)}
     */
    @Override
    public void updateStation(Station station) {

    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationDAOInterface {@link #deleteStation(Station)}
     */
    @Override
    public void deleteStation(Station station) {

    }

    /**
     * Load data in allSations in constructor
     * @throws IOException
     * @throws ParseException
     */
    private void loadData() throws IOException, ParseException {
        databaseConfig.openConnection();
        JSONObject data = databaseConfig.getData();

        logger.info("All stations are loaded from data");
        databaseConfig.closeConnection();
    }
}

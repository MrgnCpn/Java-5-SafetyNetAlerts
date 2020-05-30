package com.safetynet.safetynetalert.dao;

import com.safetynet.safetynetalert.configuration.DatabaseConfig;
import com.safetynet.safetynetalert.interfaces.StationDAOInterface;
import com.safetynet.safetynetalert.models.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
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
        this.allStations = new ArrayList<>();
        loadData();
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationDAOInterface {@link #setDatabaseConfig(DatabaseConfig)}
     */
    @Override
    public void setDatabaseConfig(DatabaseConfig dataBaseConfig) {
        this.databaseConfig = dataBaseConfig;
    }


    /**
     * @see com.safetynet.safetynetalert.interfaces.StationDAOInterface {@link #getStationByNumber(Integer)}
     */
    @Override
    public Station getStationByNumber(Integer number) {
        for (int i = 0; i < allStations.size(); i++) {
            if (allStations.get(i).getNumber().equals(number)) {
                return allStations.get(i);
            }
        }
        return null;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationDAOInterface {@link #getStationByAddress(String)}
     */
    @Override
    public Station getStationByAddress(String address) {
        for (int i = 0; i < allStations.size(); i++) {
            if (allStations.get(i).getAddress().equals(address)) {
                return allStations.get(i);
            }
        }
        return null;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationDAOInterface {@link #getAllStations()}
     */
    @Override
    public List<Station> getAllStations() {
        return this.allStations;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationDAOInterface {@link #addNewStation(Station)}
     */
    @Override
    public void addNewStation(Station station) {
        this.allStations.add(station);
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationDAOInterface {@link #updateStation(Station)}
     */
    @Override
    public void updateStation(Station station) {
        for (int i = 0; i < allStations.size(); i++) {
            if (allStations.get(i).getNumber().equals(station.getNumber())) {
                allStations.get(i).setAddress(station.getAddress());
                break;
            }
        }
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationDAOInterface {@link #deleteStationByNumber(Integer)}
     */
    @Override
    public void deleteStationByNumber(Integer number) {
        for (int i = 0; i < allStations.size(); i++) {
            if (allStations.get(i).getNumber().equals(number)) {
                allStations.remove(i);
                break;
            }
        }
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationDAOInterface {@link #deleteStationByAddress(String)}
     */
    @Override
    public void deleteStationByAddress(String address) {
        for (int i = 0; i < allStations.size(); i++) {
            if (allStations.get(i).getAddress().equals(address)) {
                allStations.remove(i);
                break;
            }
        }
    }

    /**
     * Load data in allStations in constructor
     * @throws IOException
     * @throws ParseException
     */
    private void loadData() throws IOException, ParseException {
        databaseConfig.openConnection();
        try {
            JSONObject data = databaseConfig.getData();

            JSONArray stations = (JSONArray) data.get("stations");

            for (int i = 0; i < stations.size(); i++) {
                JSONObject station = (JSONObject) stations.get(i);
                Integer number = (Integer) station.get("firstName");
                String address = (String) station.get("lastName");
                allStations.add(new Station(number, address));
            }

            logger.info("All stations are loaded from data");
        } catch (Exception e) {
            logger.error("Data can't be loaded in StationDAO : " + e);
        }
        databaseConfig.closeConnection();
    }
}

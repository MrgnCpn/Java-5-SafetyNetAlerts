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
    public StationDAO(DatabaseConfig databaseConfig) {
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
    public List<Station> getStationByNumber(Integer number) {
        List<Station> listOfStation = new ArrayList<>();

        for (Station iStation : allStations) {
            if (iStation.getNumber().equals(number)) {
                listOfStation.add(iStation);
            }
        }
        return listOfStation;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationDAOInterface {@link #getStationByAddress(String)}
     */
    @Override
    public Station getStationByAddress(String address) {
        for (Station iStation : allStations) {
            if (iStation.getAddress().equals(address)) {
                return iStation;
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
    public boolean addNewStation(Station station) {
        boolean stationAdded = false;

        if (
                (station.getNumber() > 0)
                && !station.getAddress().isEmpty()
        ) {
            this.allStations.add(station);
            stationAdded = true;
        }


        if (stationAdded) {
            logger.info("Station has been added");
        } else {
            logger.error("Station informations aren't complete and could not be added");
        }

        return stationAdded;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationDAOInterface {@link #updateStation(Station)}
     */
    @Override
    public boolean updateStation(Station station) {
        boolean stationUpdated = false;

        for (Station iStation : allStations) {
            if (iStation.getAddress().equals(station.getAddress())) {
                iStation.setNumber(station.getNumber());
                stationUpdated = true;
                break;
            }
        }

        if (stationUpdated) {
            logger.info("Station informations have been updated");
        } else {
            logger.error("Station doesn't exist in stations list and could not be updated");
        }

        return stationUpdated;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationDAOInterface {@link #deleteStationMapping(String)}
     */
    @Override
    public boolean deleteStationMapping(String address) {
        boolean stationDeleted = false;

        for (int i = 0; i < allStations.size(); i++) {
            if (allStations.get(i).getAddress().equals(address)) {
                allStations.remove(i);
                stationDeleted = true;
                break;
            }
        }

        if (stationDeleted) {
            logger.info("Station has been deleted");
        } else {
            logger.error("Station doesn't exist in stations list and could not be deleted");
        }

        return stationDeleted;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationDAOInterface {@link #deleteStationByNumber(Integer)}
     */
    @Override
    public boolean deleteStationByNumber(Integer number) {
        boolean stationDeleted = false;

        for (int i = 0; i < allStations.size(); i++) {
            if (allStations.get(i).getNumber().equals(number)) {
                allStations.remove(i);
                stationDeleted = true;
            }
        }

        if (stationDeleted) {
            logger.info("Station has been deleted");
        } else {
            logger.error("Station doesn't exist in stations list and could not be deleted");
        }

        return stationDeleted;
    }

    /**
     * Load data in allStations in constructor
     */
    private void loadData() {
        try {
            JSONObject data = databaseConfig.openConnection();

            JSONArray stations = (JSONArray) data.get("firestations");

            for (int i = 0; i < stations.size(); i++) {
                JSONObject station = (JSONObject) stations.get(i);
                Integer number = Integer.parseInt((String) station.get("station"));
                String address = (String) station.get("address");
                allStations.add(new Station(number, address));
            }

            logger.info("All stations are loaded from data");
        } catch (Exception e) {
            logger.error("Data can't be loaded in StationDAO : " + e);
        }
        databaseConfig.closeConnection();
    }
}

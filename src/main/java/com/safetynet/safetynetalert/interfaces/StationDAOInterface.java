package com.safetynet.safetynetalert.interfaces;

import com.safetynet.safetynetalert.configuration.DatabaseConfig;
import com.safetynet.safetynetalert.models.Station;

import java.util.List;

public interface StationDAOInterface {

    /**
     * Set DatabaseConfiguration
     * @param dataBaseConfig
     */
    void setDatabaseConfig(DatabaseConfig dataBaseConfig);

    /**
     * Get one station from allStations choose by station number
     * @param number
     * @return One Station
     */
    Station getStation(Integer number);

    /**
     * Get list of stations choose by Key and value
     * @param key
     * @param value
     * @return list of stations
     */
    List<Station> getListStations(String key, String value);

    /**
     * Add one new station
     * @param station
     */
    void addNewStation(Station station);

    /**
     * Update one station in allStations
     * @param station
     */
    void updateStation(Station station);

    /**
     * Delete the station in allStations
     * @param station
     */
    void deleteStation(Station station);
}

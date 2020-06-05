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
     * @return List of Stations
     */
    List<Station> getStationByNumber(Integer number);

    /**
     * Get one station from allStations choose by station address
     * @param address
     * @return One Station
     */
    Station getStationByAddress(String address);

    /**
     *  Get all stations
     * @return List of all stations
     */
    List<Station> getAllStations();

    /**
     * Add one new station
     * @param station
     */
    Boolean addNewStation(Station station);

    /**
     * Update one station in allStations
     * @param station
     */
    Boolean updateStation(Station station);


    /**
     * Delete the station in allStations
     * @param address
     */
    Boolean deleteStationByAddress(String address);
}

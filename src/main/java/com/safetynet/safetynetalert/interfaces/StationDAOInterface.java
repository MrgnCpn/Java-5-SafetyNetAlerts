package com.safetynet.safetynetalert.interfaces;

import com.safetynet.safetynetalert.configuration.DatabaseConfig;
import com.safetynet.safetynetalert.models.Station;

import java.util.List;

public interface StationDAOInterface {
    void StationDAO(DatabaseConfig databaseConfig);
    void setDatabaseConfig(DatabaseConfig dataBaseConfig);
    Station getStation(Integer number);
    List<Station> getListStations(String key, String value);
    void addNewStation(Station station);
    void updateStation(Station station);
    void deleteStation(Station station);
}

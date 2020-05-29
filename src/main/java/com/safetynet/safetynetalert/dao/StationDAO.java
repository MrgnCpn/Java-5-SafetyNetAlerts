package com.safetynet.safetynetalert.dao;

import com.safetynet.safetynetalert.configuration.DatabaseConfig;
import com.safetynet.safetynetalert.interfaces.StationDAOInterface;
import com.safetynet.safetynetalert.models.Station;

import java.util.List;

public class StationDAO implements StationDAOInterface {
    DatabaseConfig databaseConfig;
    List<Station> allStations;

    @Override
    public void StationDAO(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public void setDatabaseConfig(DatabaseConfig dataBaseConfig) {

    }

    @Override
    public Station getStation(Integer number) {
        return null;
    }

    @Override
    public List<Station> getListStations(String key, String value) {
        return null;
    }

    @Override
    public void addNewStation(Station station) {

    }

    @Override
    public void updateStation(Station station) {

    }

    @Override
    public void deleteStation(Station station) {

    }
}

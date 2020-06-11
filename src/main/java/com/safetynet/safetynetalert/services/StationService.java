package com.safetynet.safetynetalert.services;

import com.safetynet.safetynetalert.dao.StationDAO;
import com.safetynet.safetynetalert.interfaces.StationServiceInterface;
import com.safetynet.safetynetalert.models.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StationService implements StationServiceInterface {
    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger("StationService");

    /**
     * Fire station information
     */
    private StationDAO stationDAO;

    /**
     * Constructor
     * @param stationDAO
     */
    public StationService(StationDAO stationDAO) {
        this.stationDAO = stationDAO;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationServiceInterface {@link #httpPostStation(Integer, String)}
     */
    @Override
    public void httpPostStation(Integer stationNumber, String address) {
        stationDAO.addNewStation(new Station(stationNumber, address));
    }
    
    /**
     * @see com.safetynet.safetynetalert.interfaces.StationServiceInterface {@link #httpPutStation(Integer, String)}
     */
    @Override
    public void httpPutStation(Integer stationNumber, String address) {
        stationDAO.updateStation(new Station(stationNumber, address));
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationServiceInterface {@link #httpDeleteStation(Integer)}
     */
    @Override
    public void httpDeleteStation(Integer stationNumber) {
        stationDAO.deleteStationByNumber(stationNumber);
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationServiceInterface {@link #httpDeleteStationMapping(String)}
     */
    @Override
    public void httpDeleteStationMapping(String address) {
        stationDAO.deleteStationMapping(address);
    }
}

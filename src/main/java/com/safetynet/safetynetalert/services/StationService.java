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
     * @see com.safetynet.safetynetalert.interfaces.StationServiceInterface {@link #httpPost(Integer, String)}
     */
    @Override
    public void httpPost(Integer stationNumber, String address) {
        stationDAO.addNewStation(new Station(stationNumber, address));
    }
    
    /**
     * @see com.safetynet.safetynetalert.interfaces.StationServiceInterface {@link #httpPut(Integer, String)}
     */
    @Override
    public void httpPut(Integer stationNumber, String address) {
        stationDAO.updateStation(new Station(stationNumber, address));
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationServiceInterface {@link #httpDelete(Integer)}
     */
    @Override
    public void httpDelete(Integer stationNumber) {
        stationDAO.deleteStationByNumber(stationNumber);
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationServiceInterface {@link #httpDeleteMapping(String)}
     */
    @Override
    public void httpDeleteMapping(String address) {
        stationDAO.deleteStationMapping(address);
    }
}

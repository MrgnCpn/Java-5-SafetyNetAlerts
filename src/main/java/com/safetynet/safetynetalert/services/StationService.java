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
     * @see com.safetynet.safetynetalert.interfaces.StationServiceInterface {@link #httpPost(Station)}
     */
    @Override
    public boolean httpPost(Station newStation) {
        return stationDAO.addNewStation(newStation);
    }
    
    /**
     * @see com.safetynet.safetynetalert.interfaces.StationServiceInterface {@link #httpPut(Station)}
     */
    @Override
    public boolean httpPut(Station station) {
        return stationDAO.updateStation(station);
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationServiceInterface {@link #httpDelete(Integer)}
     */
    @Override
    public boolean httpDelete(Integer stationNumber) {
        return stationDAO.deleteStationByNumber(stationNumber);
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationServiceInterface {@link #httpDeleteMapping(String)}
     */
    @Override
    public boolean httpDeleteMapping(String address) {
        return stationDAO.deleteStationMapping(address);
    }
}

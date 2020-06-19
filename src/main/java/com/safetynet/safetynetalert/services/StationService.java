package com.safetynet.safetynetalert.services;

import com.safetynet.safetynetalert.dao.StationDAO;
import com.safetynet.safetynetalert.interfaces.StationServiceInterface;
import com.safetynet.safetynetalert.models.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Singleton;

@Singleton
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
    public String httpPost(Station newStation) {
        if (newStation != null) {
            if (stationDAO.addNewStation(newStation)) {
                logger.info("New station mapping added, number : " + newStation.getNumber() + ", address : " + newStation.getAddress());
                return "Station mapping added";
            } else {
                logger.error("Station mapping can't be added");
                return "Error : This Station mapping can't be added";
            }
        } else throw new NullPointerException();
    }
    
    /**
     * @see com.safetynet.safetynetalert.interfaces.StationServiceInterface {@link #httpPut(Station)}
     */
    @Override
    public String httpPut(Station station) {
        if (station != null) {
            if (stationDAO.updateStation(station)) {
                logger.info("Station mapping update, number : " + station.getNumber() + ", address : " + station.getAddress());
                return "Station mapping updated";
            } else {
                logger.error("Station mapping can't be updated");
                return "Error : This Station mapping can't be updated";
            }
        } else throw new NullPointerException();
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationServiceInterface {@link #httpDelete(Integer)}
     */
    @Override
    public String httpDelete(Integer stationNumber) {
        if (stationDAO.deleteStationByNumber(stationNumber)) {
            logger.info("Station n°" + stationNumber + " deleted");
            return "Station deleted";
        } else {
            logger.error("Station can't be deleted");
            return "Error : This Station can't be deleted";
        }
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.StationServiceInterface {@link #httpDeleteMapping(String)}
     */
    @Override
    public String httpDeleteMapping(String address) {
        if (stationDAO.deleteStationMapping(address)) {
            logger.info("Station mapping \"" + address + "\" deleted");
            return "Station mapping deleted";
        } else {
            logger.error("Station mapping can't be deleted");
            return "Error : This Station mapping can't be deleted";
        }
    }
}

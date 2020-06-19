package com.safetynet.safetynetalert.interfaces;

import com.safetynet.safetynetalert.models.Station;

public interface StationServiceInterface {

    /**
     * Add station from HTTP POST
     * @param newStation
     */
    boolean httpPost(Station newStation);

    /**
     * Update station from HTTP PUT
     * @param station
     */
    boolean httpPut(Station station);

    /**
     * Delete station from HTTP DELETE
     * @param stationNumber
     */
    boolean httpDelete(Integer stationNumber);

    /**
     * Delete station address from HTTP DELETE
     * @param address
     */
    boolean httpDeleteMapping(String address);
}

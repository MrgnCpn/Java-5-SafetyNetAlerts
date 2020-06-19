package com.safetynet.safetynetalert.interfaces;

import com.safetynet.safetynetalert.models.Station;

public interface StationServiceInterface {

    /**
     * Add station from HTTP POST
     * @param newStation
     */
    String httpPost(Station newStation);

    /**
     * Update station from HTTP PUT
     * @param station
     */
    String httpPut(Station station);

    /**
     * Delete station from HTTP DELETE
     * @param stationNumber
     */
    String httpDelete(Integer stationNumber);

    /**
     * Delete station address from HTTP DELETE
     * @param address
     */
    String httpDeleteMapping(String address);
}

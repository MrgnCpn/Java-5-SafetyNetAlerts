package com.safetynet.safetynetalert.interfaces;

public interface StationServiceInterface {

    /**
     * Add station from HTTP POST
     * @param stationNumber
     * @param address
     */
    void httpPostStation(Integer stationNumber, String address);

    /**
     * Update station from HTTP PUT
     * @param stationNumber
     * @param address
     */
    void httpPutStation(Integer stationNumber, String address);

    /**
     * Delete station from HTTP DELETE
     * @param stationNumber
     */
    void httpDeleteStation(Integer stationNumber);

    /**
     * Delete station address from HTTP DELETE
     * @param address
     */
    void httpDeleteStationMapping(String address);
}

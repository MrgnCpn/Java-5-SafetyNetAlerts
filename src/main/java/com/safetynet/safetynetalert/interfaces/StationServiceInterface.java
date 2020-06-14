package com.safetynet.safetynetalert.interfaces;

public interface StationServiceInterface {

    /**
     * Add station from HTTP POST
     * @param stationNumber
     * @param address
     */
    void httpPost(Integer stationNumber, String address);

    /**
     * Update station from HTTP PUT
     * @param stationNumber
     * @param address
     */
    void httpPut(Integer stationNumber, String address);

    /**
     * Delete station from HTTP DELETE
     * @param stationNumber
     */
    void httpDelete(Integer stationNumber);

    /**
     * Delete station address from HTTP DELETE
     * @param address
     */
    void httpDeleteMapping(String address);
}

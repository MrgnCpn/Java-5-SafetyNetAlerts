package com.safetynet.safetynetalert.interfaces;

import java.util.List;

public interface InformationsServicesInterface {

    /**
     * Get all persons served by the station with count of child and adults
     * @param stationNumber
     * @return String in JSON format
     */
    String getAllPersonsServedByTheStationWithCount(Integer stationNumber);

    /**
     * Get all child living at this address with all adults
     * @param address
     * @return String in JSON format
     */
    String getAllChildByAddress(String address);

    /**
     * Get all informations of persons living at this address with their served station
     * @param address
     * @return String in JSON format
     */
    String getAllPersonsLivingAtTheAddress(String address);

    /**
     * Get all persons served by the station group by address
     * @param stationNumbers
     * @return String in JSON format
     */
    String getAllPersonsServedByTheStations(String stationNumbers);

    /**
     * Get all phones of persons by station location
     * @param stationNumber
     * @return String in JSON format
     */
    String getAllPersonsPhoneByStationNumber(Integer stationNumber);

    /**
     * Get all informations of persons by name
     * @param firstName
     * @param lastName
     * @return String in JSON format
     */
    String getAllCompleteProfileOfPersonsByName(String firstName, String lastName);

    /**
     * Get all emails of persons by city
     * @param city
     * @return String in JSON format
     */
    String getAllPersonsEmailByCity(String city);
}

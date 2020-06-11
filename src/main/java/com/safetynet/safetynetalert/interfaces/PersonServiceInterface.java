package com.safetynet.safetynetalert.interfaces;

public interface PersonServiceInterface {

    /**
     * Add person from HTTP POST
     * @param firstName
     * @param lastName
     * @param address
     * @param city
     * @param zip
     * @param phone
     * @param email
     */
    void httpPostPerson(String firstName, String lastName, String address, String city, String zip, String phone, String email);

    /**
     * Update person from HTTP PUT
     * @param firstName
     * @param lastName
     * @param address
     * @param city
     * @param zip
     * @param phone
     * @param email
     */
    void httpPutPerson(String firstName, String lastName, String address, String city, String zip, String phone, String email);

    /**
     * Delete person from HTTP DELETE
     * @param firstName
     * @param lastName
     */
    void httpDeletePerson(String firstName, String lastName);
}

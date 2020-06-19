package com.safetynet.safetynetalert.interfaces;

import com.safetynet.safetynetalert.models.Person;

public interface PersonServiceInterface {

    /**
     * Add person from HTTP POST
     * @param person
     */
    boolean httpPost(Person person);

    /**
     * Update person from HTTP PUT
     * @param person
     */
    boolean httpPut(Person person);

    /**
     * Delete person from HTTP DELETE
     * @param id
     */
    boolean httpDelete(Integer id);
}

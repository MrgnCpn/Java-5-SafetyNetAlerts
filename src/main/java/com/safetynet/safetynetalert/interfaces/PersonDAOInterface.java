package com.safetynet.safetynetalert.interfaces;

import com.safetynet.safetynetalert.configuration.DatabaseConfig;
import com.safetynet.safetynetalert.models.Person;

import java.util.List;

public interface PersonDAOInterface {

    /**
     * Set DatabaseConfiguration
     * @param dataBaseConfig
     */
    void setDatabaseConfig(DatabaseConfig dataBaseConfig);

    /**
     * Get one person profile from allPersons choose by firstName and lastName
     * @param firstName
     * @param lastName
     * @return One person profile
     */
    Person getPersonByName(String firstName, String lastName);

    /**
     * Get one person profile from allPersons choose by if-d
     * @param id
     * @return One person profile
     */
    Person getPersonById(Integer id);

    /**
     *  Get all persons profiles
     * @return List of all persons
     */
    List<Person> getAllPersons();

    /**
     * Add one profile to allPersons
     * @param person
     */
    Boolean addNewPerson(Person person);

    /**
     * Update one person profile in allPersons
     * @param person
     */
    Boolean updatePerson(Person person);

    /**
     * Delete the profile of one person in allPersons
     * @param firstName
     * @param lastName
     */
    Boolean deletePerson(String firstName, String lastName);
}
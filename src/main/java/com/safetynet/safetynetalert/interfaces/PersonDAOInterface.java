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
    Person getPerson(String firstName, String lastName);

    /**
     * Get list of persons profiles choose by Key and value
     * @param key
     * @param value
     * @return List of persons profiles
     */
    List<Person> getListPersons(String key, String value);

    /**
     * Add one profile to allPersons
     * @param person
     */
    void addNewPerson(Person person);

    /**
     * Update one person profile in allPersons
     * @param firstName
     * @param lastName
     */
    void updatePerson(String firstName, String lastName);

    /**
     * Delete the profile of one person in allPersons
     * @param firstName
     * @param lastName
     */
    void deletePerson(String firstName, String lastName);
}
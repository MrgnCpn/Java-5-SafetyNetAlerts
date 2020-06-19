package com.safetynet.safetynetalert.interfaces;

import com.safetynet.safetynetalert.models.Person;

import java.util.List;

public interface PersonDAOInterface {

    /**
     * Get list of persons profile from allPersons choose by firstName and lastName
     * @param firstName
     * @param lastName
     * @return list of persons profile
     */
    List<Person> getPersonsByName(String firstName, String lastName);

    /**
     * Get list of persons profile from allPersons choose by id
     * @param id
     * @return List of persons profile
     */
    Person getPersonsById(Integer id);

    /**
     *  Get all persons profiles
     * @return List of all persons
     */
    List<Person> getAllPersons();

    /**
     * Add one profile to allPersons
     * @param person
     */
    boolean addNewPerson(Person person);

    /**
     * Update one person profile in allPersons
     * @param person
     */
    boolean updatePerson(Person person);

    /**
     * Delete the profile of one person in allPersons
     * @param id
     */
    boolean deletePerson(Integer id);
}
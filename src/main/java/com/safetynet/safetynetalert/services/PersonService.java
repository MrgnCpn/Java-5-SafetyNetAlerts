package com.safetynet.safetynetalert.services;

import com.safetynet.safetynetalert.dao.PersonDAO;
import com.safetynet.safetynetalert.interfaces.PersonServiceInterface;
import com.safetynet.safetynetalert.models.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PersonService implements PersonServiceInterface {
    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger("PersonService");

    /**
     * Persons Profiles
     */
    private PersonDAO personDAO;

    /**
     * Constructor
     * @param personDAO
     */
    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonServiceInterface {@link #httpPostPerson(String, String, String, String, String, String, String)}
     */
    @Override
    public void httpPostPerson(String firstName, String lastName, String address, String city, String zip, String phone, String email) {
        Person newPerson = new Person(personDAO.getAllPersons().size(), firstName, lastName, address, city, zip, phone, email);
        personDAO.addNewPerson(newPerson);
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonServiceInterface {@link #httpPutPerson(String, String, String, String, String, String, String)}
     */
    @Override
    public void httpPutPerson(String firstName, String lastName, String address, String city, String zip, String phone, String email) {
        personDAO.updatePerson(new Person(0, firstName, lastName, address, city, zip, phone, email));
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonServiceInterface {@link #httpDeletePerson(String, String)}
     */
    @Override
    public void httpDeletePerson(String firstName, String lastName) {
        personDAO.deletePerson(firstName, lastName);
    }
}

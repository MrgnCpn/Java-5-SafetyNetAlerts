package com.safetynet.safetynetalert.services;

import com.safetynet.safetynetalert.dao.MedicalRecordDAO;
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
     * Persons Medical Records
     */
    private MedicalRecordDAO medicalRecordDAO;

    /**
     * Constructor
     * @param personDAO
     */
    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonServiceInterface {@link #httpPost(String, String, String, String, String, String, String)}
     */
    @Override
    public void httpPost(String firstName, String lastName, String address, String city, String zip, String phone, String email) {
        Person newPerson = new Person(personDAO.getAllPersons().size(), firstName, lastName, address, city, zip, phone, email);
        personDAO.addNewPerson(newPerson);
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonServiceInterface {@link #httpPut(Integer, String, String, String, String, String, String, String)}
     */
    @Override
    public void httpPut(Integer id, String firstName, String lastName, String address, String city, String zip, String phone, String email) {
        personDAO.updatePerson(new Person(id, firstName, lastName, address, city, zip, phone, email));
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonServiceInterface {@link #httpDelete(Integer)}
     */
    @Override
    public void httpDelete(Integer id) {
        personDAO.deletePerson(id);
        medicalRecordDAO.deleteMedicalRecord(id);
    }
}

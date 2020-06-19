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
     * @see com.safetynet.safetynetalert.interfaces.PersonServiceInterface {@link #httpPost(Person)}
     */
    @Override
    public boolean httpPost(Person person) {
        person.setId(personDAO.getAllPersons().size() + 1);
        return personDAO.addNewPerson(person);
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonServiceInterface {@link #httpPut(Person)}
     */
    @Override
    public boolean httpPut(Person person) {
        return personDAO.updatePerson(person);
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonServiceInterface {@link #httpDelete(Integer)}
     */
    @Override
    public boolean httpDelete(Integer id) {
        if (medicalRecordDAO.deleteMedicalRecord(id)) {
            return personDAO.deletePerson(id);
        } else return false;
    }
}

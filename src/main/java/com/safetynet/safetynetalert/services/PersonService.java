package com.safetynet.safetynetalert.services;

import com.safetynet.safetynetalert.dao.MedicalRecordDAO;
import com.safetynet.safetynetalert.dao.PersonDAO;
import com.safetynet.safetynetalert.interfaces.PersonServiceInterface;
import com.safetynet.safetynetalert.models.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Singleton;

@Singleton
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
    public PersonService(PersonDAO personDAO, MedicalRecordDAO medicalRecordDAO) {
        this.personDAO = personDAO;
        this.medicalRecordDAO = medicalRecordDAO;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonServiceInterface {@link #httpPost(Person)}
     */
    @Override
    public String httpPost(Person newPerson) {
        if (newPerson != null) {
            newPerson.setId(personDAO.getAllPersons().size() + 1);
            if (personDAO.addNewPerson(newPerson)) {
                logger.info("New person profile added, id : " + newPerson.getId() + ", name : " + newPerson.getFirstName() + " " + newPerson.getLastName());
                return "Person added";
            } else {
                logger.error("Person can't be added");
                return "Error : This Person can't be added";
            }
        } else throw new NullPointerException();
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonServiceInterface {@link #httpPut(Person)}
     */
    @Override
    public String httpPut(Person person) {
        if (person != null) {
            if (personDAO.updatePerson(person)) {
                logger.info("Person profile n°" + person.getId() + " has been updated");
                return "Person updated";
            } else {
                logger.error("Person profile can't be updated");
                return "Error : This Person hasn't been updated";
            }
        } else throw new NullPointerException();
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonServiceInterface {@link #httpDelete(Integer)}
     */
    @Override
    public String httpDelete(Integer id) {
        String resultMsg;
        if (medicalRecordDAO.deleteMedicalRecord(id)){
            logger.info("The medical record of person n°" + id + " has been deleted");
            resultMsg = "Medical record deleted";
        } else {
            logger.error("The medical record of person n°" + id + " hasn't be deleted");
            resultMsg = "Error : The medical record of this person hasn't be deleted";
        }

        if (personDAO.deletePerson(id)) {
            logger.info("The person profile n°" + id + " has been deleted");
            resultMsg += " / Person deleted";
        } else {
            logger.error("The person profile n°" + id + " hasn't be deleted");
            resultMsg += " / Error : This Person hasn't been deleted";
        }
        return resultMsg;
    }
}

package com.safetynet.safetynetalert.dao;

import com.safetynet.safetynetalert.configuration.DatabaseConfig;
import com.safetynet.safetynetalert.interfaces.PersonDAOInterface;
import com.safetynet.safetynetalert.models.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class PersonDAO implements PersonDAOInterface {
    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger("PersonDAO");

    /**
     * Database configuration
     */
    private DatabaseConfig databaseConfig;

    /**
     * List of all persons profiles from database
     */
    private List<Person> allPersons;

    /**
     * Constructor and load data
     * @param databaseConfig
     * @throws IOException
     * @throws ParseException
     */
    public PersonDAO(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
        this.allPersons = new ArrayList<>();
        loadData();
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonDAOInterface {@link #setDatabaseConfig(DatabaseConfig)}
     */
    @Override
    public void setDatabaseConfig(DatabaseConfig dataBaseConfig) {
        this.databaseConfig = dataBaseConfig;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonDAOInterface {@link #getPersonsByName(String, String)}
     */
    @Override
    public List<Person> getPersonsByName(String firstName, String lastName) {
        List<Person> listOfPersons = new ArrayList<>();

        for (Person iPerson : allPersons) {
            if ((iPerson.getFirstName().equals(firstName))
                    && (iPerson.getLastName().equals(lastName))) {
                listOfPersons.add(iPerson);
            }
        }
        return listOfPersons;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonDAOInterface {@link #getPersonsById(Integer)}
     */
    @Override
    public Person getPersonsById(Integer id) {
        for (Person iPerson : allPersons) {
            if (iPerson.getId().equals(id)) {
                return iPerson;
            }
        }
        return null;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonDAOInterface {@link #getAllPersons()}
     */
    @Override
    public List<Person> getAllPersons() {
        return this.allPersons;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonDAOInterface {@link #addNewPerson(Person)}
     */
    @Override
    public boolean addNewPerson(Person person) {
        boolean personAdded = false;

        if (
                (person.getId() > 0)
                && !person.getFirstName().isEmpty()
                && !person.getLastName().isEmpty()
                && !person.getAddress().isEmpty()
                && !person.getCity().isEmpty()
                && !person.getZip().isEmpty()
                && !person.getEmail().isEmpty()
                && !person.getEmail().isEmpty()
                && !person.getPhone().isEmpty()) {
            this.allPersons.add(person);
            personAdded = true;
        }

        if (personAdded) {
            logger.info("Person profile has been added");
        } else {
            logger.error("Person isn't complete and could not be added");
        }

        return personAdded;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonDAOInterface {@link #updatePerson(Person)}
     */
    @Override
    public boolean updatePerson(Person person) {
        boolean personUpdated = false;

        for (Person iPerson : allPersons) {
            if ((iPerson.getFirstName().equals(person.getFirstName()))
                    && (iPerson.getLastName().equals(person.getLastName()))
                    && (iPerson.getId().equals(person.getId()))
            ) {
                iPerson.setAddress(person.getAddress());
                iPerson.setCity(person.getCity());
                iPerson.setEmail(person.getEmail());
                iPerson.setPhone(person.getPhone());
                iPerson.setZip(person.getZip());
                personUpdated = true;
                break;
            }
        }

        if (personUpdated) {
            logger.info("Person profile has been updated");
        } else {
            logger.error("Person doesn't exist in persons list and could not be updated");
        }

        return personUpdated;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonDAOInterface {@link #deletePerson(Integer)}
     */
    @Override
    public boolean deletePerson(Integer id) {
        boolean personDeleted = false;

        for (int i = 0; i < allPersons.size(); i++) {
            if (allPersons.get(i).getId().equals(id)){
                allPersons.remove(i);
                personDeleted = true;
                break;
            }
        }

        if (personDeleted) {
            logger.info("Person profile has been deleted");
        } else {
            logger.error("Person doesn't exist in persons list and could not be deleted");
        }

        return personDeleted;
    }

    /**
     * Load data in allPersons in constructor
     */
    private void loadData() {
        try {
            JSONObject data = databaseConfig.openConnection();

            JSONArray persons = (JSONArray) data.get("persons");

            for (int i = 0; i < persons.size(); i++) {
                JSONObject person = (JSONObject) persons.get(i);
                String firstName = (String) person.get("firstName");
                String lastName = (String) person.get("lastName");
                String address = (String) person.get("address");
                String city = (String) person.get("city");
                String zip = (String) person.get("zip");
                String phone = (String) person.get("phone");
                String email = (String) person.get("email");
                allPersons.add(new Person(allPersons.size() + 1, firstName, lastName, address, city, zip, phone, email));
            }

            logger.info("Persons are loaded from data");
        } catch (Exception e) {
            logger.error("Data can't be loaded in PersonDAO : " + e);
        }
        databaseConfig.closeConnection();
    }
}
package com.safetynet.safetynetalert.dao;

import com.safetynet.safetynetalert.configuration.DatabaseConfig;
import com.safetynet.safetynetalert.interfaces.PersonDAOInterface;
import com.safetynet.safetynetalert.models.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public PersonDAO(DatabaseConfig databaseConfig) throws IOException, ParseException {
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
     * @see com.safetynet.safetynetalert.interfaces.PersonDAOInterface {@link #getPersonByName(String, String)}
     */
    @Override
    public Person getPersonByName(String firstName, String lastName) {
        for (int i = 0; i < allPersons.size(); i++) {
            if ((allPersons.get(i).getFirstName().equals(firstName))
                    && (allPersons.get(i).getLastName().equals(lastName))) {
                return allPersons.get(i);
            }
        }
        return null;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonDAOInterface {@link #getPersonById(Integer)}
     */
    @Override
    public Person getPersonById(Integer id) {
        for (int i = 0; i < allPersons.size(); i++) {
            if (allPersons.get(i).getId().equals(id)) {
                return allPersons.get(i);
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
    public Boolean addNewPerson(Person person) {
        Boolean personAdded = false;

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
    public Boolean updatePerson(Person person) {
        Boolean personUpdated = false;

        for (int i = 0; i < allPersons.size(); i++) {
            if ((allPersons.get(i).getFirstName().equals(person.getFirstName()))
                    && (allPersons.get(i).getLastName().equals(person.getLastName()))
            ) {
                allPersons.get(i).setAddress(person.getAddress());
                allPersons.get(i).setCity(person.getCity());
                allPersons.get(i).setEmail(person.getEmail());
                allPersons.get(i).setPhone(person.getPhone());
                allPersons.get(i).setZip(person.getZip());
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
     * @see com.safetynet.safetynetalert.interfaces.PersonDAOInterface {@link #deletePerson(String, String)}
     */
    @Override
    public Boolean deletePerson(String firstName, String lastName) {
        Boolean personDeleted = false;

        for (int i = 0; i < allPersons.size(); i++) {
            if ((allPersons.get(i).getFirstName().equals(firstName)) && (allPersons.get(i).getLastName().equals(lastName))){
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
     * @throws IOException
     */
    private void loadData() throws IOException, ParseException {
        databaseConfig.openConnection();
        try {
            JSONObject data = databaseConfig.getData();

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

            logger.info("All persons are loaded from data");
        } catch (Exception e) {
            logger.error("Data can't be loaded in PersonDAO : " + e);
        }
        databaseConfig.closeConnection();
    }
}

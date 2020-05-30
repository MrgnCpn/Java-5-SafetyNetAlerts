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
     * @see com.safetynet.safetynetalert.interfaces.PersonDAOInterface {@link #getPerson(String, String)}
     */
    @Override
    public Person getPerson(String firstName, String lastName) {
        for (int i = 0; i < allPersons.size(); i++) {
            if ((allPersons.get(i).getFirstName().equals(firstName))
                    && (allPersons.get(i).getLastName().equals(lastName))) {
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
    public void addNewPerson(Person person) {
        this.allPersons.add(person);
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonDAOInterface {@link #updatePerson(Person)}
     */
    @Override
    public void updatePerson(Person person) {
        for (int i = 0; i < allPersons.size(); i++) {
            if ((allPersons.get(i).getFirstName().equals(person.getFirstName()))
                    && (allPersons.get(i).getLastName().equals(person.getLastName()))
            ) {
                allPersons.get(i).setAddress(person.getAddress());
                allPersons.get(i).setCity(person.getCity());
                allPersons.get(i).setEmail(person.getEmail());
                allPersons.get(i).setPhone(person.getPhone());
                allPersons.get(i).setZip(person.getZip());
                break;
            }
        }
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.PersonDAOInterface {@link #deletePerson(String, String)}
     */
    @Override
    public void deletePerson(String firstName, String lastName) {
        for (int i = 0; i < allPersons.size(); i++) {
            if ((allPersons.get(i).getFirstName().equals(firstName)) && (allPersons.get(i).getLastName().equals(lastName))){
                allPersons.remove(i);
                break;
            }
        }
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
                allPersons.add(new Person(firstName, lastName, address, city, zip, phone, email));
            }

            logger.info("All persons are loaded from data");
        } catch (Exception e) {
            logger.error("Data can't be loaded in PersonDAO : " + e);
        }
        databaseConfig.closeConnection();
    }
}

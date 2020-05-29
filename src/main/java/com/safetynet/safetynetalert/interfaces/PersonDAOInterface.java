package com.safetynet.safetynetalert.interfaces;

import com.safetynet.safetynetalert.configuration.DatabaseConfig;
import com.safetynet.safetynetalert.models.Person;

import java.util.List;

public interface PersonDAOInterface {
    void PersonDAO(DatabaseConfig databaseConfig);
    void setDatabaseConfig(DatabaseConfig dataBaseConfig);
    Person getPerson(String firstName, String lastName);
    List<Person> getListPersons(String key, String value);
    void addNewPerson(Person person);
    void updatePerson(Person person);
    void deletePerson(Person person);
}

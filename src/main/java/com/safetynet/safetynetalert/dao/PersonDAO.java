package com.safetynet.safetynetalert.dao;

import com.safetynet.safetynetalert.configuration.DatabaseConfig;
import com.safetynet.safetynetalert.interfaces.PersonDAOInterface;
import com.safetynet.safetynetalert.models.Person;

import java.util.List;

public class PersonDAO implements PersonDAOInterface {
    DatabaseConfig databaseConfig;
    List<Person> allPersons;

    @Override
    public void PersonDAO(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public void setDatabaseConfig(DatabaseConfig dataBaseConfig) {
        this.databaseConfig = dataBaseConfig;
    }

    @Override
    public Person getPerson(String firstName, String lastName) {
        return null;
    }

    @Override
    public List<Person> getListPersons(String key, String value) {
        return null;
    }

    @Override
    public void addNewPerson(Person person) {

    }

    @Override
    public void updatePerson(Person person) {

    }

    @Override
    public void deletePerson(Person person) {

    }
}

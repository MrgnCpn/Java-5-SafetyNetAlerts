package com.safetynet.safetynetalert.unit.DAO;

import com.safetynet.safetynetalert.configuration.DatabaseConfig;
import com.safetynet.safetynetalert.dao.PersonDAO;
import com.safetynet.safetynetalert.models.Person;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonDAOTest {
    private PersonDAO personDAO;

    private static StringBuilder data;

    @Mock
    private static DatabaseConfig databaseConfig;

    @BeforeAll
    static void initTestClass(){
        data = new StringBuilder();
        data.append("{\"persons\": [");
        data.append("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" },");
        data.append("{ \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6513\", \"email\":\"drk@email.com\" },");
        data.append("{ \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"tenz@email.com\" },],");
        data.append("\"firestations\": [");
        data.append("{ \"address\":\"1509 Culver St\", \"station\":\"3\" },");
        data.append("{ \"address\":\"29 15th St\", \"station\":\"2\" },");
        data.append("{ \"address\":\"834 Binoc Ave\", \"station\":\"3\" },");
        data.append("{ \"address\":\"644 Gershwin Cir\", \"station\":\"1\" },");
        data.append("{ \"address\":\"748 Townings Dr\", \"station\":\"3\" }],");
        data.append("\"medicalrecords\": [");
        data.append("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },");
        data.append("{ \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },");
        data.append("{ \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },]}");
    }

    @BeforeEach
    void initTest() throws IOException, ParseException {
        when(databaseConfig.getData()).thenReturn((JSONObject) new JSONParser().parse(this.data.toString()));
        personDAO = new PersonDAO(databaseConfig);
    }

    @Tag("PersonDAOTest")
    @Test
    void loadDataInDAOConstructor() {
        assertThat(personDAO.getAllPersons().size()).isEqualTo(3);
    }

    @Tag("PersonDAOTest")
    @Test
    void getPersonByName() {
        assertThat(personDAO.getPersonByName("Jacob", "Boyd")).isInstanceOf(Person.class);
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getId()).isEqualTo(2);
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getFirstName()).isEqualTo("Jacob");
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getLastName()).isEqualTo("Boyd");
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getAddress()).isEqualTo("1509 Culver St");
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getCity()).isEqualTo("Culver");
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getZip()).isEqualTo("97451");
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getEmail()).isEqualTo("drk@email.com");
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getPhone()).isEqualTo("841-874-6513");

        assertThat(personDAO.getPersonByName("John", "Smith")).isNull();
    }

    @Tag("PersonDAOTest")
    @Test
    void getPersonById() {
        assertThat(personDAO.getPersonById(2)).isInstanceOf(Person.class);
        assertThat(personDAO.getPersonById(2).getId()).isEqualTo(2);
        assertThat(personDAO.getPersonById(2).getFirstName()).isEqualTo("Jacob");
        assertThat(personDAO.getPersonById(2).getLastName()).isEqualTo("Boyd");
        assertThat(personDAO.getPersonById(2).getAddress()).isEqualTo("1509 Culver St");
        assertThat(personDAO.getPersonById(2).getCity()).isEqualTo("Culver");
        assertThat(personDAO.getPersonById(2).getZip()).isEqualTo("97451");
        assertThat(personDAO.getPersonById(2).getEmail()).isEqualTo("drk@email.com");
        assertThat(personDAO.getPersonById(2).getPhone()).isEqualTo("841-874-6513");

        assertThat(personDAO.getPersonById(50)).isNull();
    }

    @Tag("PersonDAOTest")
    @Test
    void getAllPersons() {
        assertThat(personDAO.getAllPersons()).isInstanceOf(List.class);
        assertThat(personDAO.getAllPersons().size()).isEqualTo(3);
        assertThat(personDAO.getAllPersons().get(0).getId()).isEqualTo(1);
        assertThat(personDAO.getAllPersons().get(0).getFirstName()).isEqualTo("John");
        assertThat(personDAO.getAllPersons().get(1).getId()).isEqualTo(2);
        assertThat(personDAO.getAllPersons().get(1).getFirstName()).isEqualTo("Jacob");
        assertThat(personDAO.getAllPersons().get(2).getId()).isEqualTo(3);
        assertThat(personDAO.getAllPersons().get(2).getFirstName()).isEqualTo("Tenley");

        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> personDAO.getAllPersons().get(-1));
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> personDAO.getAllPersons().get(3));
    }

    @Tag("PersonDAOTest")
    @Test
    void addNewPerson() {
        assertThat(personDAO.getAllPersons().size()).isEqualTo(3);

        Person newPerson = new Person(personDAO.getAllPersons().size() + 1, "Tessa", "Carman", "834 Binoc Ave", "Culver", "97451", "841-874-6512", "tenz@email.com");
        assertThat(personDAO.addNewPerson(newPerson)).isTrue();

        assertThat(personDAO.getAllPersons().size()).isEqualTo(4);
        assertThat(personDAO.getPersonByName("Tessa", "Carman")).isInstanceOf(Person.class);
        assertThat(personDAO.getPersonByName("Tessa", "Carman").getId()).isEqualTo(4);
        assertThat(personDAO.getPersonByName("Tessa", "Carman").getFirstName()).isEqualTo("Tessa");
        assertThat(personDAO.getPersonByName("Tessa", "Carman").getLastName()).isEqualTo("Carman");
        assertThat(personDAO.getPersonByName("Tessa", "Carman").getAddress()).isEqualTo("834 Binoc Ave");
        assertThat(personDAO.getPersonByName("Tessa", "Carman").getCity()).isEqualTo("Culver");
        assertThat(personDAO.getPersonByName("Tessa", "Carman").getZip()).isEqualTo("97451");
        assertThat(personDAO.getPersonByName("Tessa", "Carman").getEmail()).isEqualTo("tenz@email.com");
        assertThat(personDAO.getPersonByName("Tessa", "Carman").getPhone()).isEqualTo("841-874-6512");

        assertThat(personDAO.addNewPerson(new Person(0, "", "", "", "", "", "", ""))).isFalse();
        assertThat(personDAO.getAllPersons().size()).isEqualTo(4);
    }

    @Tag("PersonDAOTest")
    @Test
    void updatePerson() {
        assertThat(personDAO.getPersonByName("Jacob", "Boyd")).isInstanceOf(Person.class);
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getId()).isEqualTo(2);
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getFirstName()).isEqualTo("Jacob");
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getLastName()).isEqualTo("Boyd");
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getAddress()).isEqualTo("1509 Culver St");
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getCity()).isEqualTo("Culver");
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getZip()).isEqualTo("97451");
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getEmail()).isEqualTo("drk@email.com");
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getPhone()).isEqualTo("841-874-6513");

        assertThat(personDAO.getAllPersons().size()).isEqualTo(3);

        assertThat(personDAO.updatePerson(new Person(personDAO.getAllPersons().size() + 1, "Jacob", "Boyd", "834 Binoc Ave", "Culver", "97451", "841-874-6512", "tenz@email.com"))).isTrue();

        assertThat(personDAO.getPersonByName("Jacob", "Boyd")).isInstanceOf(Person.class);
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getId()).isEqualTo(2);
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getFirstName()).isEqualTo("Jacob");
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getLastName()).isEqualTo("Boyd");
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getAddress()).isEqualTo("834 Binoc Ave");
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getCity()).isEqualTo("Culver");
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getZip()).isEqualTo("97451");
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getEmail()).isEqualTo("tenz@email.com");
        assertThat(personDAO.getPersonByName("Jacob", "Boyd").getPhone()).isEqualTo("841-874-6512");

        assertThat(personDAO.getAllPersons().size()).isEqualTo(3);

        assertThat(personDAO.updatePerson(
                        new Person(personDAO.getAllPersons().size() + 1, "Tessa", "Carman", "834 Binoc Ave", "Culver", "97451", "841-874-6512", "tenz@email.com")
                )
        ).isFalse();

        assertThat(personDAO.getAllPersons().size()).isEqualTo(3);
    }

    @Tag("PersonDAOTest")
    @Test
    void deletePerson() {
        assertThat(personDAO.getAllPersons().size()).isEqualTo(3);
        assertThat(personDAO.deletePerson("Jacob", "Boyd")).isTrue();
        assertThat(personDAO.getAllPersons().size()).isEqualTo(2);
        assertThat(personDAO.deletePerson("John", "Smith")).isFalse();
    }

    @AfterEach
    void undefTest(){
        personDAO = null;
    }

    @AfterAll
    static void undefTestClass(){
        data = null;
    }
}
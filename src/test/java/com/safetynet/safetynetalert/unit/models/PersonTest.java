package com.safetynet.safetynetalert.unit.models;

import com.safetynet.safetynetalert.models.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PersonTest {
    private Person person;

    @BeforeEach
    void initTest(){
        person = new Person(1, "John", "Smith", "1509 Culver St", "Cluver", "97451", "841-874-6512", "jsmith@email.com");
    }

    @Tag("PersonTest")
    @Test
    void testGetter(){
        assertThat(person.getId()).isEqualTo(1);
        assertThat(person.getFirstName()).isEqualTo("John");
        assertThat(person.getLastName()).isEqualTo("Smith");
        assertThat(person.getAddress()).isEqualTo("1509 Culver St");
        assertThat(person.getCity()).isEqualTo("Cluver");
        assertThat(person.getZip()).isEqualTo("97451");
        assertThat(person.getPhone()).isEqualTo("841-874-6512");
        assertThat(person.getEmail()).isEqualTo("jsmith@email.com");
    }

    @Tag("PersonTest")
    @Test
    void testGetterAndSetter(){
        person.setId(10);
        person.setFirstName("Ron");
        person.setLastName("Peters");
        person.setAddress("112 Steppes Pl");
        person.setCity("Cluver");
        person.setZip("97451");
        person.setPhone("841-874-8888");
        person.setEmail("rpeters@email.com");

        assertThat(person.getId()).isEqualTo(10);
        assertThat(person.getFirstName()).isEqualTo("Ron");
        assertThat(person.getLastName()).isEqualTo("Peters");
        assertThat(person.getAddress()).isEqualTo("112 Steppes Pl");
        assertThat(person.getCity()).isEqualTo("Cluver");
        assertThat(person.getZip()).isEqualTo("97451");
        assertThat(person.getPhone()).isEqualTo("841-874-8888");
        assertThat(person.getEmail()).isEqualTo("rpeters@email.com");
    }

    @Tag("PersonTest")
    @Test
    void testSetAsNull(){
        person.setId(0);
        person.setFirstName("");
        person.setLastName("");
        person.setAddress("");
        person.setCity("");
        person.setZip("");
        person.setPhone("");
        person.setEmail("");

        assertThat(person.getId()).isZero();
        assertThat(person.getFirstName()).isEmpty();
        assertThat(person.getLastName()).isEmpty();
        assertThat(person.getAddress()).isEmpty();
        assertThat(person.getCity()).isEmpty();
        assertThat(person.getZip()).isEmpty();
        assertThat(person.getPhone()).isEmpty();
        assertThat(person.getEmail()).isEmpty();
    }

    @AfterEach
    void undefTest(){
        person = null;
    }
}
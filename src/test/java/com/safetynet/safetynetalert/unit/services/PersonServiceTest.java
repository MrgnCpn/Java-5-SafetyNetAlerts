package com.safetynet.safetynetalert.unit.services;

import com.safetynet.safetynetalert.dao.MedicalRecordDAO;
import com.safetynet.safetynetalert.dao.PersonDAO;
import com.safetynet.safetynetalert.models.Person;
import com.safetynet.safetynetalert.services.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    private Person person;
    private PersonService personService;

    @Mock
    private static PersonDAO personDAO;

    @Mock
    private static MedicalRecordDAO medicalRecordDAO;


    @BeforeEach
    void setUp() {
        person = new Person(0, "", "", "", "", "", "", "");
        personService = new PersonService(personDAO, medicalRecordDAO);
    }

    @Tag("PersonServiceTest")
    @Test
    void httpPost_nullPerson() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> personService.httpPost(null));
        verify(personDAO, never()).addNewPerson(any(Person.class));
    }

    @Tag("PersonServiceTest")
    @Test
    void httpPost_DAO_true() {
        when(personDAO.addNewPerson(person)).thenReturn(true);
        assertThat(personService.httpPost(person)).isEqualTo("Person added");
        verify(personDAO, times(1)).addNewPerson(person);
    }

    @Tag("PersonServiceTest")
    @Test
    void httpPost_DAO_false() {
        when(personDAO.addNewPerson(person)).thenReturn(false);
        assertThat(personService.httpPost(person)).isEqualTo("Error : This Person can't be added");
        verify(personDAO, times(1)).addNewPerson(person);
    }

    @Tag("PersonServiceTest")
    @Test
    void httpPut_nullPerson() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> personService.httpPut(null));
        verify(personDAO, never()).updatePerson(any(Person.class));
    }

    @Tag("PersonServiceTest")
    @Test
    void httpPut_DAO_true() {
        when(personDAO.updatePerson(person)).thenReturn(true);
        assertThat(personService.httpPut(person)).isEqualTo("Person updated");
        verify(personDAO, times(1)).updatePerson(person);
    }

    @Tag("PersonServiceTest")
    @Test
    void httpPut_DAO_false() {
        when(personDAO.updatePerson(person)).thenReturn(false);
        assertThat(personService.httpPut(person)).isEqualTo("Error : This Person hasn't been updated");
        verify(personDAO, times(1)).updatePerson(person);
    }

    @Tag("PersonServiceTest")
    @Test
    void httpDelete_personDAO_true_medicalRecordDAO_true() {
        when(personDAO.deletePerson(0)).thenReturn(true);
        when(medicalRecordDAO.deleteMedicalRecord(0)).thenReturn(true);
        assertThat(personService.httpDelete(0)).isEqualTo("Medical record deleted / Person deleted");
        verify(personDAO, times(1)).deletePerson(0);
        verify(medicalRecordDAO, times(1)).deleteMedicalRecord(0);
    }

    @Tag("PersonServiceTest")
    @Test
    void httpDelete_personDAO_true_medicalRecordDAO_false() {
        when(personDAO.deletePerson(0)).thenReturn(true);
        when(medicalRecordDAO.deleteMedicalRecord(0)).thenReturn(false);
        assertThat(personService.httpDelete(0)).isEqualTo("Error : The medical record of this person hasn't be deleted / Person deleted");
        verify(personDAO, times(1)).deletePerson(0);
        verify(medicalRecordDAO, times(1)).deleteMedicalRecord(0);
    }

    @Tag("PersonServiceTest")
    @Test
    void httpDelete_personDAO_false_medicalRecordDAO_true() {
        when(personDAO.deletePerson(0)).thenReturn(false);
        when(medicalRecordDAO.deleteMedicalRecord(0)).thenReturn(true);
        assertThat(personService.httpDelete(0)).isEqualTo("Medical record deleted / Error : This Person hasn't been deleted");
        verify(personDAO, times(1)).deletePerson(0);
        verify(medicalRecordDAO, times(1)).deleteMedicalRecord(0);
    }

    @Tag("PersonServiceTest")
    @Test
    void httpDelete_personDAO_false_medicalRecordDAO_false() {
        when(personDAO.deletePerson(0)).thenReturn(false);
        when(medicalRecordDAO.deleteMedicalRecord(0)).thenReturn(false);
        assertThat(personService.httpDelete(0)).isEqualTo("Error : The medical record of this person hasn't be deleted / Error : This Person hasn't been deleted");
        verify(personDAO, times(1)).deletePerson(0);
        verify(medicalRecordDAO, times(1)).deleteMedicalRecord(0);
    }

    @AfterEach
    void tearDown() {
        personService = null;
        person = null;
    }
}
package com.safetynet.safetynetalert.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordTest {
    private MedicalRecord medicalRecord;
    List<String> medication;
    List<String> allergies;

    @BeforeEach
    void initTest(){
        medication = new ArrayList<>();
        allergies = new ArrayList<>();

        medication.add("aznol:350mg");
        medication.add("hydrapermazol:100mg");
        allergies.add("xilliathal");

        medicalRecord = new MedicalRecord(1, "03/06/1984", medication, allergies);
    }

    @Tag("StationTest")
    @Test
    void testGetter(){
        assertThat(medicalRecord.getId()).isEqualTo(1);
        assertThat(medicalRecord.getBirthdate()).isEqualTo("03/06/1984");
        assertThat(medicalRecord.getMedications()).isEqualTo(medication);
        assertThat(medicalRecord.getAllergies()).isEqualTo(allergies);
    }

    @Tag("StationTest")
    @Test
    void testGetterAndSetter(){
        List<String> newMedication = new ArrayList<>();
        newMedication.add("dodoxadin:30mg");
        List<String> newAllergies = new ArrayList<>();
        newAllergies.add("shellfish");

        medicalRecord.setId(2);
        medicalRecord.setBirthdate("08/06/1945");
        medicalRecord.setMedications(newMedication);
        medicalRecord.setAllergies(newAllergies);

        assertThat(medicalRecord.getId()).isEqualTo(2);
        assertThat(medicalRecord.getBirthdate()).isEqualTo("08/06/1945");
        assertThat(medicalRecord.getMedications()).isEqualTo(newMedication);
        assertThat(medicalRecord.getAllergies()).isEqualTo(newAllergies);
    }

    @Tag("StationTest")
    @Test
    void testSetAsNull(){
        medicalRecord.setId(0);
        medicalRecord.setBirthdate("");
        medicalRecord.setMedications(new ArrayList<>());
        medicalRecord.setAllergies(new ArrayList<>());

        assertThat(medicalRecord.getId()).isEqualTo(0);
        assertThat(medicalRecord.getBirthdate()).isEmpty();
        assertThat(medicalRecord.getMedications()).isEmpty();
        assertThat(medicalRecord.getAllergies()).isEmpty();
    }

    @AfterEach
    void undefTest(){
        medicalRecord = null;
    }
}
package com.safetynet.safetynetalert.models;

import java.util.List;

public class MedicalRecord {
    private Integer id;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

    /**
     * Constructor
     * @param id
     * @param birthdate
     * @param medications
     * @param allergies
     */
    public MedicalRecord(Integer id, String birthdate, List<String> medications, List<String> allergies) {
        this.id = id;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }
}

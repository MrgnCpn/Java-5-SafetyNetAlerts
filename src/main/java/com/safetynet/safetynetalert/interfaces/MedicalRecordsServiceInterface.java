package com.safetynet.safetynetalert.interfaces;

import java.util.List;

public interface MedicalRecordsServiceInterface {

    /**
     * Add MedicalRecord from HTTP POST
     * @param id
     * @param birthdate
     * @param medications
     * @param allergies
     */
    void httpPost(Integer id, String birthdate, List<String> medications, List<String> allergies);

    /**
     * Update MedicalRecord from HTTP PUT
     * @param id
     * @param birthdate
     * @param medications
     * @param allergies
     */
    void httpPut(Integer id, String birthdate, List<String> medications, List<String> allergies);

    /**
     * Delete MedicalRecord from HTTP DELETE
     * @param id
     */
    void httpDelete(Integer id);
}
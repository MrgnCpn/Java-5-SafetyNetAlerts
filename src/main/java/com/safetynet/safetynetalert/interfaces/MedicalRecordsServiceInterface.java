package com.safetynet.safetynetalert.interfaces;

import com.safetynet.safetynetalert.models.MedicalRecord;

import java.util.List;

public interface MedicalRecordsServiceInterface {

    /**
     * Add MedicalRecord from HTTP POST
     * @param medicalRecord
     */
    String httpPost(MedicalRecord medicalRecord);

    /**
     * Update MedicalRecord from HTTP PUT
     * @param medicalRecord
     */
    String httpPut(MedicalRecord medicalRecord);

    /**
     * Delete MedicalRecord from HTTP DELETE
     * @param id
     */
    String httpDelete(Integer id);
}
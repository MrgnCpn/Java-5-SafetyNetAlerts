package com.safetynet.safetynetalert.interfaces;

import com.safetynet.safetynetalert.configuration.DatabaseConfig;
import com.safetynet.safetynetalert.models.MedicalRecord;

import java.util.List;

public interface MedicalRecordDAOInterface {

    /**
     * Set DatabaseConfiguration
     * @param dataBaseConfig
     */
    void setDatabaseConfig(DatabaseConfig dataBaseConfig);


    /**
     * Get one medicalRecord from allMedicalRecords choose by person id
     * @param id
     * @return One MedicalRecord
     */
    MedicalRecord getMedicalRecord(Integer id);

    /**
     * Get list of medical records choose by Key and value
     * @param key
     * @param value
     * @return List of medical records
     */
    List<MedicalRecord> getListMedicalRecords(String key, String value);


    /**
     * Add one new medical record
     * @param station
     */
    void addNewMedicalRecord(MedicalRecord station);

    /**
     * Update one medical record in allMedicalRecords
     * @param id
     */
    void updateMedicalRecord(Integer id);

    /**
     * Delete the medical record in allMedicalRecords
     * @param id
     */
    void deleteMedicalRecord(Integer id);
}

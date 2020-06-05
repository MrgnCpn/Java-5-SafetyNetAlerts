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
     *  Get all medicalRecords
     * @return List of all medicalRecords
     */
    List<MedicalRecord> getAllMedicalRecords();

    /**
     * Add one new medical record
     * @param medicalRecord
     */
    Boolean addNewMedicalRecord(MedicalRecord medicalRecord);

    /**
     * Update one medical record in allMedicalRecords
     * @param medicalRecord
     */
    Boolean updateMedicalRecord(MedicalRecord medicalRecord);

    /**
     * Delete the medical record in allMedicalRecords
     * @param id
     */
    Boolean deleteMedicalRecord(Integer id);
}

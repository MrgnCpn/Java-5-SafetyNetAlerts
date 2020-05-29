package com.safetynet.safetynetalert.interfaces;

import com.safetynet.safetynetalert.configuration.DatabaseConfig;
import com.safetynet.safetynetalert.models.MedicalRecord;

import java.util.List;

public interface MedicalRecordDAOInterface {
    void MedicalRecordDAO(DatabaseConfig databaseConfig);
    void setDatabaseConfig(DatabaseConfig dataBaseConfig);
    MedicalRecord getMedicalRecord(Integer id);
    List<MedicalRecord> getListMedicalRecords(String key, String value);
    void addNewMedicalRecord(MedicalRecord station);
    void updateMedicalRecord(MedicalRecord station);
    void deleteMedicalRecord(MedicalRecord station);
}

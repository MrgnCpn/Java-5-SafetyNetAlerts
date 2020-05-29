package com.safetynet.safetynetalert.dao;

import com.safetynet.safetynetalert.configuration.DatabaseConfig;
import com.safetynet.safetynetalert.interfaces.MedicalRecordDAOInterface;
import com.safetynet.safetynetalert.models.MedicalRecord;

import java.util.List;

public class MedicalRecordDAO implements MedicalRecordDAOInterface {
    DatabaseConfig databaseConfig;
    List<MedicalRecord> allMedicalRecords;

    @Override
    public void MedicalRecordDAO(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public void setDatabaseConfig(DatabaseConfig dataBaseConfig) {

    }

    @Override
    public MedicalRecord getMedicalRecord(Integer id) {
        return null;
    }

    @Override
    public List<MedicalRecord> getListMedicalRecords(String key, String value) {
        return null;
    }

    @Override
    public void addNewMedicalRecord(MedicalRecord station) {

    }

    @Override
    public void updateMedicalRecord(MedicalRecord station) {

    }

    @Override
    public void deleteMedicalRecord(MedicalRecord station) {

    }
}

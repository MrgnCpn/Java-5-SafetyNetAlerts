package com.safetynet.safetynetalert.dao;

import com.safetynet.safetynetalert.configuration.DatabaseConfig;
import com.safetynet.safetynetalert.interfaces.MedicalRecordDAOInterface;
import com.safetynet.safetynetalert.models.MedicalRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO implements MedicalRecordDAOInterface {
    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger("MedicalRecordDAO");

    /**
     * Database configuration
     */
    private DatabaseConfig databaseConfig;

    /**
     * List of all medical records from database
     */
    private List<MedicalRecord> allMedicalRecords;

    /**
     * Constructor and load data
     * @param databaseConfig
     * @throws IOException
     * @throws ParseException
     */
    public MedicalRecordDAO(DatabaseConfig databaseConfig) throws IOException, ParseException {
        this.databaseConfig = databaseConfig;
        this.allMedicalRecords = new ArrayList<>();
        loadData();
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordDAOInterface {@link #setDatabaseConfig(DatabaseConfig)}
     */
    @Override
    public void setDatabaseConfig(DatabaseConfig dataBaseConfig) {

    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordDAOInterface {@link #getMedicalRecord(Integer)}
     */
    @Override
    public MedicalRecord getMedicalRecord(Integer id) {
        return null;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordDAOInterface {@link #getListMedicalRecords(String, String)}
     */
    @Override
    public List<MedicalRecord> getListMedicalRecords(String key, String value) {
        return null;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordDAOInterface {@link #addNewMedicalRecord(MedicalRecord)}
     */
    @Override
    public void addNewMedicalRecord(MedicalRecord station) {

    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordDAOInterface {@link #updateMedicalRecord(Integer)}
     */
    @Override
    public void updateMedicalRecord(Integer id) {

    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordDAOInterface {@link #deleteMedicalRecord(Integer)}
     */
    @Override
    public void deleteMedicalRecord(Integer id) {

    }

    /**
     * Load data in allMedicalRecords in constructor
     * @throws IOException
     * @throws ParseException
     */
    private void loadData() throws IOException, ParseException {
        databaseConfig.openConnection();
        JSONObject data = databaseConfig.getData();

        JSONArray medicalRecords = (JSONArray) data.get("medicalrecords");

        for (int i = 0; i < medicalRecords.size(); i++) {
            JSONObject medicalRecord = (JSONObject) medicalRecords.get(i);

            String birthdate = (String) medicalRecord.get("birthdate");
            List<String> medications = (List<String>) medicalRecord.get("medications");
            List<String> allergies = (List<String>) medicalRecord.get("allergies");

            allMedicalRecords.add(new MedicalRecord(i, birthdate, medications, allergies));
        }

        logger.info("All medical records are loaded from data");
        databaseConfig.closeConnection();
    }
}

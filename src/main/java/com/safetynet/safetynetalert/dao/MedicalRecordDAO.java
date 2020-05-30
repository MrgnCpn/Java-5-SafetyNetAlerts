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
        this.databaseConfig = dataBaseConfig;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordDAOInterface {@link #getMedicalRecord(Integer)}
     */
    @Override
    public MedicalRecord getMedicalRecord(Integer id) {
        for (int i = 0; i < allMedicalRecords.size(); i++) {
            if (allMedicalRecords.get(i).getId().equals(id)) {
                return allMedicalRecords.get(i);
            }
        }
        return null;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordDAOInterface {@link #getAllMedicalRecords()}
     */
    @Override
    public List<MedicalRecord> getAllMedicalRecords() {
        return this.allMedicalRecords;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordDAOInterface {@link #addNewMedicalRecord(MedicalRecord)}
     */
    @Override
    public void addNewMedicalRecord(MedicalRecord medicalRecord) {
        this.allMedicalRecords.add(medicalRecord);
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordDAOInterface {@link #updateMedicalRecord(MedicalRecord)}
     */
    @Override
    public void updateMedicalRecord(MedicalRecord medicalRecord) {
        for (int i = 0; i < allMedicalRecords.size(); i++) {
            if (allMedicalRecords.get(i).getId().equals(medicalRecord.getId())) {
                allMedicalRecords.get(i).setBirthdate(medicalRecord.getBirthdate());
                allMedicalRecords.get(i).setMedications(medicalRecord.getMedications());
                allMedicalRecords.get(i).setAllergies(medicalRecord.getAllergies());
            }
        }
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordDAOInterface {@link #deleteMedicalRecord(Integer)}
     */
    @Override
    public void deleteMedicalRecord(Integer id) {
        for (int i = 0; i < allMedicalRecords.size(); i++) {
            if (allMedicalRecords.get(i).getId().equals(id)){
               allMedicalRecords.remove(i);
               break;
            }
        }
    }

    /**
     * Load data in allMedicalRecords in constructor
     * @throws IOException
     * @throws ParseException
     */
    private void loadData() throws IOException, ParseException {
        databaseConfig.openConnection();
        try {
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
        } catch (Exception e) {
            logger.error("Data can't be loaded in MedicalRecordDAO : " + e);
        }

        databaseConfig.closeConnection();
    }
}

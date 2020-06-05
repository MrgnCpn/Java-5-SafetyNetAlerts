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
     *
     */
    private PersonDAO personDAO;

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
    public MedicalRecordDAO(DatabaseConfig databaseConfig, PersonDAO personDAO){
        this.databaseConfig = databaseConfig;
        this.personDAO = personDAO;
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
    public Boolean addNewMedicalRecord(MedicalRecord medicalRecord) {
        Boolean medicalRecordAdded = false;

        if (personDAO.getPersonById(medicalRecord.getId()) != null) {
            this.allMedicalRecords.add(medicalRecord);
            medicalRecordAdded =  true;
        }

        if(medicalRecordAdded){
            logger.info("Medical record has been added");
        } else {
            logger.error("The medical record of person n° " + medicalRecord.getId() + ", born on " + medicalRecord.getBirthdate() + " does not correspond to an existing profile");
        }

        return medicalRecordAdded;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordDAOInterface {@link #updateMedicalRecord(MedicalRecord)}
     */
    @Override
    public Boolean updateMedicalRecord(MedicalRecord medicalRecord) {
        Boolean medicalRecordUpdated = false;

        for (int i = 0; i < allMedicalRecords.size(); i++) {
            if (allMedicalRecords.get(i).getId().equals(medicalRecord.getId())) {
                allMedicalRecords.get(i).setBirthdate(medicalRecord.getBirthdate());
                allMedicalRecords.get(i).setMedications(medicalRecord.getMedications());
                allMedicalRecords.get(i).setAllergies(medicalRecord.getAllergies());
                medicalRecordUpdated = true;
                break;
            }
        }

        if (medicalRecordUpdated) {
            logger.info("Medical record informations have been updated");
        } else {
            logger.error("Medical record doesn't exist in medical records list and could not be updated");
        }

        return medicalRecordUpdated;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordDAOInterface {@link #deleteMedicalRecord(Integer)}
     */
    @Override
    public Boolean deleteMedicalRecord(Integer id) {
        Boolean medicalRecordDeleted = false;

        for (int i = 0; i < allMedicalRecords.size(); i++) {
            if (allMedicalRecords.get(i).getId().equals(id)){
               allMedicalRecords.remove(i);
                medicalRecordDeleted = true;
               break;
            }
        }

        if (medicalRecordDeleted) {
            logger.info("Medical record have been deleted");
        } else {
            logger.error("Medical record doesn't exist in medical records list and could not be deleted");
        }

        return medicalRecordDeleted;
    }

    /**
     * Load data in allMedicalRecords in constructor
     */
    private void loadData() {
        try {
            JSONObject data = databaseConfig.openConnection();

            JSONArray medicalRecords = (JSONArray) data.get("medicalrecords");

            for (int i = 0; i < medicalRecords.size(); i++) {
                JSONObject medicalRecord = (JSONObject) medicalRecords.get(i);

                String firstName = (String) medicalRecord.get("firstName");
                String lastName = (String) medicalRecord.get("lastName");
                String birthdate = (String) medicalRecord.get("birthdate");

                if (personDAO.getPersonByName(firstName, lastName) != null) {
                    List<String> medications = (List<String>) medicalRecord.get("medications");
                    List<String> allergies = (List<String>) medicalRecord.get("allergies");

                    allMedicalRecords.add(new MedicalRecord(personDAO.getPersonByName(firstName, lastName).getId(), birthdate, medications, allergies));
                } else {
                    logger.error("The medical file of " + firstName + " " + lastName + ", born on " + birthdate + " does not correspond to an existing profile");
                }
            }
            logger.info("All medical records are loaded from data");
        } catch (Exception e) {
            logger.error("Data can't be loaded in MedicalRecordDAO : " + e);
        }

        databaseConfig.closeConnection();
    }
}

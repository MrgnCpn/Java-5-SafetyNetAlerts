package com.safetynet.safetynetalert.services;

import com.safetynet.safetynetalert.dao.MedicalRecordDAO;
import com.safetynet.safetynetalert.dao.PersonDAO;
import com.safetynet.safetynetalert.interfaces.MedicalRecordsServiceInterface;
import com.safetynet.safetynetalert.models.MedicalRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MedicalRecordsService implements MedicalRecordsServiceInterface {
    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger("MedicalRecordsService");

    /**
     * Persons profiles
     */
    private PersonDAO personDAO;

    /**
     * Persons medical records
     */
    private MedicalRecordDAO medicalRecordDAO;

    /**
     * Constructor
     * @param medicalRecordDAO
     */
    public MedicalRecordsService(MedicalRecordDAO medicalRecordDAO) {
        this.medicalRecordDAO = medicalRecordDAO;
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordsServiceInterface {@link #httpPost(Integer, String, List, List)}
     */
    @Override
    public void httpPost(Integer id, String birthdate, List<String> medications, List<String> allergies) {
        medicalRecordDAO.addNewMedicalRecord(new MedicalRecord(id, birthdate, medications, allergies));
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordsServiceInterface {@link #httpPut(Integer, String, List, List)}
     */
    @Override
    public void httpPut(Integer id, String birthdate, List<String> medications, List<String> allergies) {
        medicalRecordDAO.updateMedicalRecord(new MedicalRecord(id, birthdate, medications, allergies));
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordsServiceInterface {@link #httpDelete(Integer)}
     */
    @Override
    public void httpDelete(Integer id) {
        medicalRecordDAO.deleteMedicalRecord(id);
    }

}

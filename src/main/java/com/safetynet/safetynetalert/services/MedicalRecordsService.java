package com.safetynet.safetynetalert.services;

import com.safetynet.safetynetalert.dao.MedicalRecordDAO;
import com.safetynet.safetynetalert.dao.PersonDAO;
import com.safetynet.safetynetalert.interfaces.MedicalRecordsServiceInterface;
import com.safetynet.safetynetalert.models.MedicalRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordsServiceInterface {@link #httpPost(MedicalRecord)}
     */
    @Override
    public boolean httpPost(MedicalRecord medicalRecord) {
        return medicalRecordDAO.addNewMedicalRecord(medicalRecord);
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordsServiceInterface {@link #httpPut(MedicalRecord)}
     */
    @Override
    public boolean httpPut(MedicalRecord medicalRecord) {
        return medicalRecordDAO.updateMedicalRecord(medicalRecord);
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordsServiceInterface {@link #httpDelete(Integer)}
     */
    @Override
    public boolean httpDelete(Integer id) {
        return medicalRecordDAO.deleteMedicalRecord(id);
    }

}

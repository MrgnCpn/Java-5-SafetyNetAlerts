package com.safetynet.safetynetalert.services;

import com.safetynet.safetynetalert.dao.MedicalRecordDAO;
import com.safetynet.safetynetalert.dao.PersonDAO;
import com.safetynet.safetynetalert.interfaces.MedicalRecordsServiceInterface;
import com.safetynet.safetynetalert.models.MedicalRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

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

    public void httpPostMedicalRecord(String firstName, String lastName, String birthdate) {
        medicalRecordDAO.addNewMedicalRecord(new MedicalRecord(personDAO.getPersonsByName(firstName, lastName).get(0).getId(), birthdate, new ArrayList<>(),new ArrayList<>()));
    }

    public void httpPutMedicalRecord() {

    }

    public void httpDeleteMedicalRecord() {

    }

}

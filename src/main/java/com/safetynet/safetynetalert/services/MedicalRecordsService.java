package com.safetynet.safetynetalert.services;

import com.safetynet.safetynetalert.dao.MedicalRecordDAO;
import com.safetynet.safetynetalert.interfaces.MedicalRecordsServiceInterface;
import com.safetynet.safetynetalert.models.MedicalRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Singleton;

@Singleton
public class MedicalRecordsService implements MedicalRecordsServiceInterface {
    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger("MedicalRecordsService");

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
    public String httpPost(MedicalRecord medicalRecord) {
        if (medicalRecordDAO.addNewMedicalRecord(medicalRecord)) {
            logger.info("New medical record for person n°" + medicalRecord.getId() + " added");
            return "Medical record added";
        } else {
            logger.error("Medical record can't be added");
            return "Error : This Medical record can't be added";
        }
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordsServiceInterface {@link #httpPut(MedicalRecord)}
     */
    @Override
    public String httpPut(MedicalRecord medicalRecord) {
        if (medicalRecordDAO.updateMedicalRecord(medicalRecord)) {
            logger.info("Medical record for person n°" + medicalRecord.getId() + " updated");
            return "Medical record updated";
        } else {
            logger.error("Medical record can't be updated");
            return "Error : This Medical record can't be updated";
        }
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.MedicalRecordsServiceInterface {@link #httpDelete(Integer)}
     */
    @Override
    public String httpDelete(Integer id) {
        if (medicalRecordDAO.deleteMedicalRecord(id)) {
            logger.info("Medical record for person n°" + id + " deleted");
            return "Medical record deleted";
        } else {
            logger.error("Medical record can't be deleted");
            return "Error : This Medical record can't be deleted";
        }
    }

}

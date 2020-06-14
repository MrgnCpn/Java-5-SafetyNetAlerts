package com.safetynet.safetynetalert.controllers;

import com.safetynet.safetynetalert.services.MedicalRecordsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MedicalRecordController {
    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger("MedicalRecordController");

    /**
     * Service
     */
    private MedicalRecordsService medicalRecordsService;

    /**
     * Constructor
     * @param medicalRecordsService
     */
    public MedicalRecordController(MedicalRecordsService medicalRecordsService) {
        this.medicalRecordsService = medicalRecordsService;
    }
}

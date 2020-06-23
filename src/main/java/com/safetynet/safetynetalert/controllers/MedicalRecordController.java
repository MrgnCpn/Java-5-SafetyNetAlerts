package com.safetynet.safetynetalert.controllers;

import com.safetynet.safetynetalert.models.MedicalRecord;
import com.safetynet.safetynetalert.services.MedicalRecordsService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Singleton;

@RestController
@Singleton
public class MedicalRecordController {
    /**
     * Service
     */
    private MedicalRecordsService medicalRecordsService;

    /**
     * Begin of return message
     */
    private String startReturnMessage = "{\"message\" : \"";

    /**
     * End of return message
     */
    private String endReturnMessage = "\"}";

    /**
     * Constructor
     * @param medicalRecordsService
     */
    public MedicalRecordController(MedicalRecordsService medicalRecordsService) {
        this.medicalRecordsService = medicalRecordsService;
    }

    @PostMapping("/medicalRecord")
    public String post(@RequestBody MedicalRecord newMedicalRecord){
        return startReturnMessage + medicalRecordsService.httpPost(newMedicalRecord) + endReturnMessage;
    }

    @PutMapping("/medicalRecord")
    public String put(@RequestBody MedicalRecord medicalRecord){
        return startReturnMessage + medicalRecordsService.httpPut(medicalRecord) + endReturnMessage;
    }

    @DeleteMapping("/medicalRecord")
    public String delete(@RequestParam(required = true) Integer id){
        return startReturnMessage + medicalRecordsService.httpDelete(id) + endReturnMessage;
    }
}

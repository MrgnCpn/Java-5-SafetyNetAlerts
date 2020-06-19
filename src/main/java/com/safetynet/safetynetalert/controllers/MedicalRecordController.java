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
     * Constructor
     * @param medicalRecordsService
     */
    public MedicalRecordController(MedicalRecordsService medicalRecordsService) {
        this.medicalRecordsService = medicalRecordsService;
    }

    @PostMapping("/medicalRecord")
    public String post(@RequestBody MedicalRecord newMedicalRecord){
        return "{\"message\" : \"" + medicalRecordsService.httpPost(newMedicalRecord) + "\"}";
    }

    @PutMapping("/medicalRecord")
    public String put(@RequestBody MedicalRecord medicalRecord){
        return "{\"message\" : \"" + medicalRecordsService.httpPut(medicalRecord) + "\"}";
    }

    @DeleteMapping("/medicalRecord")
    public String delete(@RequestParam(required = true) Integer id){
        return "{\"message\" : \"" + medicalRecordsService.httpDelete(id) + "\"}";
    }
}

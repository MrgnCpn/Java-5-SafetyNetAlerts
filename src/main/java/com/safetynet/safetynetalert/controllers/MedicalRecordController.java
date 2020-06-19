package com.safetynet.safetynetalert.controllers;

import com.safetynet.safetynetalert.models.MedicalRecord;
import com.safetynet.safetynetalert.services.MedicalRecordsService;
import org.springframework.web.bind.annotation.*;

@RestController
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
        if (medicalRecordsService.httpPost(newMedicalRecord)) return "{\"message\" : \"Medical record added\"}";
        else return "{\"message\" : \"error\"}";
    }

    @PutMapping("/medicalRecord")
    public String put(@RequestBody MedicalRecord medicalRecord){
        if (medicalRecordsService.httpPut(medicalRecord)) return "{\"message\" : \"Medical record updated\"}";
        else return "{\"message\" : \"error\"}";
    }

    @DeleteMapping("/medicalRecord")
    public String delete(@RequestBody Integer id){
        if (medicalRecordsService.httpDelete(id)) return "{\"message\" : \"Medical record deleted\"}";
        else return "{\"message\" : \"error\"}";
    }
}

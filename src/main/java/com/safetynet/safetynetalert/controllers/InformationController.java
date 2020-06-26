package com.safetynet.safetynetalert.controllers;

import com.safetynet.safetynetalert.services.InformationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Singleton;

@RestController
@Singleton
public class InformationController {
    /**
     * Service
     */
    private InformationService informationService;

    /**
     * Constructor
     * @param informationService
     */
    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @GetMapping("/person")
    public String person(){
        return informationService.getAllPersons();
    }

    @GetMapping("/firestations")
    public String firestations(){
        return informationService.getAllFirestations();
    }

    @GetMapping("/medicalRecords")
    public String medicalRecords(){
        return informationService.getAllMedicalRecords();
    }

    @GetMapping("/firestation")
    public String fireStation(@RequestParam(required = false) Integer stationNumber){
        return informationService.getAllPersonsServedByTheStationWithCount(stationNumber);
    }

    @GetMapping("/childAlert")
    public String childAlert(@RequestParam(required = false) String address){
        return informationService.getAllChildByAddress(address);
    }

    @GetMapping("/phoneAlert")
    public String phoneAlert(@RequestParam(required = false) Integer firestation){
        return informationService.getAllPersonsPhoneByStationNumber(firestation);
    }

    @GetMapping("/fire")
    public String fire(@RequestParam(required = false) String address){
        return informationService.getAllPersonsLivingAtTheAddress(address);
    }

    @GetMapping("/flood/stations")
    public String flood(@RequestParam(required = false) String stations){
        return informationService.getAllPersonsServedByTheStations(stations);
    }

    @GetMapping("/personInfo")
    public String personInfo(@RequestParam(required = false) String firstName, String lastName){
        return informationService.getAllCompleteProfileOfPersonsByName(firstName, lastName);
    }

    @GetMapping("/communityEmail")
    public String communityEmail(@RequestParam(required = false) String city) {
        return informationService.getAllPersonsEmailByCity(city);
    }
}

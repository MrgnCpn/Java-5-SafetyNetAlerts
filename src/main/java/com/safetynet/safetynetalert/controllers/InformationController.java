package com.safetynet.safetynetalert.controllers;

import com.safetynet.safetynetalert.services.InformationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class InformationController {
    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger("InformationController");

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

    @GetMapping("/firestation")
    @ResponseBody
    public String firestation(@RequestParam(required = false) Integer stationNumber){
        return informationService.getAllPersonsServedByTheStationWithCount(stationNumber);
    }

    @GetMapping("/childAlert")
    @ResponseBody
    public String childAlert(@RequestParam(required = false) String address){
        return informationService.getAllChildByAddress(address);
    }

    @GetMapping("/phoneAlert")
    @ResponseBody
    public String phoneAlert(@RequestParam(required = false) Integer stationNumber){
        return informationService.getAllPersonsPhoneByStationNumber(stationNumber);
    }

    @GetMapping("/fire")
    @ResponseBody
    public String fire(@RequestParam(required = false) String address){
        return informationService.getAllPersonsLivingAtTheAddress(address);
    }

    @GetMapping("/flood/stations")
    @ResponseBody
    public String flood(@RequestParam(required = false) String stations){
        List<Integer> listOfStations = new ArrayList<>();
        for(String s : Arrays.asList(stations.split("/"))) listOfStations.add(Integer.valueOf(s));
        return informationService.getAllPersonsServedByTheStations(listOfStations);
    }

    @GetMapping("/personInfo")
    @ResponseBody
    public String personInfo(@RequestParam(required = false) String firstName, String lastName){
        return informationService.getAllCompleteProfileOfPersonsByName(firstName, lastName);
    }

    @GetMapping("/communityEmail")
    @ResponseBody
    public String communityEmail(@RequestParam(required = false) String city) {
        return informationService.getAllPersonsEmailByCity(city);
    }
}

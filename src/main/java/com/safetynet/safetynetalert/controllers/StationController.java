package com.safetynet.safetynetalert.controllers;

import com.safetynet.safetynetalert.models.Station;
import com.safetynet.safetynetalert.services.StationService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Singleton;

@RestController
@Singleton
public class StationController {
    /**
     * Service
     */
    private StationService stationService;

    /**
     * Constructor
     * @param stationService
     */
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping("/firestation")
    public String post(@RequestBody Station newStation){
        return "{\"message\" : \"" + stationService.httpPost(newStation) + "\"}";
    }

    @PutMapping("/firestation")
    public String put(@RequestBody Station station){
        return "{\"message\" : \"" + stationService.httpPut(station) + "\"}";
    }

    @DeleteMapping("/firestation/address/{address}")
    public String deleteMapping(@PathVariable String address){
        return "{\"message\" : \"" + stationService.httpDeleteMapping(address) + "\"}";
    }

    @DeleteMapping("/firestation/stationNumber/{number}")
    public String deleteStation(@PathVariable Integer number){
        return "{\"message\" : \"" + stationService.httpDelete(number) + "\"}";
    }
}

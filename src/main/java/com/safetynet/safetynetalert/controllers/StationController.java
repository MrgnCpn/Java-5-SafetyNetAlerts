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

    @DeleteMapping("/firestation")
    public String delete(@RequestBody Station station){
        if (station.getAddress() != null) {
            return "{\"message\" : \"" + stationService.httpDeleteMapping(station.getAddress()) + "\"}";
        } else if (station.getNumber() != null) {
            return "{\"message\" : \"" + stationService.httpDelete(station.getNumber()) + "\"}";
        } else {
            return "{\"message\" : \"Error\"}";
        }
    }
}

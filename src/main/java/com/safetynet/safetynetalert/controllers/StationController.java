package com.safetynet.safetynetalert.controllers;

import com.safetynet.safetynetalert.models.Station;
import com.safetynet.safetynetalert.services.StationService;
import org.springframework.web.bind.annotation.*;

@RestController
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
        if (stationService.httpPost(newStation)) return "{\"message\" : \"Station added\"}";
        else return "{\"message\" : \"error\"}";
    }

    @PutMapping("/firestation")
    public String put(@RequestBody Station station){
        if (stationService.httpPut(station)) return "{\"message\" : \"Station updated\"}";
        else return "{\"message\" : \"error\"}";
    }

    @DeleteMapping("/firestation")
    public String delete(@RequestBody Station station){
        if (station.getAddress() != null) {
            stationService.httpDeleteMapping(station.getAddress());
            return "{\"message\" : \"Station mapping deleted\"}";
        } else if (station.getNumber() != null) {
            stationService.httpDelete(station.getNumber());
            return "{\"message\" : \"Station deleted\"}";
        } else {
            return "{\"message\" : \"error\"}";
        }
    }
}

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
     * Begin of return message
     */
    private String startReturnMessage = "{\"message\" : \"";

    /**
     * End of return message
     */
    private String endReturnMessage = "\"}";

    /**
     * Constructor
     * @param stationService
     */
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping("/firestation")
    public String post(@RequestBody Station newStation){
        return startReturnMessage + stationService.httpPost(newStation) + endReturnMessage;
    }

    @PutMapping("/firestation")
    public String put(@RequestBody Station station){
        return startReturnMessage + stationService.httpPut(station) + endReturnMessage;
    }

    @DeleteMapping("/firestation")
    public String delete(@RequestParam(required = true) Integer number, String address){
        if (address != null && !address.equals("")) return startReturnMessage + stationService.httpDeleteMapping(address) + endReturnMessage;
        else if (number != null) return startReturnMessage + stationService.httpDelete(number) + endReturnMessage;
        else throw new NullPointerException();
    }
}

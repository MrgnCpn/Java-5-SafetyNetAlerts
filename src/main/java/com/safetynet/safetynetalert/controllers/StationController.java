package com.safetynet.safetynetalert.controllers;

import com.safetynet.safetynetalert.services.StationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StationController {
    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger("StationController");

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
}

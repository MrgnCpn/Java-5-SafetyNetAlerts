package com.safetynet.safetynetalert.controllers;

import com.safetynet.safetynetalert.services.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PersonController {
    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger("PersonController");

    /**
     * Service
     */
    private PersonService personService;

    /**
     * Constructor
     * @param personService
     */
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
}

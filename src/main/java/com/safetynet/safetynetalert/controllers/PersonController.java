package com.safetynet.safetynetalert.controllers;

import com.safetynet.safetynetalert.models.Person;
import com.safetynet.safetynetalert.services.PersonService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Singleton;

@RestController
@Singleton
public class PersonController {
    /**
     * Service
     */
    private PersonService personService;

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
     * @param personService
     */
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/person")
    public String post(@RequestBody Person newPerson) {
        return startReturnMessage + personService.httpPost(newPerson) + endReturnMessage;
    }

    @PutMapping("/person")
    public String put(@RequestBody Person person){
        return startReturnMessage + personService.httpPut(person) + endReturnMessage;
    }

    @DeleteMapping("/person")
    public String delete(@RequestParam(required = true) Integer id){
        return startReturnMessage + personService.httpDelete(id) + endReturnMessage;
    }
}



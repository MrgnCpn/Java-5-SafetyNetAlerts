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
     * Constructor
     * @param personService
     */
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/person")
    public String post(@RequestBody Person newPerson) {
        return "{\"message\" : \"" + personService.httpPost(newPerson) + "\"}";
    }

    @PutMapping("/person")
    public String put(@RequestBody Person person){
        return "{\"message\" : \"" + personService.httpPut(person) + "\"}";
    }

    @DeleteMapping("/person")
    public String delete(@RequestParam(required = true) Integer id){
        return "{\"message\" : \"" + personService.httpDelete(id) + "\"}";
    }
}



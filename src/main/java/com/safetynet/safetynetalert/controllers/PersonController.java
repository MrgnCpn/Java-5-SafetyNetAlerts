package com.safetynet.safetynetalert.controllers;

import com.safetynet.safetynetalert.models.Person;
import com.safetynet.safetynetalert.services.PersonService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
    public String post(@RequestBody Person newPerson){
        if (personService.httpPost(newPerson)) return "{\"message\" : \"Person added\"}";
        else return "{\"message\" : \"error\"}";
    }

    @PutMapping("/person")
    public String put(@RequestBody Person person){
        if (personService.httpPut(person)) return "{\"message\" : \"Person updated\"}";
        else return "{\"message\" : \"error\"}";
    }

    @DeleteMapping("/person")
    public String delete(@RequestBody Integer id){
        if (personService.httpDelete(id)) return "{\"message\" : \"Person deleted\"}";
        else return "{\"message\" : \"error\"}";
    }
}



package com.safetynet.safetynetalert.configuration;

import com.safetynet.safetynetalert.dao.MedicalRecordDAO;
import com.safetynet.safetynetalert.dao.PersonDAO;
import com.safetynet.safetynetalert.dao.StationDAO;
import com.safetynet.safetynetalert.services.InformationService;
import com.safetynet.safetynetalert.services.MedicalRecordsService;
import com.safetynet.safetynetalert.services.PersonService;
import com.safetynet.safetynetalert.services.StationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    private DatabaseConfig databaseConfig = new DatabaseConfig();

    @Bean
    public PersonDAO personDAO(){
        return new PersonDAO(databaseConfig);
    }

    @Bean
    public StationDAO stationDAO(){
        return new StationDAO(databaseConfig);
    }

    @Bean
    public MedicalRecordDAO medicalRecordDAO(){
        return new MedicalRecordDAO(databaseConfig, personDAO());
    }

    @Bean
    public PersonService personService(){
        return new PersonService(personDAO(), medicalRecordDAO());
    }

    @Bean
    public StationService stationService(){
        return new StationService(stationDAO());
    }

    @Bean
    public MedicalRecordsService medicalRecordsService(){
        return new MedicalRecordsService(medicalRecordDAO());
    }

    @Bean
    public InformationService informationService(){
        return new InformationService(personDAO(), stationDAO(), medicalRecordDAO());
    }
}
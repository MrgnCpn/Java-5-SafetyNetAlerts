package com.safetynet.safetynetalert.unit.services;

import com.safetynet.safetynetalert.configuration.DatabaseConfig;
import com.safetynet.safetynetalert.dao.MedicalRecordDAO;
import com.safetynet.safetynetalert.dao.PersonDAO;
import com.safetynet.safetynetalert.dao.StationDAO;
import com.safetynet.safetynetalert.services.InformationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class InformationServiceTest {

    private InformationService informationService;

    @BeforeEach
    void initTests(){
        DatabaseConfig databaseConfig = new DatabaseConfig();
        PersonDAO personDAO = new PersonDAO(databaseConfig);
        StationDAO stationDAO = new StationDAO(databaseConfig);
        MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO(databaseConfig, personDAO);
        informationService = new InformationService(personDAO, stationDAO, medicalRecordDAO);
    }

    @Test
    void runIt() {

        System.out.println(informationService.getAllPersonsEmailByCity("Culver"));
        System.out.println(informationService.getAllCompleteProfileOfPersonsByName("Jacob", "Boyd"));
        System.out.println(informationService.getAllPersonsPhoneByStationNumber(1));
        System.out.println(informationService.getAllPersonsServedByTheStationWithCount(3));
        System.out.println(informationService.getAllChildByAddress("1509 Culver St"));
        System.out.println(informationService.getAllPersonsLivingAtTheAddress("1509 Culver St"));


        List<Integer> num = new ArrayList<>();
        num.add(3);
        num.add(4);
        System.out.println(informationService.getAllPersonsServedByTheStations(num));

    }
}
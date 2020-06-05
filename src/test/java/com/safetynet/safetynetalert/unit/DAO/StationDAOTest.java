package com.safetynet.safetynetalert.unit.DAO;

import com.safetynet.safetynetalert.configuration.DatabaseConfig;
import com.safetynet.safetynetalert.dao.StationDAO;
import com.safetynet.safetynetalert.models.Person;
import com.safetynet.safetynetalert.models.Station;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StationDAOTest {
    private StationDAO stationDAO;

    private static StringBuilder data;

    @Mock
    private static DatabaseConfig databaseConfig;

    @BeforeAll
    static void initTestClass(){
        data = new StringBuilder();
        data.append("{\"persons\": [");
        data.append("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" },");
        data.append("{ \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6513\", \"email\":\"drk@email.com\" },");
        data.append("{ \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"tenz@email.com\" },],");
        data.append("\"firestations\": [");
        data.append("{ \"address\":\"1509 Culver St\", \"station\":\"3\" },");
        data.append("{ \"address\":\"29 15th St\", \"station\":\"2\" },");
        data.append("{ \"address\":\"834 Binoc Ave\", \"station\":\"3\" },");
        data.append("{ \"address\":\"644 Gershwin Cir\", \"station\":\"1\" },");
        data.append("{ \"address\":\"748 Townings Dr\", \"station\":\"3\" }],");
        data.append("\"medicalrecords\": [");
        data.append("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },");
        data.append("{ \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },");
        data.append("{ \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },]}");
    }

    @BeforeEach
    void initTest() throws IOException, ParseException {
        when(databaseConfig.getData()).thenReturn((JSONObject) new JSONParser().parse(data.toString()));
        stationDAO  = new StationDAO(databaseConfig);
    }

    @Tag("StationDAOTest")
    @Test
    void LoadDataInDAOConstructor(){
        assertThat(stationDAO.getAllStations().size()).isEqualTo(5);
    }

    @Tag("StationDAOTest")
    @Test
    void getStationByNumber() {
        assertThat(stationDAO.getStationByNumber(3)).isInstanceOf(List.class);
        assertThat(stationDAO.getStationByNumber(3).size()).isEqualTo(3);
        assertThat(stationDAO.getStationByNumber(3).get(0).getAddress()).isEqualTo("1509 Culver St");
        assertThat(stationDAO.getStationByNumber(3).get(1).getAddress()).isEqualTo("834 Binoc Ave");
        assertThat(stationDAO.getStationByNumber(4).size()).isEqualTo(0);
    }

    @Tag("StationDAOTest")
    @Test
    void getStationByAddress() {
        assertThat(stationDAO.getStationByAddress("1509 Culver St")).isInstanceOf(Station.class);
        assertThat(stationDAO.getStationByAddress("1509 Culver St").getNumber()).isEqualTo(3);

        assertThat(stationDAO.getStationByAddress("951 LoneTree Rd")).isNull();
    }

    @Tag("StationDAOTest")
    @Test
    void getAllStations() {
        assertThat(stationDAO.getAllStations()).isInstanceOf(List.class);
        assertThat(stationDAO.getAllStations().size()).isEqualTo(5);
        assertThat(stationDAO.getAllStations().get(0).getNumber()).isEqualTo(3);
        assertThat(stationDAO.getAllStations().get(0).getAddress()).isEqualTo("1509 Culver St");

        assertThat(stationDAO.getAllStations().get(1).getNumber()).isEqualTo(2);
        assertThat(stationDAO.getAllStations().get(1).getAddress()).isEqualTo("29 15th St");

        assertThat(stationDAO.getAllStations().get(2).getNumber()).isEqualTo(3);
        assertThat(stationDAO.getAllStations().get(2).getAddress()).isEqualTo("834 Binoc Ave");

        assertThat(stationDAO.getAllStations().get(3).getNumber()).isEqualTo(1);
        assertThat(stationDAO.getAllStations().get(3).getAddress()).isEqualTo("644 Gershwin Cir");

        assertThat(stationDAO.getAllStations().get(4).getNumber()).isEqualTo(3);
        assertThat(stationDAO.getAllStations().get(4).getAddress()).isEqualTo("748 Townings Dr");


        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> stationDAO.getAllStations().get(-1));
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> stationDAO.getAllStations().get(5));
    }

    @Tag("StationDAOTest")
    @Test
    void addNewStation() {
        assertThat(stationDAO.getAllStations().size()).isEqualTo(5);

        assertThat(stationDAO.addNewStation(new Station(0, ""))).isFalse();

        assertThat(stationDAO.getAllStations().size()).isEqualTo(5);

        assertThat(stationDAO.addNewStation(new Station(99, "951 LoneTree Rd"))).isTrue();

        assertThat(stationDAO.getAllStations().size()).isEqualTo(6);
        assertThat(stationDAO.getStationByAddress("951 LoneTree Rd").getNumber()).isEqualTo(99);
    }

    @Tag("StationDAOTest")
    @Test
    void updateStation() {
        assertThat(stationDAO.getStationByAddress("834 Binoc Ave")).isInstanceOf(Station.class);
        assertThat(stationDAO.getStationByAddress("834 Binoc Ave").getNumber()).isEqualTo(3);

        assertThat(stationDAO.getAllStations().size()).isEqualTo(5);

        stationDAO.updateStation(new Station(12, "834 Binoc Ave"));

        assertThat(stationDAO.getAllStations().size()).isEqualTo(5);

        assertThat(stationDAO.getStationByAddress("834 Binoc Ave").getNumber()).isEqualTo(12);
    }


    @Tag("StationDAOTest")
    @Test
    void deleteStationByAddress() {
        assertThat(stationDAO.getAllStations().size()).isEqualTo(5);
        stationDAO.deleteStationByAddress("1509 Culver St");
        assertThat(stationDAO.getAllStations().size()).isEqualTo(4);
        assertThat(stationDAO.deleteStationByAddress("1509 Culver St")).isFalse();

    }
}
package com.safetynet.safetynetalert.unit.models;

import com.safetynet.safetynetalert.models.Station;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class StationTest {
    private Station station;

    @BeforeEach
    void initTest(){
        station = new Station(1, "1509 Culver St");
    }

    @Tag("StationTest")
    @Test
    void testGetter(){
        assertThat(station.getNumber()).isEqualTo(1);
        assertThat(station.getAddress()).isEqualTo("1509 Culver St");
    }

    @Tag("StationTest")
    @Test
    void testGetterAndSetter(){
        station.setNumber(10);
        station.setAddress("748 Townings Dr");

        assertThat(station.getNumber()).isEqualTo(10);
        assertThat(station.getAddress()).isEqualTo("748 Townings Dr");
    }

    @Tag("StationTest")
    @Test
    void testSetAsNull(){
        station.setNumber(0);
        station.setAddress("");

        assertThat(station.getNumber()).isEqualTo(0);
        assertThat(station.getAddress()).isEmpty();
    }

    @AfterEach
    void undefTest(){
        station = null;
    }
}
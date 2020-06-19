package com.safetynet.safetynetalert.unit.services;

import com.safetynet.safetynetalert.dao.StationDAO;
import com.safetynet.safetynetalert.models.Station;
import com.safetynet.safetynetalert.services.StationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StationServiceTest {
    private Station station;
    private StationService stationService;

    @Mock
    private static StationDAO stationDAO;

    @BeforeEach
    void setUp() {
        station = new Station(0,"");
        stationService = new StationService(stationDAO);
    }

    @Tag("StationServiceTest")
    @Test
    void httpPost_nullStation(){
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> stationService.httpPost(null));
        verify(stationDAO, never()).addNewStation(any(Station.class));
    }

    @Tag("StationServiceTest")
    @Test
    void httpPost_DAO_true() {
        when(stationDAO.addNewStation(station)).thenReturn(true);
        assertThat(stationService.httpPost(station)).isEqualTo("Station mapping added");
        verify(stationDAO, times(1)).addNewStation(station);
    }

    @Tag("StationServiceTest")
    @Test
    void httpPost_DAO_false() {
        when(stationDAO.addNewStation(station)).thenReturn(false);
        assertThat(stationService.httpPost(station)).isEqualTo("Error : This Station mapping can't be added");
        verify(stationDAO, times(1)).addNewStation(station);
    }

    @Tag("StationServiceTest")
    @Test
    void httpPut_nullStation() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> stationService.httpPut(null));
        verify(stationDAO, never()).updateStation(any(Station.class));
    }

    @Tag("StationServiceTest")
    @Test
    void httpPut_DAO_true() {
        when(stationDAO.updateStation(station)).thenReturn(true);
        assertThat(stationService.httpPut(station)).isEqualTo("Station mapping updated");
        verify(stationDAO, times(1)).updateStation(station);
    }

    @Tag("StationServiceTest")
    @Test
    void httpPut_DAO_false() {
        when(stationDAO.updateStation(station)).thenReturn(false);
        assertThat(stationService.httpPut(station)).isEqualTo("Error : This Station mapping can't be updated");
        verify(stationDAO, times(1)).updateStation(station);
    }

    @Tag("StationServiceTest")
    @Test
    void httpDelete_DAO_true() {
        when(stationDAO.deleteStationByNumber(0)).thenReturn(true);
        assertThat(stationService.httpDelete(0)).isEqualTo("Station deleted");
        verify(stationDAO, times(1)).deleteStationByNumber(0);
    }

    @Tag("StationServiceTest")
    @Test
    void httpDelete_DAO_false() {
        when(stationDAO.deleteStationByNumber(0)).thenReturn(false);
        assertThat(stationService.httpDelete(0)).isEqualTo("Error : This Station can't be deleted");
        verify(stationDAO, times(1)).deleteStationByNumber(0);
    }

    @Tag("StationServiceTest")
    @Test
    void httpDeleteMapping_DAO_true() {
        when(stationDAO.deleteStationMapping("")).thenReturn(true);
        assertThat(stationService.httpDeleteMapping("")).isEqualTo("Station mapping deleted");
        verify(stationDAO, times(1)).deleteStationMapping("");
    }

    @Tag("StationServiceTest")
    @Test
    void httpDeleteMapping_DAO_false() {
        when(stationDAO.deleteStationMapping("")).thenReturn(false);
        assertThat(stationService.httpDeleteMapping("")).isEqualTo("Error : This Station mapping can't be deleted");
        verify(stationDAO, times(1)).deleteStationMapping("");
    }

    @AfterEach
    void tearDown() {
        station = null;
        stationService = null;
    }
}
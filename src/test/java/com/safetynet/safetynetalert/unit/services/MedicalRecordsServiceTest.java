package com.safetynet.safetynetalert.unit.services;

import com.safetynet.safetynetalert.dao.MedicalRecordDAO;
import com.safetynet.safetynetalert.models.MedicalRecord;
import com.safetynet.safetynetalert.services.MedicalRecordsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class MedicalRecordsServiceTest {
    private MedicalRecord medicalRecord;
    private MedicalRecordsService medicalRecordsService;

    @Mock
    private static MedicalRecordDAO medicalRecordDAO;

    @BeforeEach
    void setUp() {
        medicalRecord = new MedicalRecord(0, "", new ArrayList<>(), new ArrayList<>());
        medicalRecordsService = new MedicalRecordsService(medicalRecordDAO);
    }

    @Tag("MedicalRecordsService")
    @Test
    void httpPost_nullMedicalRecord() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> medicalRecordsService.httpPost(null));
        verify(medicalRecordDAO, never()).addNewMedicalRecord(any(MedicalRecord.class));
    }

    @Tag("MedicalRecordsService")
    @Test
    void httpPost_DAO_true() {
        when(medicalRecordDAO.addNewMedicalRecord(medicalRecord)).thenReturn(true);
        assertThat(medicalRecordsService.httpPost(medicalRecord)).isEqualTo("Medical record added");
        verify(medicalRecordDAO, times(1)).addNewMedicalRecord(medicalRecord);
    }

    @Tag("MedicalRecordsService")
    @Test
    void httpPost_DAO_false() {
        when(medicalRecordDAO.addNewMedicalRecord(medicalRecord)).thenReturn(false);
        assertThat(medicalRecordsService.httpPost(medicalRecord)).isEqualTo("Error : This Medical record can't be added");
        verify(medicalRecordDAO, times(1)).addNewMedicalRecord(medicalRecord);
    }

    @Tag("MedicalRecordsService")
    @Test
    void httpPut_nullMedicalRecord() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> medicalRecordsService.httpPut(null));
        verify(medicalRecordDAO, never()).updateMedicalRecord(any(MedicalRecord.class));
    }

    @Tag("MedicalRecordsService")
    @Test
    void httpPut_DAO_true() {
        when(medicalRecordDAO.updateMedicalRecord(medicalRecord)).thenReturn(true);
        assertThat(medicalRecordsService.httpPut(medicalRecord)).isEqualTo("Medical record updated");
        verify(medicalRecordDAO, times(1)).updateMedicalRecord(medicalRecord);
    }

    @Tag("MedicalRecordsService")
    @Test
    void httpPut_DAO_false() {
        when(medicalRecordDAO.updateMedicalRecord(medicalRecord)).thenReturn(false);
        assertThat(medicalRecordsService.httpPut(medicalRecord)).isEqualTo("Error : This Medical record can't be updated");
        verify(medicalRecordDAO, times(1)).updateMedicalRecord(medicalRecord);
    }

    @Tag("MedicalRecordsService")
    @Test
    void httpDelete_DAO_true() {
        when(medicalRecordDAO.deleteMedicalRecord(0)).thenReturn(true);
        assertThat(medicalRecordsService.httpDelete(0)).isEqualTo("Medical record deleted");
        verify(medicalRecordDAO, times(1)).deleteMedicalRecord(0);
    }

    @Tag("MedicalRecordsService")
    @Test
    void httpDelete_DAO_false() {
        when(medicalRecordDAO.deleteMedicalRecord(0)).thenReturn(false);
        assertThat(medicalRecordsService.httpDelete(0)).isEqualTo("Error : This Medical record can't be deleted");
        verify(medicalRecordDAO, times(1)).deleteMedicalRecord(0);
    }

    @AfterEach
    void tearDown() {
        medicalRecord = null;
        medicalRecordsService = null;
    }
}
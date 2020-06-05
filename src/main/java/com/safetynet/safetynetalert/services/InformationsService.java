package com.safetynet.safetynetalert.services;

import com.safetynet.safetynetalert.dao.MedicalRecordDAO;
import com.safetynet.safetynetalert.dao.PersonDAO;
import com.safetynet.safetynetalert.dao.StationDAO;
import com.safetynet.safetynetalert.interfaces.InformationsServicesInterface;
import com.safetynet.safetynetalert.models.MedicalRecord;
import com.safetynet.safetynetalert.models.Person;
import com.safetynet.safetynetalert.models.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class InformationsService implements InformationsServicesInterface {
    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger("InformationsService");

    /**
     * Persons Profiles
     */
    private PersonDAO personDAO;

    /**
     * Firestation informations
     */
    private StationDAO stationDAO;

    /**
     * Persons medical records
     */
    private MedicalRecordDAO medicalRecordDAO;

    /**
     * Return String data
     */
    private StringBuilder data;

    /**
     * Constructor
     */
    public InformationsService(PersonDAO personDAO, StationDAO stationDAO, MedicalRecordDAO medicalRecordDAO) {
        this.personDAO = personDAO;
        this.stationDAO = stationDAO;
        this.medicalRecordDAO = medicalRecordDAO;
        data = new StringBuilder();
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.InformationsServicesInterface {@link #getAllPersonsServedByTheStationWithCount(Integer)}
     */
    @Override
    public String getAllPersonsServedByTheStationWithCount(Integer stationNumber) {
        int childCount = 0;
        int adultCount = 0;
        clearData();
        data.append("{\"station\" : \"" + stationNumber + "\",");
        data.append("\"persons\" : [");

        for (int i = 0; i < this.stationDAO.getAllStations().size(); i++) {
            if (this.stationDAO.getAllStations().get(i).getNumber().equals(stationNumber)) {
                for (int j = 0; j < this.personDAO.getAllPersons().size(); j++) {
                    if (this.personDAO.getAllPersons().get(j).getAddress().equals(this.stationDAO.getAllStations().get(i).getAddress())) {
                        data.append("{");
                        data.append("\"firstName\" : \"" + this.personDAO.getAllPersons().get(j).getFirstName() + "\",");
                        data.append("\"lastName\" : \"" + this.personDAO.getAllPersons().get(j).getLastName() + "\",");
                        data.append("\"address\" : \"" + this.personDAO.getAllPersons().get(j).getAddress() + "\",");
                        data.append("\"phone\" : \"" + this.personDAO.getAllPersons().get(j).getPhone() + "\"");

                        if (medicalRecordDAO.getMedicalRecord(this.personDAO.getAllPersons().get(j).getId()).getAge() <= 18) {
                            childCount ++;
                        } else adultCount ++;

                        data.append("},");
                    }
                }
            }
        }
        deleteLastComma();
        data.append("],");

        data.append("\"adultCount\" : " + adultCount + ",");
        data.append("\"childCount\" : " + childCount);
        data.append("}");
        return data.toString();
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.InformationsServicesInterface {@link #getAllChildByAddress(String)}
     */
    @Override
    public String getAllChildByAddress(String address) {
        int childCount = 0;
        clearData();

        data.append("{\"address\" : \"" + address + "\",");
        data.append("\"childs\" : [");
        for (int i = 0; i < this.personDAO.getAllPersons().size(); i++) {
            if (
                    (medicalRecordDAO.getMedicalRecord(this.personDAO.getAllPersons().get(i).getId()).getAge() <= 18)
                    && (this.personDAO.getAllPersons().get(i).getAddress().equals(address))
            ) {
                data.append("{");
                data.append("\"firstName\" : \"" + this.personDAO.getAllPersons().get(i).getFirstName() + "\",");
                data.append("\"lastName\" : \"" + this.personDAO.getAllPersons().get(i).getLastName() + "\",");
                data.append("\"age\" : \"" + medicalRecordDAO.getMedicalRecord(this.personDAO.getAllPersons().get(i).getId()).getAge() + "\"");
                data.append("},");
                childCount ++;
            }
        }
        deleteLastComma();
        data.append("], \"adults\" : [");
        for (int i = 0; i < this.personDAO.getAllPersons().size(); i++) {
            if (
                    (medicalRecordDAO.getMedicalRecord(this.personDAO.getAllPersons().get(i).getId()).getAge() > 18)
                            && (this.personDAO.getAllPersons().get(i).getAddress().equals(address))
            ) {
                data.append("{");
                data.append("\"firstName\" : \"" + this.personDAO.getAllPersons().get(i).getFirstName() + "\",");
                data.append("\"lastName\" : \"" + this.personDAO.getAllPersons().get(i).getLastName() + "\"");
                data.append("},");
            }
        }
        deleteLastComma();
        data.append("]}");


        if (childCount == 0) {
            clearData();
        }
        return data.toString();
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.InformationsServicesInterface {@link #getAllPersonsPhoneByStationNumber(Integer)}
     */
    @Override
    public String getAllPersonsPhoneByStationNumber(Integer stationNumber) {
        clearData();
        data.append("{\"station\": \"" + stationNumber + "\",");
        data.append("\"phones\" : [");

        for (int i = 0; i < this.stationDAO.getAllStations().size(); i++) {
            if (this.stationDAO.getAllStations().get(i).getNumber().equals(stationNumber)) {
                for (int j = 0; j < this.personDAO.getAllPersons().size(); j++) {
                    if (this.personDAO.getAllPersons().get(j).getAddress().equals(this.stationDAO.getAllStations().get(i).getAddress())) {
                        data.append("\"" + this.personDAO.getAllPersons().get(j).getPhone() + "\",");
                    }
                }
            }
        }
        deleteLastComma();
        data.append("]}");

        return data.toString();
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.InformationsServicesInterface {@link #getAllPersonsLivingAtTheAddress(String)}
     */
    @Override
    public String getAllPersonsLivingAtTheAddress(String address) {
        clearData();
        List<Person> listPersons = this.personDAO.getAllPersons();
        MedicalRecord personMedicalRecords;

        data.append("{\"address\" : \"" + address + "\",");
        data.append("\"station\": \"" + stationDAO.getStationByAddress(address).getNumber() + "\",");
        data.append("\"persons\" : [");
        for (int i = 0; i < listPersons.size(); i++) {
            personMedicalRecords = medicalRecordDAO.getMedicalRecord(listPersons.get(i).getId());

            if (listPersons.get(i).getAddress().equals(address)) {
                data.append("{");
                data.append("\"firstName\" : \"" + listPersons.get(i).getFirstName() + "\",");
                data.append("\"lastName\" : \"" + listPersons.get(i).getLastName() + "\",");
                data.append("\"phone\" : \"" + listPersons.get(i).getPhone() + "\",");
                data.append("\"birthdate\" : \"" + personMedicalRecords.getBirthdate() + "\",");
                data.append("\"age\" : \"" + personMedicalRecords.getAge() + "\",");

                data.append("\"medicalRecords\" : {");
                data.append("\"medications\" : [");
                for (int j = 0; j < personMedicalRecords.getMedications().size(); j++) {
                    data.append("\"" + personMedicalRecords.getMedications().get(j) + "\",");
                }
                deleteLastComma();
                data.append("], \"allergies\" : [");
                for (int j = 0; j < personMedicalRecords.getAllergies().size(); j++) {
                    data.append("\"" + personMedicalRecords.getAllergies().get(j) + "\",");
                }
                deleteLastComma();
                data.append("]}},");
            }
        }
        deleteLastComma();
        data.append("]}");

        return data.toString();
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.InformationsServicesInterface {@link #getAllPersonsServedByTheStations(List<Integer>)}
     */
    @Override
    public String getAllPersonsServedByTheStations(List<Integer> stationNumbers) {
        clearData();

        data.append("{\"station\" : [");
        for (int i = 0; i < stationNumbers.size(); i++) {
            List<Station> listStation = stationDAO.getStationByNumber(stationNumbers.get(i));

            data.append("{\"number\" : " + stationNumbers.get(i) + ",");
            data.append("\"homes\" : [");
            for (int j = 0; j < listStation.size(); j++) {
                data.append("{ \"address\" : \"" + listStation.get(j).getAddress() + "\",");

                List<Person> listPersons = this.personDAO.getAllPersons();
                MedicalRecord personMedicalRecords;

                data.append("\"persons\" : [");
                for (int k = 0; k < listPersons.size(); k++) {
                    personMedicalRecords = medicalRecordDAO.getMedicalRecord(listPersons.get(k).getId());

                    if (listPersons.get(k).getAddress().equals(listStation.get(j).getAddress())) {
                        data.append("{");
                        data.append("\"firstName\" : \"" + listPersons.get(k).getFirstName() + "\",");
                        data.append("\"lastName\" : \"" + listPersons.get(k).getLastName() + "\",");
                        data.append("\"phone\" : \"" + listPersons.get(k).getPhone() + "\",");
                        data.append("\"birthdate\" : \"" + personMedicalRecords.getBirthdate() + "\",");
                        data.append("\"age\" : \"" + personMedicalRecords.getAge() + "\",");

                        data.append("\"medicalRecords\" : {");
                        data.append("\"medications\" : [");
                        for (int l = 0; l < personMedicalRecords.getMedications().size(); l++) {
                            data.append("\"" + personMedicalRecords.getMedications().get(l) + "\",");
                        }
                        deleteLastComma();
                        data.append("], \"allergies\" : [");
                        for (int l = 0; l < personMedicalRecords.getAllergies().size(); l++) {
                            data.append("\"" + personMedicalRecords.getAllergies().get(l) + "\",");
                        }
                        deleteLastComma();
                        data.append("]}},");
                    }
                }
                deleteLastComma();
                data.append("]},");
            }
            deleteLastComma();
            data.append("]},");
        }
        deleteLastComma();
        data.append("]}");

        return data.toString();
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.InformationsServicesInterface {@link #getAllCompleteProfileOfPersonsByName(String, String)}
     */
    @Override
    public String getAllCompleteProfileOfPersonsByName(String firstName, String lastName) {
        clearData();
        List<Person> listPersons = this.personDAO.getAllPersons();
        MedicalRecord personMedicalRecords;

        data.append("{\"persons\" : [");
        for (int i = 0; i < listPersons.size(); i++) {
            personMedicalRecords = medicalRecordDAO.getMedicalRecord(listPersons.get(i).getId());

            if (listPersons.get(i).getFirstName().equals(firstName) && listPersons.get(i).getLastName().equals(lastName)) {
                data.append("{");
                data.append("\"firstName\" : \"" + listPersons.get(i).getFirstName() + "\",");
                data.append("\"lastName\" : \"" + listPersons.get(i).getLastName() + "\",");
                data.append("\"address\" : \"" + listPersons.get(i).getAddress() + "\",");
                data.append("\"city\" : \"" + listPersons.get(i).getCity() + "\",");
                data.append("\"zip\" : \"" + listPersons.get(i).getZip() + "\",");
                data.append("\"email\" : \"" + listPersons.get(i).getEmail() + "\",");
                data.append("\"phone\" : \"" + listPersons.get(i).getPhone() + "\",");
                data.append("\"birthdate\" : \"" + personMedicalRecords.getBirthdate() + "\",");
                data.append("\"age\" : \"" + personMedicalRecords.getAge() + "\",");

                data.append("\"medicalRecords\" : {");
                data.append("\"medications\" : [");
                for (int j = 0; j < personMedicalRecords.getMedications().size(); j++) {
                    data.append("\"" + personMedicalRecords.getMedications().get(j) + "\",");
                }
                deleteLastComma();
                data.append("], \"allergies\" : [");
                for (int j = 0; j < personMedicalRecords.getAllergies().size(); j++) {
                    data.append("\"" + personMedicalRecords.getAllergies().get(j) + "\",");
                }
                deleteLastComma();
                data.append("]}},");
            }
        }
        deleteLastComma();
        data.append("]}");

        return data.toString();
    }

    /**
     * @see com.safetynet.safetynetalert.interfaces.InformationsServicesInterface {@link #getAllPersonsEmailByCity(String)}
     */
    @Override
    public String getAllPersonsEmailByCity(String city) {
        clearData();
        data.append("{\"emails\" : [");

        for (int i = 0; i < this.personDAO.getAllPersons().size(); i++) {
            if (this.personDAO.getAllPersons().get(i).getCity().equals(city)) {
                data.append("\"" + this.personDAO.getAllPersons().get(i).getEmail() + "\",");
            }
        }
        deleteLastComma();
        data.append("]}");

        return data.toString();
    }

    /**
     * Data clear
     */
    private void clearData() {
        data.delete(0, data.length());
    }

    /**
     * Delete last comma ',' from data
     */
    private void deleteLastComma(){
        if (data.charAt(data.length() - 1) == ',') data.delete(data.length() - 1, data.length());
    }

}

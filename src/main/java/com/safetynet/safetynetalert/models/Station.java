package com.safetynet.safetynetalert.models;

public class Station {
    private Integer number;
    private String address;

    public Station(Integer number, String address) {
        this.number = number;
        this.address = address;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

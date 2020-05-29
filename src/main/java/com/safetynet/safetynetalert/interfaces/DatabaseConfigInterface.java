package com.safetynet.safetynetalert.interfaces;

public interface DatabaseConfigInterface {
    void openConnection();
    String getData();
    void closeConnection();
}

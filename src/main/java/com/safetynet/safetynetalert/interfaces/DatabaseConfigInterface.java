package com.safetynet.safetynetalert.interfaces;


import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface DatabaseConfigInterface {

    /**
     * Read data.json and cast it in JSONObject data
     * @throws IOException
     * @throws ParseException
     */
    void openConnection() throws IOException, ParseException;

    /**
     * Getter data
     * @return data : JSoNObject
     */
    JSONObject getData();

    /**
     * Set data as null
     */
    void closeConnection();
}

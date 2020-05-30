package com.safetynet.safetynetalert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafetyNetAlertApplication {
	private static final Logger logger = LogManager.getLogger("SafetyNetAlertApplication");

	public static void main(String[] args) {
		logger.info("Application start");
		SpringApplication.run(SafetyNetAlertApplication.class, args);
		logger.info("Application running");





/*

		// parsing file "JSONExample.json"
		JSONObject data = (JSONObject) new JSONParser().parse(new FileReader("data.json"));

		System.out.println(data);

		// getting firstName and lastName
		System.out.println((String) data.get("firstName"));
		System.out.println((String) data.get("lastName"));

		// getting age
		System.out.println(data.get("age"));

		// getting address
		Map address = ((Map)data.get("address"));

		// iterating address Map
		Iterator<Map.Entry> itr1 = address.entrySet().iterator();
		while (itr1.hasNext()) {
			Map.Entry pair = itr1.next();
			System.out.println(pair.getKey() + " : " + pair.getValue());
		}

		// getting phoneNumbers
		JSONArray ja = (JSONArray) data.get("phoneNumbers");

		// iterating phoneNumbers
		Iterator itr2 = ja.iterator();

		while (itr2.hasNext())
		{
			itr1 = ((Map) itr2.next()).entrySet().iterator();
			while (itr1.hasNext()) {
				Map.Entry pair = itr1.next();
				System.out.println(pair.getKey() + " : " + pair.getValue());
			}
		}
*/







	}
}

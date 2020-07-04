# OC : Parcours Java / Project 5 ( SafetyNet Alerts )
##### Author : **_MorganCpn_**

## Description
Backend REST API server for SafetyNet Alert apps

## Configuration

	- Java 11
	- Maven 4.0.0
	- Spring Boot 2.3.0
	
## Install Project

1. Import JAVA project in IDE
2. Run `mvn clean site` to generate tests, coverage, and report site
3. Change server port in /src/main/resources/application.properties (Default : server.port=8080)
**WARNING : if you change server port and use the POSTMAN import file you must change every API urls**
4. Import in POSTMAN the import file : /PostmanImportFile
5. Run app : `./mvnw spring-boot:run`

## Running App

`./mvnw spring-boot:run`

## Testing

`mvn test`

## Logging File

`/logs/SafetyNetAlert.log`

## URLS
	* GET : http://localhost:8080/person
		Return list of all persons
		
	* GET : http://localhost:8080/firestations
		Return list of all stations
		
	* GET : http://localhost:8080/medicalRecords
		Return list of all medical records
		
	* GET : http://localhost:8080/firestation?stationNumber=
		Return list of all persons served by the station with count of child and adults
		
	* GET : http://localhost:8080/childAlert?address=
		Return list of all child living at this address with all adults
		
	* GET : http://localhost:8080/phoneAlert?firestation=
		Return list of all phones of persons by station location
		
	* GET : http://localhost:8080/fire?address=
		Return list of all informations of persons living at this address with their served station
		
	* GET : http://localhost:8080/flood/stations?stations= (List of stations number split by '/' ex : 1/2/3)
		Return list of all persons served by the station group by address
		
	* GET : http://localhost:8080/personInfo?firstName=&lastName=
		Return list of all informations of persons by name
		
	* GET : http://localhost:8080/communityEmail?city=
		Return list of all emails of persons by city

## ENDPOINTS
### ACTUATOR (GET)
	* INFO       : http://localhost:8080/actuator/info
	* HEALTH     : http://localhost:8080/actuator/health
	* BEANS      : http://localhost:8080/actuator/beans
	* METRICS    : http://localhost:8080/actuator/metrics
	* ENV        : http://localhost:8080/actuator/env
	* HTTP TRACE : http://localhost:8080/actuator/httptrace
	
#### PERSONS :
	* POST : http://localhost:8080:persons
		{
		    "id" : 0, // Default
		    "firstName" : "James",
		    "lastName" : "Bond",
		    "address" : "Vauxhall Cross",
		    "city" : "London",
		    "zip" : "SW8",
		    "email" : "007@mi6.com",
		    "phone" : "+447987654321"
		}
		
	* PUT : http://localhost:8080/person
		{
		    "id" : 24,
		    "firstName" : "James",
		    "lastName" : "Bond",
		    "address" : "Central Intelligence Agency",
		    "city" : "Washington, D.C",
		    "zip" : "20505",
		    "email" : "007@cia.com",
		    "phone" : "+1555123456"
		}
	
	* DELETE : http://localhost:8080/person?id=24

#### STATIONS :
	* POST : http://localhost:8080/firestation
		{
		    "number": 5,
		    "address" : "Vauxhall Cross"
		}
		
	* PUT : http://localhost:8080/firestation
		{
		    "number": 30,
		    "address" : "Vauxhall Cross"
		}
		
	* DELETE (Station mapping) : http://localhost:8080/firestation?number=30&address=Vauxhall Cross
	* DELETE (All mapping of station by number): http://localhost:8080/firestation?number=3

#### MEDICAL RECORDS :

	* POST : http://localhost:8080/medicalRecord
		{
		    "id": 24,
		    "birthdate": "11/16/1924",
		    "medications": [],
		    "allergies": []
		}	

	* PUT : http://localhost:8080/medicalRecord
		{
		    "id": 24,
		    "birthdate": "11/16/2006",
		    "medications": [],
		    "allergies": []
		}	

	* DELETE : http://localhost:8080/medicalRecord?id=24
	


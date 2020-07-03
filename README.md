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
5. Run app : `$ ./mvnw spring-boot:run`

## Running App

`$ ./mvnw spring-boot:run`

## Testing

`$ mvn test`

## Logging File

`/logs/SafetyNetAlert.log`

## URLS

* 
*
* 
*
* 
*

## ENDPOINTS

# PERSONS :
	* POST : http://localhost:8080:persons
		{
			"id" : 0, // Default 0
			"firstName" : "",
		}
	* PUT : 
	* DELETE : 

# STATIONS :
	* POST : 
	* PUT : 
	* DELETE : 
	* DELETE : 

# MEDICAL RECORDS

	* POST : 
	* PUT : 
	* DELETE :

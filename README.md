# couchbase-rest

mvn clean package

### To run the web proxy :

mvn spring-boot:run

### Web proxy parameters :

By default Spring Boot use the port 8080

For Couchbase configuration, you can modify the file src/main/java/resources/applications.properties

* storage.host=10.112.163.101 (couchbase ip adress)
* storage.bucket=beer-sample
* storage.username=Administrator
* storage.password=couchbase

Querie for finding Airport by Id :

select META(`travel-sample`).id from `travel-sample` where type="airport";

Queries for finding Airport Fly Paths :

First retreive airport name for the departure and arrival airports to Federal Aviation Administration (FAA) codes:

SELECT faa AS fromAirport FROM `travel-sample` WHERE airportname = "Los Angeles Intl"
  UNION SELECT faa AS toAirport FROM `travel-sample` WHERE airportname = "San Francisco Intl"
  
Retreive the result set of available flight paths that connect the two airports:  
  
SELECT a.name, s.flight, s.utc, r.sourceairport, r.destinationairport, r.equipment
  FROM `travel-sample` AS r
  UNNEST r.schedule AS s
  JOIN `travel-sample` AS a ON KEYS r.airlineid
  WHERE r.sourceairport = "LAX" AND r.destinationairport = "SFO" AND s.day = 6
  ORDER BY a.name ASC

  
REst for FTS :

Retreive the hotels that seems to be central in the description

http://127.0.0.1:8080/api/hotels/description/central/
# CloudAssignment2
REST API

**Project Description**
The project is about performing CRUD operations on *Daily.csv* to:

1. Read all the weather details using GET 
1. Read the details of a particular date using GET
1. Add using POST
1. Delete the details of a particular date using DELETE
1. Forecast the weather of upto 7 days for a given date using GET

**Platform/Technologies Used**
* MySQL to import the data of *Daily.csv* into database and perform the operations on the data
* Servlets to implement the Dao and service methods
* REST API to call the methods in HTTP

**Procedure Followed**

1.Created a database and imported the data from *Daily.csv*
1.Used MVC architecture to implement the operations.
  * Model is implemented using POJO class(Weather.java,WeatherDate.java)
  * Service and Dao methods are used to represent the controller
1.The implementation logic for each method is written in WeatherDao.java and the end points are in WeatherService.java


**Deployment**

1. EC2 instance is launched and dynamic DNS is generated
1. The web application is now hosted through the dynamic DNS


#Implementation

**Used REST API to get weather details and forecast future weather**

1. *Retrieving All Weather Dates*
----
  Returns json array containing all dates in weather data.

* *URL*

  http://sindhuravaranasi.hopto.org:8080/WeatherAPI/rest/api/historical

* *Method:*

  `GET`
  
* *Output Parameters:*

  JSONArray[JSONObject{"DATE":Date String(yyyyMMdd)}]
  
* *Success Response:*

  * *Code:* 200 <br />
    *Content:* `[{"DATE":"20130101"},{"DATE":"20130102"},{"DATE":"20130103"},{"DATE":"20130104"},{"DATE":"20130105"},{"DATE":"20130106"},{"DATE":"20130107"},{"DATE":"20130108"}...}]`
 
* *Error Response:*

  * *Code:* 404 NOT FOUND <br />
  

2. *Retrieving Weather Details for a given date*
----
  Returns json array containing all dates in weather data.

* *URL*

  http://sindhuravaranasi.hopto.org:8080/WeatherAPI/rest/api/historical/{DATE(yyyyMMdd)}
  
* *SAMPLE URL*

  http://sindhuravaranasi.hopto.org:8080/WeatherAPI/rest/api/historical/20150902

* *Method:*

  `GET`

* *Input Parameters:*

  Pass Date as a path paramter as shown in above URL
  
* *Output Parameters:*

  JSONObject{"DATE":Date String(yyyyMMdd), "TMAX": Double, "TMIN": Double}
  
* *Success Response:*

  * *Code:* 200 <br />
    *Content:* `{"DATE":"20150902","TMAX":90.0,"TMIN":65.0}`
 
* *Error Response:*

  * *Code:* 404 NOT FOUND <br />
  
3. *Adding weather details for a given date into the database*
----
  Returns json object containing the date whose data is added. Pass the input parameters in the body, in JSON format.

* *URL*

  http://sindhuravaranasi.hopto.org:8080/WeatherAPI/rest/api/historical
  
* *SAMPLE URL*

  http://sindhuravaranasi.hopto.org:8080/WeatherAPI/rest/api/historical

* *Method:*

  `POST`

* *Input Parameters:*

  JSONObject{"DATE":Date String(yyyyMMdd), "TMAX": Double, "TMIN": Double}
  
* *Sample Input Parameters:*

  {"DATE": "20170211", "TMAX": 60.5, "TMIN": 55.3}  
  
* *Output Parameters:*

  JSONObject{"DATE":Date String(yyyyMMdd)}
  
* *Success Response:*

  * *Code:* 201 <br />
    *Content:* `{"DATE": "20170211"}`
 
* *Error Response:*

  * *Code:* 400 Bad Request <br />
  
    
4. *Deleting Weather details for a given date*
----
  Returns json object containing date whose data is deleted.

* *URL*

  http://sindhuravaranasi.hopto.org:8080/WeatherAPI/rest/api/historical/{DATE(yyyyMMdd)}
  
* *SAMPLE URL*

  http://sindhuravaranasi.hopto.org:8080/WeatherAPI/rest/api/historical/20170211

* *Method:*

  `DELETE`

* *Input Parameters:*

  Pass Date as a path paramter as shown in above URL
  
* *Output Parameters:*

  * Success/Error Code
  
* *Success Response:*

  * *Code:* 204 No Content <br />
  
 
* *Error Response:*

  * *Code:* 404 NOT FOUND <br />
  
  
5.*To Forecast 7 days of Weather from Given Date*
----
  Returns json array containing json objects of predicted weather data

* *URL*

  http://sindhuravaranasi.hopto.org:8080/WeatherAPI/rest/api/forecast/{DATE(yyyyMMdd)}
  
* *SAMPLE URL*

  http://sindhuravaranasi.hopto.org:8080/WeatherAPI/rest/api/forecast/20170302

* *Method:*

  `GET`

* *Input Parameters:*

  Pass Date as a path paramter as shown in above URL
  
* *Output Parameters:*

  JSONArray[JSONObject{"DATE":Date String(yyyyMMdd), "TMAX": Double, "TMIN": Double}]
  
* *Success Response:*

  * *Code:* 200 <br />
  * *Content:* `[
  {
    "DATE": "20170302",
    "TMAX": 43,
    "TMIN": 32
  },
  {
    "DATE": "20170303",
    "TMAX": 37,
    "TMIN": 27
  },
  {
    "DATE": "20170304",
    "TMAX": 41,
    "TMIN": 26
  },
  {
    "DATE": "20170305",
    "TMAX": 64,
    "TMIN": 29
  },
  {
    "DATE": "20170306",
    "TMAX": 56,
    "TMIN": 50
  },
  {
    "DATE": "20170307",
    "TMAX": 58,
    "TMIN": 43
  },
  {
    "DATE": "20170308",
    "TMAX": 59,
    "TMIN": 39
  }
]`
 
* *Error Response:*

  * *Code:* 400 BAD REQUEST <br />



# CloudAssignment2
REST API

**Project Description**
The project is about performing CRUD operations on *Daily.csv* to:

1. Read all the weather details using GET 
1. Read the details of a particular date using GET
1. Add/update using POST
1. Delete the details of a particular date using DELETE

**Platform/Technologies Used**
* MySQL to import the data of *Daily.csv* into database and perform the operations on the data
* Servlets to implement the Dao and service methods

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

  http://varanasisindhura.tk:8080/Weather/rest/historical

* *Method:*

  `GET`
  
* *Output Parameters:*

  JSONArray[JSONObject{"DATE":Date String(yyyyMMdd)}]
  
* *Success Response:*

  * *Code:* 200 <br />
    *Content:* `[{"dATE":"20130101"},{"dATE":"20130102"}]`
 
* *Error Response:*

  * *Code:* 404 NOT FOUND <br />
  

2. *Retrieving Weather Details for a given date*
----
  Returns json array containing all dates in weather data.

* *URL*

  http://varanasisindhura.tk:8080/Weather/rest/historical/{DATE(yyyyMMdd)}
  
* *SAMPLE URL*

  http://varanasisindhura.tk:8080/Weather/rest/historical/20130302

* *Method:*

  `GET`

* *Input Parameters:*

  Pass Date as a path paramter as shown in above URL
  
* *Output Parameters:*

  JSONObject{"DATE":Date String(yyyyMMdd), "TMAX": Double, "TMIN": Double}
  
* *Success Response:*

  * *Code:* 200 <br />
    *Content:* `{"dATE":"20130302","tMAX":30.5,"tMIN":26.5}`
 
* *Error Response:*

  * *Code:* 404 NOT FOUND <br />
  
3. *Adding weather details for a given date into the database*
----
  Returns json object containing the date whose data is added.

* *URL*

  http://varanasisindhura.tk:8080/Weather/rest/historical/{DATE(yyyyMMdd)}
  
* *SAMPLE URL*

  http://varanasisindhura.tk:8080/Weather/rest/historical

* *Method:*

  `POST`

* *Input Parameters:*

  JSONObject{"DATE":Date String(yyyyMMdd), "TMAX": Double, "TMIN": Double}
  
* *Sample Input Parameters:*

  {   "DATE": "20140311", "TMAX": 60.5, "TMIN": 55.3 }  
  
* *Output Parameters:*

  JSONObject{"DATE":Date String(yyyyMMdd)}
  
* *Success Response:*

  * *Code:* 201 <br />
    *Content:* `{"DATE": "20140311"}`
 
* *Error Response:*

  * *Code:* 500 INTERNAL SERVER ERROR <br />
    *Content:* `{"Error": "Unparseable date"}`
    
4. *Deleting Weather details for a given date*
----
  Returns json object containing date whose data is deleted.

* *URL*

  http://varanasisindhura.tk:8080/Weather/rest/historical/{DATE(yyyyMMdd)}
  
* *SAMPLE URL*

  http://varanasisindhura.tk:8080/Weather/rest/historical/20150712

* *Method:*

  `DELETE`

* *Input Parameters:*

  Pass Date as a path paramter as shown in above URL
  
* *Output Parameters:*

  JSONObject{"DATE":Date String(yyyyMMdd)}
  
* *Success Response:*

  * *Code:* 200 <br />
  * *Content:* `{   "DATE": "20130101" }`
 
* *Error Response:*

  * *Code:* 404 NOT FOUND <br />
  * *Content:* `{   "Response": "Deletion Not Successful" }`
  
5.*To Forecast 7 days of Weather from Given Date*
----
  Returns json array containing json objects of predicted weather data

* *URL*

  http://varanasisindhura.tk:8080/Weather/rest/historical/forecast/{DATE(yyyyMMdd)}
  
* *SAMPLE URL*

  http://varanasisindhura.tk:8080/Weather/rest/historical/forecast/20120712

* *Method:*

  `GET`

* *Input Parameters:*

  Pass Date as a path paramter as shown in above URL
  
* *Output Parameters:*

  JSONArray[JSONObject{"DATE":Date String(yyyyMMdd), "TMAX": Double, "TMIN": Double}]
  
* *Success Response:*

  * *Code:* 200 <br />
  * *Content:* `[{
    "dATE": "20120712",
    "tMAX": 87.0,
    "tMIN": 66.0
}, {
    "dATE": "20120713",
    "tMAX": 82.0,
    "tMIN": 69.0
}, {
    "dATE": "20120714",
    "tMAX": 82.0,
    "tMIN": 71.0
}, {
    "dATE": "20120715",
    "tMAX": 87.0,
    "tMIN": 70.0
}, {
    "dATE": "20120716",
    "tMAX": 93.0,
    "tMIN": 71.0
}, {
    "dATE": "20120717",
    "tMAX": 95.0,
    "tMIN": 74.0
}, {
    "dATE": "20120718",
    "tMAX": 90.0,
    "tMIN": 72.0
}]`
 
* *Error Response:*

  * *Code:* 400 BAD REQUEST <br />



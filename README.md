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

**Implementation**

1.Created a database and imported the data from *Daily.csv*
1.Used MVC architecture to implement the operations.
  * Model is implemented using POJO class(Weather.java,WeatherDate.java)
  * Service and Dao methods are used to represent the controller
1.The implementation logic for each method is written in WeatherDao.java and the end points are in WeatherService.java


**Deployment**

1. EC2 instance is launched and dynamic DNS is generated
1. The web application is now hosted through the dynamic DNS


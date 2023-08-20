**Overview**

Mail tracker implement a REST API that allows you to track mail.
The system register postal items - letters, parcels - their movement between post offices, and the possibility of obtaining information and the entire history of the movement of a particular postal item.

**Prerequisites**

* Maven
* JDK 17

**Configuration**

We are using spring boot so all of the setting are in application.properties file.

There are no need any configuration to run the project.

**Test**

For test the application use below command in mail-tracker path:
```
mvn test
```

**Code Coverage Report**

You can use below command to generate code coverage report:
```
mvn jacoco:report
```
So you can find full report in target/site/index.html path

**Deploy**
To run the project use below command in mail-tracker path:
```
mvn clean 

mvn spring-boot:run
```
So you can access the application on [http](http://localhost:8080/)http://localhost:8080/

To build a war file:
```
mvn war:war
```
you can find mail-tracker-0.0.1-SNAPSHOT.war file in target directory


**Rest APIs**

for test application using rest apis you can find swagger ui in below address after deploye the project:

http://localhost:8080/swagger-ui/index.html

*Please note that HOST:PORT(localhost:8080) depends on way of deployement.*

There are multipale api there:
  
* Register the new postal Item

http://localhost:8080/postal-item/register

*You can find a sample item by calling http://localhost:8080/postal-item?itemid=1. you can copy the item in register api body and excute it in swagger UI.*

This registration needs the details of postal item and id of the post office.*You can get list of post office from http://localhost:8080/post-offices *

* Arrival the item to new office

 http://localhost:8080/postal-item/arrival
 this method needs item id and post office id.

* Departure the item from the office

 http://localhost:8080/postal-item/departure

* Deliver the item to receipt

 http://localhost:8080/postal-item/deliver



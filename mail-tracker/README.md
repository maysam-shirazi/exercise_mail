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

*Application uses a data file(data.sql) for initializing base info data.you can find it in resources directory in src*

**Rest APIs**

for test application using rest apis you can find swagger ui in below address after deploye the project:

http://localhost:8080/swagger-ui/index.html

*Please note that HOST:PORT(localhost:8080) depends on way of deployement.*

There are multiple api:
  
* Register the new postal Item

http://localhost:8080/postal-item/register

*You can find a sample item by calling http://localhost:8080/postal-item?itemid=1. you can copy the item in register api body and execute it in swagger UI.*

here is an example of postal item for registration:
 ```
{
  "type": {
    "id": 2,
  },
  "recipientIndex": 5432167890,
  "recipientAddress": "Volzhskiy Druzhby Ul bld 121 appt 160",
  "receiverName": "Vladimir",
  "postOffice": {
    "id": 2,
  }
}
 ```

This registration needs the details of postal item and id of the post office.*You can get list of post office from http://localhost:8080/post-offices *

* Arrival the item to new office

 http://localhost:8080/postal-item/arrival
 
 this method needs postal item id and post office id.
 ```
 {"itemId":1,"postOfficeId":1}
```

* Departure the item from the office

 http://localhost:8080/postal-item/departure

 this method needs only postal item id .
 ```
 {"itemId":1}
```

* Deliver the item to receipt

 http://localhost:8080/postal-item/deliver

this method needs only postal item id .
 ```
 {"itemId":1}
```

* Get last status of the postal item

http://localhost:8080/event/item-status?itemid=1

You can use *itemId* as input parameter

sample of response:

 ```
{
  "id": 2,
  "postalItem": {
    "id": 1,
    "type": {
      "id": 1,
      "title": "letter"
    },
    "recipientIndex": 5432167890,
    "recipientAddress": "Volzhskiy Druzhby Ul bld 121 appt 160",
    "receiverName": "Vladimir",
    "postOffice": {
      "id": 1,
      "index": 100001,
      "name": "Moscow",
      "address": "central post office"
    }
  },
  "eventTime": "2023-08-20T23:16:48.315+00:00",
  "type": {
    "id": 3,
    "title": "departed"
  },
  "postOffice": {
    "id": 1,
    "index": 100001,
    "name": "Moscow",
    "address": "central post office"
  }
}
 ```

* Get the history of the postal item

http://localhost:8080/event?itemid=1

You can use *itemId* as input parameter

sample of response:

 ```
[
  {
    "id": 1,
    "postalItem": {
      "id": 1,
      "type": {
        "id": 1,
        "title": "letter"
      },
      "recipientIndex": 5432167890,
      "recipientAddress": "Volzhskiy Druzhby Ul bld 121 appt 160",
      "receiverName": "Vladimir",
      "postOffice": {
        "id": 2,
        "index": 200001,
        "name": "Sochi",
        "address": "local post office"
      }
    },
    "eventTime": "2023-08-20T23:13:30.170+00:00",
    "type": {
      "id": 1,
      "title": "registered"
    },
    "postOffice": {
      "id": 1,
      "index": 100001,
      "name": "Moscow",
      "address": "central post office"
    }
  },
  {
    "id": 2,
    "postalItem": {
      "id": 1,
      "type": {
        "id": 1,
        "title": "letter"
      },
      "recipientIndex": 5432167890,
      "recipientAddress": "Volzhskiy Druzhby Ul bld 121 appt 160",
      "receiverName": "Vladimir",
      "postOffice": {
        "id": 2,
        "index": 200001,
        "name": "Sochi",
        "address": "local post office"
      }
    },
    "eventTime": "2023-08-20T23:16:48.315+00:00",
    "type": {
      "id": 3,
      "title": "departed"
    },
    "postOffice": {
      "id": 1,
      "index": 100001,
      "name": "Moscow",
      "address": "central post office"
    }
  },
  {
    "id": 3,
    "postalItem": {
      "id": 1,
      "type": {
        "id": 1,
        "title": "letter"
      },
      "recipientIndex": 5432167890,
      "recipientAddress": "Volzhskiy Druzhby Ul bld 121 appt 160",
      "receiverName": "Vladimir",
      "postOffice": {
        "id": 2,
        "index": 200001,
        "name": "Sochi",
        "address": "local post office"
      }
    },
    "eventTime": "2023-08-20T23:27:15.657+00:00",
    "type": {
      "id": 2,
      "title": "arrived"
    },
    "postOffice": {
      "id": 2,
      "index": 200001,
      "name": "Sochi",
      "address": "local post office"
    }
  }
]
 ```
There are some other apis to obtain basic information for postal item registration:

List of post offices
http://localhost:8080/post-offices

List of postal item types
http://localhost:8080/postal-item-types

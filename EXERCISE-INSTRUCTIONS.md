# Aircraft Queue Manager Challenge

## Challenge description
The goal of this exercise is to demonstrate programming ability by developing a simple software subsystem. Work should be done in Java.

A software subsystem of an air-traffic control system is required to manage a queue of aircraft at an airport. The aircraft queue is managed by a service that responds to three types of requests:
 - System boot used to start the system.
 - Enqueue aircraft used to insert a new Aircraft into the system. 
 - Dequeue aircraft used to remove an Aircraft from the system.

Aircraft have at least (but are not limited to having) the following properties:
 - Type (see `AircraftType` enum): Passenger or Cargo
 - Size (see `AircraftSize` enum): Small or Large

The process that manages the queue of aircraft satisfies the following: 
 - There is no limit to the number of aircraft it can manage.
 - Dequeue aircraft requests result in selection of one aircraft for removal such that:
    - Passenger aircraft have removal precedence over Cargo aircraft.
    - Large aircraft of a given type have removal precedence over Small aircraft of the same type.
    - Earlier enqueued aircraft of a given type and size have precedence over later enqueued aircraft of the same type and size.

System Implementation Requirements:
 - Develop one or more data structures to hold the state of an individual aircraft.
 - Develop one or more data structures to hold the state of the aircraft queue
 - Define data structures and/or constants needed to represent requests.
 - Develop the code for REST endpoints that implement the three defined requests and follows the above guidelines to implement an aircraft queue manager.
 - To the greatest extent possible, show all of your code. Feel free to use standard libraries.

## Running Spring Boot

This project should be able to run with a version of Maven at or greater than `3.6.1`. Use the command `mvn spring-boot:run` to start Spring Boot.

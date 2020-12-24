# Aircraft Queue Manager

## Description

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

System Implementation:
- There are multiple data structures and/or constants defined to represent requests, the state of an individual aircraft and the state of the aircraft queue.
- The code has REST endpoints that implement the three defined requests and follows the above guidelines to implement an aircraft queue manager.


## Local environment swagger API documentation links

The API documentation provided at:
Open __http://localhost:8080/swagger-ui.html__ for API documentation
http://localhost:8080/v2/api-docs

## How To Run:

Use the command `mvn spring-boot:run` to start the Spring Boot

Run `mvn clean install` to build the project

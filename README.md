# Aircraft Queue Manager

## It manages multiple Aircraft queues

Aircraft have at least (but are not limited to having) the following properties:
 - Type (see `AircraftType` enum): Passenger or Cargo
 - Size (see `AircraftSize` enum): Small or Large

The process that manages the queue of aircraft satisfies the following:
 - There is no limit to the number of aircraft it can manage.
 - Dequeue aircraft requests result in selection of one aircraft for removal such that:
    - Passenger aircraft have removal precedence over Cargo aircraft.
    - Large aircraft of a given type have removal precedence over Small aircraft of the same type.
    - Earlier enqueued aircraft of a given type and size have precedence over later enqueued aircraft of the same type and size.

## Local environment swagger API documentation links

The API documentation provided at:
Open __http://localhost:8080/swagger-ui.html__ for API documentation
http://localhost:8080/v2/api-docs

## How To Run:

mvn spring-boot:run

Run mvn clean install to build the project

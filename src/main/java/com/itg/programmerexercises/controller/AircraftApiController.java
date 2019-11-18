package com.itg.programmerexercises.controller;

import com.itg.programmerexercises.api.v1.mapper.AircraftMapper;
import com.itg.programmerexercises.api.v1.model.AircraftDTO;
import com.itg.programmerexercises.objects.aircraft.Aircraft;
import com.itg.programmerexercises.service.AircraftQueueManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Aircraft Api Controller class
 */
@RestController
@RequestMapping(AircraftApiController.BASE_URL)
@Api(description = "This is Aircraft queues Api")
public class AircraftApiController {
    public static final String BASE_URL = "/api/v1/aircraft/queues";
    private final AircraftQueueManager aircraftQueueManager;
    private final AircraftMapper aircraftMapper;

    @Autowired
    public AircraftApiController(AircraftQueueManager aircraftQueueManager, AircraftMapper aircraftMapper) {
        this.aircraftQueueManager = aircraftQueueManager;
        this.aircraftMapper = aircraftMapper;
    }

    @ApiOperation(value = "Initiate system boot")
    @PostMapping(value = "/systemboot")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity systemBoot() {
        this.aircraftQueueManager.systemBoot();
        return ResponseEntity.ok("System rebooted");
    }

    @ApiOperation(value = "Enqueue an Aircraft into the system")
    @PostMapping(value = "/enqueue")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity enqueueAircraft(@RequestBody AircraftDTO aircraftDto) {
        Aircraft aircraft = aircraftMapper.aircraftDtoToAircraft(aircraftDto);
        this.aircraftQueueManager.enqueueAircraft(aircraft);
        return new ResponseEntity<>(aircraft, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Dequeue an Aircraft from the system")
    @PostMapping(value = "/dequeue")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity dequeueAircraft() {
        Aircraft aircraft = this.aircraftQueueManager.dequeueAircraft();
        return new ResponseEntity<>(aircraft, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Get Aircraft queue size")
    @GetMapping(value = "/queuesize")
    @ResponseStatus(HttpStatus.OK)
    public int getQueueSize() {
        return this.aircraftQueueManager.getQueueSize();
    }
}

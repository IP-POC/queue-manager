package com.ipyrin.programmerexercises.controller;

import com.ipyrin.programmerexercises.api.v1.mapper.AircraftMapper;
import com.ipyrin.programmerexercises.api.v1.model.AircraftDTO;
import com.ipyrin.programmerexercises.objects.aircraft.Aircraft;
import com.ipyrin.programmerexercises.service.AircraftQueueManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Aircraft Api Controller class
 */
@RestController
@RequestMapping(AircraftApiController.BASE_URL)
@Tag(name = "queues", description = "An endpoint exposing Aircraft queues Api")
public class AircraftApiController {
    public static final String BASE_URL = "/api/v1/aircraft/queues";
    private final AircraftQueueManager aircraftQueueManager;
    private final AircraftMapper aircraftMapper;

    @Autowired
    public AircraftApiController(AircraftQueueManager aircraftQueueManager, AircraftMapper aircraftMapper) {
        this.aircraftQueueManager = aircraftQueueManager;
        this.aircraftMapper = aircraftMapper;
    }

    @Operation(summary = "Initiate system boot")
    @PostMapping(value = "/systemboot")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity systemBoot() {
        this.aircraftQueueManager.systemBoot();
        return ResponseEntity.ok("System rebooted");
    }

    @Operation(summary = "Enqueue an Aircraft into the system")
    @PostMapping(value = "/enqueue")
    @ResponseStatus(HttpStatus.CREATED)
    //TODO: check why body params doesn't show up in swagger ui
    public ResponseEntity enqueueAircraft(@RequestBody AircraftDTO aircraftDto) {
        Aircraft aircraft = aircraftMapper.aircraftDtoToAircraft(aircraftDto);
        this.aircraftQueueManager.enqueueAircraft(aircraft);
        return new ResponseEntity<>(aircraft, HttpStatus.CREATED);
    }

    @Operation(summary = "Dequeue an Aircraft from the system")
    @PostMapping(value = "/dequeue")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity dequeueAircraft() {
        Aircraft aircraft = this.aircraftQueueManager.dequeueAircraft();
        return new ResponseEntity<>(aircraft, HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Get Aircraft queue size")
    @GetMapping(value = "/queuesize")
    @ResponseStatus(HttpStatus.OK)
    public int getQueueSize() {
        return this.aircraftQueueManager.getQueueSize();
    }
}

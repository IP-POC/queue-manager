package com.ipyrin.programmerexercises.service;

import com.ipyrin.programmerexercises.exception.AircraftNotFoundException;
import com.ipyrin.programmerexercises.objects.aircraft.Aircraft;
import com.ipyrin.programmerexercises.objects.aircraft.enums.AircraftSize;
import com.ipyrin.programmerexercises.objects.aircraft.enums.AircraftType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AircraftQueueManagerImplTest {

    AircraftQueueManagerImpl subject;

    @BeforeEach
    public void setUp() {
        subject = new AircraftQueueManagerImpl(new AircraftCompositeQueue());
    }

    @Test
    public void systemBootEmpty() {
        subject.systemBoot();
        assertEquals(0, subject.getQueueSize());
    }

    @Test
    public void systemBootOne() {
        buildAndEnqueueAircraftToQueue(AircraftType.PASSENGER, AircraftSize.LARGE);
        subject.systemBoot();
        assertEquals(0, subject.getQueueSize());
    }

    @Test
    public void enqueueAircraftPassengerLarge() {
        buildAndEnqueueAircraftToQueue(AircraftType.PASSENGER, AircraftSize.LARGE);
        assertEquals(1, subject.getQueueSize());
    }

    @Test
    public void dequeueAircraftEmptyQueue() {
        Assertions.assertThrows(AircraftNotFoundException.class, () -> {
            Aircraft aircraft = subject.dequeueAircraft();
            assertEquals(0, subject.getQueueSize());
        });
    }

    @Test
    public void dequeueAircraft() {
        buildAndEnqueueAircraftToQueue(AircraftType.PASSENGER, AircraftSize.LARGE);
        Aircraft aircraft = subject.dequeueAircraft();
        assertEquals(0, subject.getQueueSize());
    }

    private Aircraft buildAndEnqueueAircraftToQueue(AircraftType type, AircraftSize size) {
        Aircraft aircraft = Aircraft.builder()
                .aircraftType(type)
                .aircraftSize(size)
                .build();
        subject.enqueueAircraft(aircraft);
        return aircraft;
    }

}
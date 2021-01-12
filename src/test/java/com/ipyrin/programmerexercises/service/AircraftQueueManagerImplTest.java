package com.ipyrin.programmerexercises.service;

import com.ipyrin.programmerexercises.exception.AircraftNotFoundException;
import com.ipyrin.programmerexercises.objects.aircraft.Aircraft;
import com.ipyrin.programmerexercises.objects.aircraft.enums.AircraftSize;
import com.ipyrin.programmerexercises.objects.aircraft.enums.AircraftType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AircraftQueueManagerImplTest {

    AircraftQueueManagerImpl subject;

    @Before
    public void setUp() throws Exception {
        subject = new AircraftQueueManagerImpl(new AircraftCompositeQueue());
    }

    @Test
    public void systemBootEmpty() {
        subject.systemBoot();
        assertEquals(0, subject.getQueueSize());
    }

    @Test
    public void systemBootOne() {
        buildAndEnqueAircraftToQueue(AircraftType.PASSENGER, AircraftSize.LARGE);
        subject.systemBoot();
        assertEquals(0, subject.getQueueSize());
    }

    @Test
    public void enqueueAircraftPassengerLarge() {
        buildAndEnqueAircraftToQueue(AircraftType.PASSENGER, AircraftSize.LARGE);
        assertEquals(1, subject.getQueueSize());
    }

    @Test(expected = AircraftNotFoundException.class)
    public void dequeueAircraftEmptyQueue() {
        Aircraft aircraft = subject.dequeueAircraft();
        assertEquals(0, subject.getQueueSize());
    }

    @Test
    public void dequeueAircraft() {
        buildAndEnqueAircraftToQueue(AircraftType.PASSENGER, AircraftSize.LARGE);
        Aircraft aircraft = subject.dequeueAircraft();
        assertEquals(0, subject.getQueueSize());
    }

    private Aircraft buildAndEnqueAircraftToQueue(AircraftType type, AircraftSize size) {
        Aircraft aircraft = Aircraft.builder()
                .aircraftType(type)
                .aircraftSize(size)
                .build();
        subject.enqueueAircraft(aircraft);
        return aircraft;
    }

}
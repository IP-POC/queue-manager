package com.ipyrin.programmerexercises.service;

import com.ipyrin.programmerexercises.objects.aircraft.Aircraft;
import com.ipyrin.programmerexercises.objects.aircraft.enums.AircraftSize;
import com.ipyrin.programmerexercises.objects.aircraft.enums.AircraftType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AircraftCompositeQueueTest {

    AircraftCompositeQueue subject;

    @BeforeEach
    public void setUp() {
        subject = new AircraftCompositeQueue();
    }

    @Test
    public void clearEmpty() {
        subject.clear();
        assertEquals(0, subject.size());
    }

    @Test
    public void clear() {
        buildAndAddAircraftToQueue(AircraftType.PASSENGER, AircraftSize.LARGE);
        subject.clear();
        assertEquals(0, subject.size());
    }

    @Test
    public void sizeEmpty() {
        assertEquals(0, subject.size());
    }

    @Test
    public void sizeOne() {
        buildAndAddAircraftToQueue(AircraftType.PASSENGER, AircraftSize.LARGE);
        assertEquals(1, subject.size());
    }

    @Test
    public void pollPassengerLarge() {
        buildAndAddAircraftToQueue(AircraftType.PASSENGER, AircraftSize.LARGE);
        subject.poll();
        assertEquals(0, subject.size());
    }

    @Test
    public void addPassengerLarge() {
        Aircraft aircraft = buildAircraft(AircraftType.PASSENGER, AircraftSize.LARGE);
        assertTrue(subject.add(aircraft));
        assertEquals(1, subject.size());
        assertEquals(1, subject.passengerLarge.size());
    }

    @Test
    public void addPassengerSmall() {
        Aircraft aircraft = buildAircraft(AircraftType.PASSENGER, AircraftSize.SMALL);
        assertTrue(subject.add(aircraft));
        assertEquals(1, subject.size());
        assertEquals(1, subject.passengerSmall.size());
    }

    @Test
    public void addCargoLarge() {
        Aircraft aircraft = buildAircraft(AircraftType.CARGO, AircraftSize.LARGE);
        assertTrue(subject.add(aircraft));
        assertEquals(1, subject.size());
        assertEquals(1, subject.cargoLarge.size());
    }

    @Test
    public void addCargoSmall() {
        Aircraft aircraft = buildAircraft(AircraftType.CARGO, AircraftSize.SMALL);
        assertTrue(subject.add(aircraft));
        assertEquals(1, subject.size());
        assertEquals(1, subject.cargoSmall.size());
    }

    @Test
    public void addAll() {
        buildAndAddAircraftToQueue(AircraftType.PASSENGER, AircraftSize.LARGE);
        buildAndAddAircraftToQueue(AircraftType.PASSENGER, AircraftSize.SMALL);
        buildAndAddAircraftToQueue(AircraftType.CARGO, AircraftSize.LARGE);
        buildAndAddAircraftToQueue(AircraftType.CARGO, AircraftSize.SMALL);

        assertEquals(4, subject.size());
        assertEquals(1, subject.passengerSmall.size());
        assertEquals(1, subject.passengerLarge.size());
        assertEquals(1, subject.cargoSmall.size());
        assertEquals(1, subject.cargoLarge.size());
    }

    @Test
    public void poolByPriority() {
        buildAndAddAircraftToQueue(AircraftType.PASSENGER, AircraftSize.LARGE);
        buildAndAddAircraftToQueue(AircraftType.PASSENGER, AircraftSize.SMALL);
        buildAndAddAircraftToQueue(AircraftType.CARGO, AircraftSize.LARGE);
        buildAndAddAircraftToQueue(AircraftType.CARGO, AircraftSize.SMALL);

        assertEquals(4, subject.size());
        assertEquals(1, subject.passengerSmall.size());
        assertEquals(1, subject.passengerLarge.size());
        assertEquals(1, subject.cargoSmall.size());
        assertEquals(1, subject.cargoLarge.size());
        Aircraft aircraft1 = subject.poll();
        assertEquals(AircraftType.PASSENGER, aircraft1.getAircraftType());
        assertEquals(AircraftSize.LARGE, aircraft1.getAircraftSize());
        Aircraft aircraft2 = subject.poll();
        assertEquals(AircraftType.PASSENGER, aircraft2.getAircraftType());
        assertEquals(AircraftSize.SMALL, aircraft2.getAircraftSize());
        Aircraft aircraft3 = subject.poll();
        assertEquals(AircraftType.CARGO, aircraft3.getAircraftType());
        assertEquals(AircraftSize.LARGE, aircraft3.getAircraftSize());
        Aircraft aircraft4 = subject.poll();
        assertEquals(AircraftType.CARGO, aircraft4.getAircraftType());
        assertEquals(AircraftSize.SMALL, aircraft4.getAircraftSize());
    }

    @Test
    public void getQueuePassengerLarge() {
        assertNotNull(subject.getQueue(AircraftType.PASSENGER, AircraftSize.LARGE));
    }

    @Test
    public void getQueuePassengerSmall() {
        assertNotNull(subject.getQueue(AircraftType.PASSENGER, AircraftSize.SMALL));
    }

    @Test
    public void getQueueCargoLarge() {
        assertNotNull(subject.getQueue(AircraftType.CARGO, AircraftSize.LARGE));
    }

    @Test
    public void getQueueCargoSmall() {
        assertNotNull(subject.getQueue(AircraftType.CARGO, AircraftSize.SMALL));
    }

    private Aircraft buildAndAddAircraftToQueue(AircraftType type, AircraftSize size) {
        Aircraft aircraft = buildAircraft(type, size);
        subject.add(aircraft);
        return aircraft;
    }

    private Aircraft buildAircraft(AircraftType type, AircraftSize size) {
        return Aircraft.builder()
                .aircraftType(type)
                .aircraftSize(size)
                .build();
    }
}
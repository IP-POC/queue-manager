package com.itg.programmerexercises.service;

import com.itg.programmerexercises.exception.AircraftNotFoundException;
import com.itg.programmerexercises.objects.aircraft.Aircraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Aircraft Queue Manager Implementation
 *
 * @author Igor Pyrin
 */
@Service
public class AircraftQueueManagerImpl implements AircraftQueueManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(AircraftQueueManagerImpl.class);

    private final AircraftCompositeQueue aircraftCompositeQueue;

    @Autowired
    public AircraftQueueManagerImpl(AircraftCompositeQueue aircraftCompositeQueue) {
        this.aircraftCompositeQueue = aircraftCompositeQueue;
    }

    @Override
    public void systemBoot() {
        aircraftCompositeQueue.clear();
        LOGGER.debug("system booted");
    }

    @Override
    public Aircraft enqueueAircraft(final Aircraft aircraft) {
        LOGGER.debug("Enqueue aircraft: {}", aircraft);
        if (!aircraftCompositeQueue.add(aircraft)) {
            throw new AircraftNotFoundException("Can not enqueue aircraft!");
        }
        return aircraft;
    }

    @Override
    public Aircraft dequeueAircraft() {
        Aircraft aircraft = null;
        if (getQueueSize() == 0) {
            throw new AircraftNotFoundException("The aircraft queue is empty!");
        }
        aircraft = aircraftCompositeQueue.poll();
        LOGGER.debug("Dequeue aircraft: {}", aircraft);
        if (aircraft == null) {
            throw new AircraftNotFoundException("Can not dequeue aircraft!");
        }
        return aircraft;
    }

    public int getQueueSize() {
        return aircraftCompositeQueue.size();
    }

}

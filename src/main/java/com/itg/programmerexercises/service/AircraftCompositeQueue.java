package com.itg.programmerexercises.service;

import com.itg.programmerexercises.objects.aircraft.Aircraft;
import com.itg.programmerexercises.objects.aircraft.enums.AircraftSize;
import com.itg.programmerexercises.objects.aircraft.enums.AircraftType;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Aircraft Composite Queue class
 * provides faster performance than priority queue
 *
 * @author Igor Pyrin
 */
@Component
public class AircraftCompositeQueue {
    protected final ConcurrentLinkedQueue<Aircraft> passengerLarge = new ConcurrentLinkedQueue<>();
    protected final ConcurrentLinkedQueue<Aircraft> passengerSmall = new ConcurrentLinkedQueue<>();
    protected final ConcurrentLinkedQueue<Aircraft> cargoLarge = new ConcurrentLinkedQueue<>();
    protected final ConcurrentLinkedQueue<Aircraft> cargoSmall = new ConcurrentLinkedQueue<>();
    private final Queue[] queueArray;

    public AircraftCompositeQueue() {
        this.queueArray = new ConcurrentLinkedQueue[]{passengerLarge, passengerSmall, cargoLarge, cargoSmall};
    }

    protected Queue[] getQueueArray() {
        return queueArray;
    }

    /**
     * Removes all of the elements from this queue.
     */
    public void clear() {
        passengerLarge.clear();
        passengerSmall.clear();
        cargoLarge.clear();
        cargoSmall.clear();
    }

    /**
     * Gets overall size of composite queue.
     *
     * @return
     */
    public int size() {
        int size = passengerLarge.size();
        size += passengerSmall.size();
        size += cargoLarge.size();
        size += cargoSmall.size();
        return size;
    }

    /**
     * Retrieves according to priority and removes the head of this queue
     * or returns {@code null} if this queue is empty.
     *
     * @return
     */
    public synchronized Aircraft poll() {
        Aircraft aircraft = null;
        for (Queue<Aircraft> queue : getQueueArray()) {
            aircraft = queue.poll();
            if (aircraft != null) {
                break;
            }
        }
        return aircraft;
    }

    public synchronized boolean add(Aircraft aircraft) {
        Queue<Aircraft> queue = getQueue(aircraft.getAircraftType(), aircraft.getAircraftSize());
        if (queue == null) {
            return false;
        }
        return queue.add(aircraft);
    }

    /**
     * Gets specific queue
     *
     * @param aircraftType
     * @param aircraftSize
     * @return
     */
    protected Queue<Aircraft> getQueue(final AircraftType aircraftType, final AircraftSize aircraftSize) {
        ConcurrentLinkedQueue<Aircraft> queue = null;
        if (aircraftType == AircraftType.PASSENGER) {
            switch (aircraftSize) {
                case LARGE:
                    queue = passengerLarge;
                    break;
                case SMALL:
                    queue = passengerSmall;
                    break;
            }
        } else if (aircraftType == AircraftType.CARGO) {
            switch (aircraftSize) {
                case LARGE:
                    queue = cargoLarge;
                    break;
                case SMALL:
                    queue = cargoSmall;
                    break;
            }
        }

        return queue;
    }

}
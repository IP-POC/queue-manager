package com.itg.programmerexercises.service;

import com.itg.programmerexercises.objects.aircraft.Aircraft;

public interface AircraftQueueManager
{

	/**
	 * <p>
	 * Initialize and start the system
	 * </p>
	 */
	void systemBoot();

	/**
	 * <p>
	 * Enqueue aircraft used to insert a new Aircraft into the system
	 * </p>
	 *
	 * @param aircraft
	 * @return
	 */
	Aircraft enqueueAircraft(Aircraft aircraft);

	/**
	 * <p>
	 * Dequeue aircraft used to remove next available by priority Aircraft from the system
	 * </p>
	 *
	 * @return
	 */
	Aircraft dequeueAircraft();


	int getQueueSize();
}

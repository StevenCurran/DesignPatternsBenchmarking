package com.stecurran.benchmark.nonstrategy;

import com.stecurra.benchmark.strategy.SimpleEvent;
import com.stecurra.benchmark.strategy.TimeStore;

public class EventRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		TimeStore.start = System.nanoTime();

		CalendarService cs = new CalendarService('f');

		for (SimpleEvent simpleEvent : cs.getEvents()) {
			System.out.println(simpleEvent);
		}

		TimeStore.end = System.nanoTime();

		System.out.println(TimeStore.getTime());

	}

}

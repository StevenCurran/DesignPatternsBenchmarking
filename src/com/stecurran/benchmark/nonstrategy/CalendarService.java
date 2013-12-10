package com.stecurran.benchmark.nonstrategy;

import java.util.Collections;
import java.util.List;

import com.stecurra.benchmark.strategy.FacebookRetriever;
import com.stecurra.benchmark.strategy.GoogleRetriever;
import com.stecurra.benchmark.strategy.SimpleEvent;

/*
 * Use this or the calendar context
 */

public class CalendarService {

	private FacebookRetriever fbConn = null;
	private GoogleRetriever gConn = null;

	public CalendarService(char calType) {

		switch (calType) {
		case 'f':
			this.fbConn = FacebookRetriever.getInstance();
			break;
		case 'g':
			this.gConn = GoogleRetriever.getInstance();
			break;
		default:
			System.exit(1);
			// pass this in from the constructor;
			// this will need to be set for benchmarking.
			break;
		}

	}

	public List<SimpleEvent> getEvents() {

		if (fbConn != null) {
			return fbConn.retrieveEvents();
		} else if (gConn != null) {
			return fbConn.retrieveEvents();
		}
		return Collections.emptyList();
	}

}
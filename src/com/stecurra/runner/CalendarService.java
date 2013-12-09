package com.stecurra.runner;

import java.util.List;

/*
 * Use this or the calendar context
 */

public class CalendarService {

	private CalendarMethod calendarConnector;

	public CalendarService(char calType) {

		switch (calType) {
		case 'f':
			this.calendarConnector = FacebookRetriever.getInstance();
			break;
		case 'g':
			this.calendarConnector = GoogleRetriever.getInstance();
			break;
		default:
			this.calendarConnector = null;
			// pass this in from the constructor;
			// this will need to be set for benchmarking.
			break;
		}

	}

	public CalendarService(CalendarMethod cm) {
		this.calendarConnector = cm;
	}

	public void setCalendarConnector(CalendarMethod connector) {
		this.calendarConnector = connector;
	}

	public List<SimpleEvent> getEvents() {
		return calendarConnector.retrieveEvents();
	}

}

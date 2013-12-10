package com.stecurra.benchmark.strategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoogleRetriever implements CalendarMethod {

	private static final Date DATE = new Date();

	private GoogleRetriever() {
	}

	@Override
	public List<SimpleEvent> retrieveEvents() {

		List<SimpleEvent> events = new ArrayList<>();

		for (int i = 0; i < 15; i++) {
			SimpleEvent e = new SimpleEvent();
			e.setEventName("name");
			e.setEventDesc("desc");
			e.setEventStartDate(GoogleRetriever.DATE);
			e.setEventEndDate(GoogleRetriever.DATE);
			events.add(e);
		}

		return events;

	}

	public static GoogleRetriever getInstance() {
		return new GoogleRetriever();
	}

}

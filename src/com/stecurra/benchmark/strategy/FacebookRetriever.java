package com.stecurra.benchmark.strategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FacebookRetriever implements CalendarMethod {

	private static final Date DATE = new Date();

	private FacebookRetriever() {
	}

	public static FacebookRetriever getInstance() {
		return new FacebookRetriever();
	}

	
	@Override
	public List<SimpleEvent> retrieveEvents() {

		List<SimpleEvent> events = new ArrayList<>();

		for (int i = 0; i < 15; i++) {
			SimpleEvent e = new SimpleEvent();
			e.setEventName("name");
			e.setEventDesc("desc");
			e.setEventStartDate(FacebookRetriever.DATE);
			e.setEventEndDate(FacebookRetriever.DATE);
			events.add(e);
		}

		return events;

	}

}

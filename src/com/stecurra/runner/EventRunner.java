package com.stecurra.runner;

import java.nio.file.Path;
import java.nio.file.Paths;

public class EventRunner {

	public static void main(String[] args) {

		setUpUniqueVals();

		
		//FacebookRetriever fbCal = new FacebookRetriever();
		FacebookRetriever fbCal = FacebookRetriever.getInstance();
		GoogleRetriever gCal = GoogleRetriever.getInstance();

		CalendarService cs = new CalendarService(fbCal);

		for (SimpleEvent simpleEvent : cs.getEvents()) {
			System.out.println(simpleEvent);
		}
		

	}

	private static void setUpUniqueVals() {
		System.setProperty(
				"fb.usertoken",
				"CAACEdEose0cBAIEFDcLPH4d8B58nZB7ApK1ogu1kTIZCZAZBZAaACqyy3sYNHzDcZClYQ8M9xXZCY4i18P3W1HsKn8HfPbvwfERFAk8brL9bDMQJ67PNcScMbm9fwRZCzqb35tfAODeq6PSbl0BELFpi9gf4vDhbA2SWRxJcv3wZCIQf6xYc5uIEL5PpQyRyvt4oZD");
		
		System.setProperty("google.calendar.name", "9epmhcof6u6lc14cv08148577k@group.calendar.google.com");

	}

}

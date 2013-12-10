package com.stecurra.benchmark.strategy;

@SuppressWarnings("unused")
public class EventRunner {

	public static void main(String[] args) {

		TimeStore.start = System.nanoTime();

		//FacebookRetriever fbCal = FacebookRetriever.getInstance();
		GoogleRetriever gCal = GoogleRetriever.getInstance();

		CalendarService cs = new CalendarService(gCal);

		for (SimpleEvent simpleEvent : cs.getEvents()) {
			System.out.println(simpleEvent);
		}

		TimeStore.end = System.nanoTime();
		
		System.out.println(TimeStore.getTime());

	}

}

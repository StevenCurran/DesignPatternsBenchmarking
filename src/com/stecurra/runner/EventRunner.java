package com.stecurra.runner;

import java.nio.file.Path;
import java.nio.file.Paths;

public class EventRunner {

	public static void main(String[] args) {

		setUpUniqueVals();

		
		//FacebookRetriever fbCal = new FacebookRetriever();
		FacebookRetriever fbCal = FacebookRetriever.getInstance();
		GoogleRetriever gCal = GoogleRetriever.getInstance();

		CalendarService cs = new CalendarService(gCal);

		for (SimpleEvent simpleEvent : cs.getEvents()) {
			System.out.println(simpleEvent);
		}
		

	}

	private static void setUpUniqueVals() {
		System.setProperty(
				"fb.usertoken",
				"CAACEdEose0cBACWNCA0Wt0GQrLpoFZCfVF9Hj5EraiGZBbgdiGF7qcabZASCA9AJIuhO9yZAriQy11elimZC2ID2sFvsbn32KZCmDOee1AIxAgHkqiI1t2ZAzMc6xu0AEa2rhiNDuHsmwSdAmU73gahZCuyOMBiCCTyFz0yeIDD8QBRZCHkdk2Nq4fELlKioRXZAUZD");
		
		System.setProperty("google.calendar.name", "9epmhcof6u6lc14cv08148577k@group.calendar.google.com");

	}

}

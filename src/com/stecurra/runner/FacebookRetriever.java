package com.stecurra.runner;

import java.util.ArrayList;
import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Event;

public class FacebookRetriever implements CalendarMethod {

	private FacebookClient facebookClient;

	private FacebookRetriever() {
		facebookClient = new DefaultFacebookClient(System.getProperty("fb.usertoken"));
	}
	
	public static FacebookRetriever getInstance(){
		FacebookRetriever fb = new FacebookRetriever();
		fb.setFacebookClient(new DefaultFacebookClient(System.getProperty("fb.usertoken")));
		return fb;
	}
	
	private void setFacebookClient(FacebookClient facebookClient){
		this.facebookClient = facebookClient;
	}
	

	@Override
	public List<SimpleEvent> retrieveEvents() {
		
		List<SimpleEvent> events = new ArrayList<>();

		Connection<Event> myEvents = facebookClient.fetchConnection("me/events", Event.class, Parameter.with("fields", "description, name, location"));
		
		for (Event post : myEvents.getData()){
			SimpleEvent e = new SimpleEvent();
			e.setEventDesc(post.getDescription());
			e.setEventEndDate(post.getEndTime());
			e.setEventName(post.getName());
			e.setEventStartTime(post.getStartTime());
			System.err.println(post.getLocation());
			events.add(e);
		}
		// https://developers.facebook.com/tools/explorer/?method=GET&path=me%2Fevents
		return events;

	}

}

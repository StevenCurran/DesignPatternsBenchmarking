package com.stecurra.runner;

import java.util.Date;

public class SimpleEvent {
	
	private String eventName;
	private String eventDesc;
	private Date eventStartTime;
	private Date eventEndDate;
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	public Date getEventStartTime() {
		return eventStartTime;
	}
	public void setEventStartTime(Date eventStartTime) {
		this.eventStartTime = eventStartTime;
	}
	public Date getEventEndDate() {
		return eventEndDate;
	}
	public void setEventEndDate(Date eventEndDate) {
		this.eventEndDate = eventEndDate;
	}
	
	
	@Override
	public String toString() {
		return this.eventName + " : " + this.eventStartTime;
	}
	

}

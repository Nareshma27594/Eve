package com.cybage.bean;

public class Event {
int event_id;
public String getName() {
	return name;
}
public Event(int event_id, String name, String venue, int price, String category) {
	super();
	this.event_id = event_id;
	this.name = name;
	this.venue = venue;
	this.price = price;
	this.category = category;
}


public Event(String name, String venue, int price, String category) {
	super();
	this.name = name;
	this.venue = venue;
	this.price = price;
	this.category = category;
}
public void setName(String name) {
	this.name = name;
}
public String getVenue() {
	return venue;
}
public void setVenue(String venue) {
	this.venue = venue;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public int getEvent_id() {
	return event_id;
}
String name;
String venue;
int price;
String category;
}

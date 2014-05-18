package com.hackathon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Event {
	
	@Id
	private Long id;
	private Date date;
	private List<String> groups = new ArrayList<String>();
	private double lat;
	private double lng;
	private String location;
	private String name;
	
	public Date getDate() {
		return date;
	}
	
	public Event setDate(Date date) {
		this.date = date;
		return this;
	}
	
	public List<String> getGroups() {
		return groups;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void addGroup(String groupId) {
		this.groups.add(groupId);
	}
	
	public Event setLat(double lat) {
		this.lat = lat;
		return this;
	}
	
	public Event setLng(double lng) {
		this.lng = lng;
		return this;
	}
	
	public double getLat() {
		return lat;
	}
	
	public double getLng() {
		return lng;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
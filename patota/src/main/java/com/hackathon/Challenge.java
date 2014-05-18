package com.hackathon;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Challenge {

	@Id
	private Long id;
	private Date date;
	private Date expires;
	private String groupId;
	private double lat;
	private double lng;
	private String location;
	private String name;
	private boolean accepted;
	private Long opponentGroupId;
	public Long getId() {
		return id;
	}
	public Challenge setId(Long id) {
		this.id = id;
		return this;
	}
	public Date getDate() {
		return date;
	}
	public Challenge setDate(Date date) {
		this.date = date;
		return this;
	}
	public Date getExpires() {
		return expires;
	}
	public Challenge setExpires(Date expires) {
		this.expires = expires;
		return this;
	}
	public String getGroupId() {
		return groupId;
	}
	public Challenge setGroupId(String groupId) {
		this.groupId = groupId;
		return this;
	}
	public double getLat() {
		return lat;
	}
	public Challenge setLat(double lat) {
		this.lat = lat;
		return this;
	}
	public double getLng() {
		return lng;
	}
	public Challenge setLng(double lng) {
		this.lng = lng;
		return this;
	}
	public String getLocation() {
		return location;
	}
	public Challenge setLocation(String location) {
		this.location = location;
		return this;
	}
	public String getName() {
		return name;
	}
	public Challenge setName(String name) {
		this.name = name;
		return this;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public Challenge setAccepted(boolean accepted) {
		this.accepted = accepted;
		return this;
	}
	public Long getOpponentGroupId() {
		return opponentGroupId;
	}
	public Challenge setOpponentGroupId(Long opponentGroupId) {
		this.opponentGroupId = opponentGroupId;
		return this;
	}
	
}
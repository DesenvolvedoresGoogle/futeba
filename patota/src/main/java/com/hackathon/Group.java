package com.hackathon;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Group {
	
	@Id
	private Long id;
	private String name;
	private List<String> members = new ArrayList<String>();
	@Index
	private String userEmail;
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Group setName(String name) {
		this.name = name;
		return this;
	}
	
	public void addMember(String memberId) {
		this.members.add(memberId);
	}
	
	public void removeMember(String memberId) {
		this.members.remove(memberId);
	}
	
	public List<String> getMembers() {
		return members;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public Group setUserEmail(String userEmail) {
		this.userEmail = userEmail;
		return this;
	}

}

package com.hackathon;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class GroupMember {
	
	@Id
	private String id;
	private String name;
	private boolean keeper;
	private int abilityLevel;
	private List<Long> groups = new ArrayList<Long>();
	private boolean confirmed;
	
	// Google API requires that
	public GroupMember() {
	}
	
	public GroupMember(String googleId) {
		setId(googleId);
	}
	
	public String getId() {
		return id;
	}
	
	private GroupMember setId(String id) {
		this.id = id;
		return this;
	}
	
	public String getName() {
		return name;
	}
	
	public GroupMember setName(String name) {
		this.name = name;
		return this;
	}
	
	public GroupMember setAbilityLevel(int abilityLevel) {
		this.abilityLevel = abilityLevel;
		return this;
	}
	
	public int getAbilityLevel() {
		return abilityLevel;
	}
	
	public boolean isKeeper() {
		return keeper;
	}
	
	public GroupMember setKeeper(boolean keeper) {
		this.keeper = keeper;
		return this;
	}
	
	public List<Long> getGroups() {
		return groups;
	}
	
	public void addGroup(Long groupId) {
		groups.add(groupId);
	}
	
	public void removeGroup(Long groupId) {
		groups.remove(groupId);
	}
	
	public boolean isConfirmed() {
		return confirmed;
	}
	
	public GroupMember setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
		return this;
	}

}

package com.hackathon;

import java.util.List;

// We can not return lists, so...
class GroupBulk {
	
	private List<Group> groups;
	
	public GroupBulk(List<Group> groups) {
		this.groups = groups;
	}
	
	public List<Group> getGroups() {
		return groups;
	}
	
}

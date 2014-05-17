package model;

import java.util.ArrayList;
import java.util.List;

public class Group {
	
	private Long id;
	private String name;
	private List<String> members = new ArrayList<String>();
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

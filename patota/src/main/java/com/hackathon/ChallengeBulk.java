package com.hackathon;

import java.util.List;

//We can not return lists, so...
class ChallengeBulk {
	
	private List<Challenge> challenges;
	
	public ChallengeBulk(List<Challenge> challenges) {
		this.challenges = challenges;
	}
	
	public List<Challenge> getChallenges() {
		return challenges;
	}
	
}
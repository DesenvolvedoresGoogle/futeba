package com.fglp.futeba;

public class ChallengeGroup {

	public final String groupName;

	public final long groupId;

	public final double distance;

	public final long challengeId;

	public ChallengeGroup(long challengeId, String groupName, long groupId,
			double distance) {
		this.groupName = groupName;
		this.groupId = groupId;
		this.distance = distance;
		this.challengeId = challengeId;

	}

}
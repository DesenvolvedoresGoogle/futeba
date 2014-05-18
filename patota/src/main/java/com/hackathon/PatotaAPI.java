package com.hackathon;

import java.util.Date;
import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

@Api(name = "api", 
	 version = "v1", 
	 scopes = { Constants.EMAIL_SCOPE }, 
	 clientIds = {
		Constants.WEB_CLIENT_ID, 
		Constants.ANDROID_CLIENT_ID,
		Constants.IOS_CLIENT_ID, 
		Constants.API_EXPLORER_CLIENT_ID }, 
		audiences = { Constants.ANDROID_AUDIENCE })

public class PatotaAPI {

	static {
		ObjectifyService.register(Group.class);
		ObjectifyService.register(GroupMember.class);
		ObjectifyService.register(Event.class);
		ObjectifyService.register(Challenge.class);
	}

	public Group getGroup(User user, @Named("groupId") Long id) throws UnauthorizedException {
		if (user == null) {
			// Returns status code 401.
			throw new UnauthorizedException("Authorization required");
		}
		return getGroup(id);
	}

	private Group getGroup(Long id) {
		Objectify ofy = ObjectifyService.ofy();
		return ofy.load().type(Group.class).id(id).now();
	}
	
	@ApiMethod(httpMethod = "post")
	public Group insertGroup(User user, @Named("groupName") String name) throws UnauthorizedException {
		if (user == null) {
			// Returns status code 401.
			throw new UnauthorizedException("Authorization required");
		}

		Objectify ofy = ObjectifyService.ofy();
		Group g = new Group().setName(name).setUserEmail(user.getEmail());
		ofy.save().entities(g).now();
		return g;
	}

	public GroupBulk allGroups(User user) throws UnauthorizedException {
		if (user == null) {
			// Returns status code 401.
			throw new UnauthorizedException("Authorization required");
		}

		Objectify ofy = ObjectifyService.ofy();
		List<Group> groups = ofy.load().type(Group.class).filter("userEmail ==", user.getEmail()).list();
		return new GroupBulk(groups);
	}
	
	@ApiMethod(httpMethod = "post")
	public void addMember(User user, @Named("groupId") Long groupId, GroupMember member) throws UnauthorizedException {
		if (user == null) {
			// Returns status code 401.
			throw new UnauthorizedException("Authorization required");
		}
		Objectify ofy = ObjectifyService.ofy();
		
		GroupMember gm = ofy.load().type(GroupMember.class).id(member.getId()).now();
		if (gm == null) {
			gm = member;
		}
		gm.addGroup(groupId);
		ofy.save().entities(member);
		
		Group g = getGroup(groupId);
		g.addMember(member.getId());
		ofy.save().entities(g);
	}
	
	public DateBulk now() {
		return new DateBulk();
	}

	@ApiMethod(httpMethod = "post")
	public void removeMember(User user, @Named("groupId") Long groupId, GroupMember member) throws UnauthorizedException {
	
		if (user == null) {
			// Returns status code 401.
			throw new UnauthorizedException("Authorization required");
		}

		Objectify ofy = ObjectifyService.ofy();
		
		GroupMember gm = ofy.load().type(GroupMember.class).id(member.getId()).now();
		gm.removeGroup(groupId);
		ofy.save().entities(member);
		
		Group g = getGroup(groupId);
		g.removeMember(member.getId());
		ofy.save().entities(g);
	}
	
	@ApiMethod(httpMethod = "post")
	public Event addEvent(User user, Event event) throws UnauthorizedException {
		
		if (user == null) {
			// Returns status code 401.
			throw new UnauthorizedException("Authorization required");
		}

		Objectify ofy = ObjectifyService.ofy();
		ofy.save().entity(event);
		return event;
		
	}

	@ApiMethod(httpMethod = "post")
	public Challenge addChallenge(User user, Challenge challenge) throws UnauthorizedException {
		
		if (user == null) {
			// Returns status code 401.
			throw new UnauthorizedException("Authorization required");
		}

		Objectify ofy = ObjectifyService.ofy();
		ofy.save().entity(challenge);
		return challenge;
	}
	
	public ChallengeBulk allChallenges(User user) throws UnauthorizedException {
		if (user == null) {
			// Returns status code 401.
			throw new UnauthorizedException("Authorization required");
		}

		Objectify ofy = ObjectifyService.ofy();
		List<Challenge> challenges = ofy.load().type(Challenge.class).filter("accepted != ", true).list();
		return new ChallengeBulk(challenges);
	}

	@ApiMethod(httpMethod = "post")
	public Challenge acceptChallenge(User user, @Named("groupId") Long groupId, @Named("challengeId") Long challengeId) throws UnauthorizedException {
		
		if (user == null) {
			// Returns status code 401.
			throw new UnauthorizedException("Authorization required");
		}
		Objectify ofy = ObjectifyService.ofy();

		Challenge challenge = ofy.load().type(Challenge.class).id(challengeId).now();
		challenge.setAccepted(true);
		challenge.setOpponentGroupId(groupId);
		
		ofy.save().entity(challenge);
		return challenge;
		
	}

}

class DateBulk {
	Date date = new Date();
	
	public Date getDate() {
		return date;
	}
}
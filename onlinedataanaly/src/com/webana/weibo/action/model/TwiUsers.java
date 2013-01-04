package com.webana.weibo.action.model;

public class TwiUsers {

	private String screenName;
	private String userType;
	private String gender;
	private String location;
	private int commentCount;// comments number of this tweet
	private int repostCount;// repost count of this tweet
	private int followers;// followers of this user
	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getRepostCount() {
		return repostCount;
	}

	public void setRepostCount(int repostCount) {
		this.repostCount = repostCount;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	
}

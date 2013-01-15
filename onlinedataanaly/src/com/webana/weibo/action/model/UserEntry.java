package com.webana.weibo.action.model;

import java.util.Comparator;
import java.util.List;

public class UserEntry {

	private String id;

	String gov;
	String screenNames;	
	int statusCount;
	
	int dailyStatusCount;
	long dailyOriginCount;
	int dailyRepostCount;
	int followers;
	int followersYesterday;
	
	TopicEntry topic = new TopicEntry();
	
	public Object[] toArray(){	
		Object[] array = {gov, screenNames, statusCount, dailyStatusCount, dailyOriginCount, dailyRepostCount, followers, followersYesterday,
				topic.hotTopic, topic.hotTopicRepostCount, topic.hotTopicCommentCount, "", "", "", "", ""};
		return array;
	}
	
	public Object[] toHotTopicArray(){	
		Object[] array = {screenNames, topic.hotTopic, topic.hotTopicRepostCount, topic.hotTopicCommentCount};
		return array;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getGov() {
		return gov;
	}

	public void setGov(String gov) {
		this.gov = gov;
	}

	public String getScreenNames() {
		return screenNames;
	}

	public void setScreenNames(String screenNames) {
		this.screenNames = screenNames;
	}

	public int getStatusCount() {
		return statusCount;
	}

	public void setStatusCount(int statusCount) {
		this.statusCount = statusCount;
	}

	public int getDailyStatusCount() {
		return dailyStatusCount;
	}

	public void setDailyStatusCount(int dailyStatusCount) {
		this.dailyStatusCount = dailyStatusCount;
	}

	public long getDailyOriginCount() {
		return dailyOriginCount;
	}

	public void setDailyOriginCount(long dailyOriginCount) {
		this.dailyOriginCount = dailyOriginCount;
	}

	public int getDailyRepostCount() {
		return dailyRepostCount;
	}

	public void setDailyRepostCount(int dailyRepostCount) {
		this.dailyRepostCount = dailyRepostCount;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public int getFollowersYesterday() {
		return followersYesterday;
	}

	public void setFollowersYesterday(int followersYesterday) {
		this.followersYesterday = followersYesterday;
	}

	public TopicEntry getTopic() {
		return topic;
	}

	public void setTopic(TopicEntry topic) {
		this.topic = topic;
	}

	
}

package com.webana.weibo.action.model;

import java.util.ArrayList;
import java.util.List;

public class Tweet {

	private String twiMid;

	private String text;
	
	private int commentCount;
	
	private int repostCount;
	
	private List<TwiUsers> users = new ArrayList<TwiUsers>();

	public Tweet(String twiMid) {
		this.twiMid = twiMid;
	}
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public List<TwiUsers> getUsers() {
		return users;
	}

	public void setUsers(List<TwiUsers> users) {
		this.users = users;
	}
	
	public String getTwiMid() {
		return twiMid;
	}

	public void setTwiMid(String twiMid) {
		this.twiMid = twiMid;
	}
}

package com.webana.weibo.action.model;

public class TopicEntry {
	
	String hotTopic = "";
	int hotTopicRepostCount;
	int hotTopicCommentCount;
	
	public String getHotTopic() {
		return hotTopic;
	}
	public void setHotTopic(String hotTopic) {
		this.hotTopic = hotTopic;
	}
	public int getHotTopicRepostCount() {
		return hotTopicRepostCount;
	}
	public void setHotTopicRepostCount(int hotTopicRepostCount) {
		this.hotTopicRepostCount = hotTopicRepostCount;
	}
	public int getHotTopicCommentCount() {
		return hotTopicCommentCount;
	}
	public void setHotTopicCommentCount(int hotTopicCommentCount) {
		this.hotTopicCommentCount = hotTopicCommentCount;
	}
	
}

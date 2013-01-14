package com.webana.weibo.action.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webana.weibo.action.model.Tweet;
import com.webana.weibo.action.model.TwiUsers;

import weibo4j.Timeline;
import weibo4j.model.Paging;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

/**
 * 
 * @author Andrew
 * 
 */

public class WeiboService {
	
	private static final Logger logger = LoggerFactory.getLogger(WeiboService.class);


	public static final int MAX_NUM_EACH_PAGE = 200;
	public static final int MAX_THREAD_NUM = 5;
	public static final int MIN_ = 5;
	
	private Timeline tm;

	private Tweet twi;

	private String tweetId;
	
	private int finishedPages = 1;
	
	private List<String> mediaList;
	
	public WeiboService(String token) {
		tm = new Timeline();
		tm.setToken(token);		
	}
	
	public void queryTweet(String twiMid, List<String> mediaList) {
		this.mediaList = mediaList;
		try {
			JSONObject tweet = tm.QueryId(twiMid, 1, 1);
			this.tweetId = tweet.getString("id").toString();
			this.twi = new Tweet(twiMid);
			queryTweet(1);// query first page
			int totalNumber = twi.getRepostCount();
			
			ExecutorService service = Executors.newFixedThreadPool(MAX_THREAD_NUM);
			for(int pageNo = 2; pageNo < (totalNumber / MAX_NUM_EACH_PAGE) + 2; pageNo++) {
				service.execute(new Worker(pageNo));
			}
			service.shutdown();
		} catch (WeiboException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	class Worker implements Runnable {

		private int pageNo;

		public Worker(int pageNo) {
			this.pageNo = pageNo;
		}
		@Override
		public void run() {
			queryTweet(pageNo);
		}
	}

	private void queryTweet(int pageNo) {
		try {
			StatusWapper statusWrap = tm.getRepostTimeline(tweetId, new Paging(pageNo, MAX_NUM_EACH_PAGE));
			List<Status> stas = statusWrap.getStatuses();

			if(stas.size()>0 && pageNo == 1) {
				int commentCount = stas.get(0).getRetweetedStatus().getCommentsCount();
				int repostCount = stas.get(0).getRetweetedStatus().getRepostsCount();
				twi.setCommentCount(commentCount);
				twi.setRepostCount(repostCount);
				twi.setText(stas.get(0).getRetweetedStatus().getText());
			}
			for (Status status : stas) {
				User user = status.getUser();
				if(user == null) {
					continue;
				}
				TwiUsers tu = new TwiUsers();
				tu.setScreenName(user.getScreenName());
				tu.setCommentCount(status.getCommentsCount());
				tu.setRepostCount(status.getRepostsCount());
				tu.setFollowers(user.getFollowersCount());
				tu.setGender(user.getGender());
				tu.setLocation(user.getLocation());
				if("新京报".equals(user.getScreenName()))
				System.out.println(user.getScreenName());
				if (mediaList.contains(user.getScreenName())) {
					tu.setUserType("媒体用户");
				} else if(user.isVerified()) {
					tu.setUserType("认证用户");
				} else {
					tu.setUserType("普通用户");
				}
				twi.getUsers().add(tu);
			}			
		} catch (WeiboException e) {
			e.printStackTrace();
		} finally {
			finishedPages++;
		}
	}
	
	public int getProgress() {
		return  (MAX_NUM_EACH_PAGE*finishedPages) * 100 / twi.getRepostCount();
	}

	public Tweet getTwi() {
		return twi;
	}
}

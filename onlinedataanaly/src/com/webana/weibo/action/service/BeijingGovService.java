package com.webana.weibo.action.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webana.weibo.action.model.TopicEntry;
import com.webana.weibo.action.model.UserEntry;
import com.webana.weibo.excel.ExcelWriter;
import com.webana.weibo.excel.FileUtil;

import weibo4j.Account;
import weibo4j.Timeline;
import weibo4j.Users;
import weibo4j.Weibo;
import weibo4j.model.Paging;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

/**
 * 
 * @author Andrew
 * 
 */

public class BeijingGovService {
	private String accessToken;
	private Weibo weibo;
	Users um;
	Timeline tm;
	Account am;
	int dayOfstat = 1;
	private static final Logger logger = LoggerFactory.getLogger(BeijingGovService.class);
	public static final int MAX_NEW_NUM = 120;
	public static final String LIST_CONFIG_STARTWITH = "sheet";
	
	private int finishedUsersCount;
	
	private int totalUsersCount = 1;
	private boolean finished;
	
	private String newFileName;

	public BeijingGovService(String token) {
		this.accessToken = token;
		weibo = new Weibo();
		weibo.setToken(accessToken);
		um = new Users();
		tm = new Timeline();
		am = new Account();
	}

	private UserEntry getUserByScreenName(String sn) {
		logger.info("fetch:" + sn);
		User user = null;
		try {
			user = um.showUserByScreenName(sn);
		} catch (WeiboException e) {
			if (e.getErrorCode() == 20003) {
				logger.error("can't find this user");
				e.printStackTrace();
			}
			return null;
		}
		UserEntry entry = new UserEntry();
		entry.setId(user.getId());
		entry.setScreenNames(sn);
		entry.setFollowers(user.getFollowersCount());
		entry.setStatusCount(user.getStatusesCount());
		String gov = user.getVerifiedReason();
		if (gov != null)
			entry.setGov(gov.replaceAll("官方微博", ""));
		return entry;
	}

	private void statWeibo(UserEntry ue, int dayofstat) {
		StatusWapper statusWrap;
		try {
			statusWrap = tm.getUserTimelineByUid(ue.getId(), new Paging(1, MAX_NEW_NUM),
					0, 0);
		} catch (WeiboException e) {
			e.printStackTrace();
			return;
		}
		List<Status> stas = statusWrap.getStatuses();
		int dailyOriginTopicCount = 0;
		int dailyretweetedCount = 0;
		int maxTotalCount = -1;
		for (Status status : stas) {
			Date createdDate = status.getCreatedAt();
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -dayofstat);
			Date yesterday = cal.getTime();
			if (createdDate.after(yesterday)) {
				if (status.getRetweetedStatus() == null) {
					dailyOriginTopicCount++;
				} else {
					dailyretweetedCount++;
				}

				int commentCount = status.getCommentsCount();
				int repostCount = status.getRepostsCount();
				if (maxTotalCount < commentCount + repostCount) {
					maxTotalCount = commentCount + repostCount;
					TopicEntry te = new TopicEntry();
					te.setHotTopic(status.getText());
					te.setHotTopicCommentCount(commentCount);
					te.setHotTopicRepostCount(repostCount);
					ue.setTopic(te);
				}
			}
		}
		ue.setDailyOriginCount(dailyOriginTopicCount);
		ue.setDailyRepostCount(dailyretweetedCount);
		ue.setDailyStatusCount(dailyretweetedCount + dailyOriginTopicCount);
	}

	public List<UserEntry> queryByScreenName(List<String> screenNames) {
		logger.info("queryByScreenName");
		List<UserEntry> entries = new ArrayList<UserEntry>();		
		for (String sn : screenNames) {
			UserEntry ue = getUserByScreenName(sn);
			if (ue != null) {
				this.statWeibo(ue, dayOfstat);
				entries.add(ue);
			}
			this.finishedUsersCount++;
		}
		return entries;
	}
	
	public void setDayOfstat(int dayOfstat) {
		this.dayOfstat = dayOfstat;
	}
	
	
	private List<File> listGovListFiles(String rootPath){		
		List<File> files = FileUtil.listFiles(rootPath + File.separator + ExcelWriter.GOV_LIST_DIR, "txt", LIST_CONFIG_STARTWITH);
		return files;
	}
	
	public void generateStatExcel(final String rootPath) {
		logger.info("generateStatExcel.....");
		new Thread() {
			public void run() {
				List<List<String>> sheets = new ArrayList<List<String>>();
				List<File> files = listGovListFiles(rootPath);
				for (File file:files){
					sheets.add(FileUtil.readFileByLines(file.getAbsolutePath()));
				}
				ExcelWriter ew = new ExcelWriter();
				ew.initExcel(rootPath, dayOfstat);
				List<UserEntry> totalEntries = new ArrayList<UserEntry>();
				for (int n = 0; n < sheets.size(); n++) {
					List<String> screenNames = sheets.get(n);
					totalUsersCount += screenNames.size();
				}
				for (int n = 0; n < sheets.size(); n++) {
					List<String> screenNames = sheets.get(n);
					List<UserEntry> ues = queryByScreenName(screenNames);
					int sheetIndex = n;
					ew.writeUserEntry(ues, sheetIndex);
					totalEntries.addAll(ues);
				}
				ew.writeMostHotTopics(totalEntries);//write all of hot topics.
				try {
					newFileName = ew.writeToFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				finished = true;
			}
		}.start();
		
	}

	public int getProgress() {
		if(this.finished) {
			return 100;
		}
		return  (finishedUsersCount) * 100 / totalUsersCount;
	}
	
	public String getNewFileName() {
		return newFileName;
	}


}

package com.webana.weibo.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;

import com.webana.weibo.action.model.PieChart;
import com.webana.weibo.action.model.Tweet;
import com.webana.weibo.action.service.TwiToChart;
import com.webana.weibo.action.service.TwiService;
import com.webana.weibo.util.Constants;
import com.webana.weibo.util.TweetPersistence;
import com.webana.weibo.util.WeiboUtil;

/**
 *
 * @author
 */
public class TwiAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private String twiMid;
    
    private String uid;
    
    private String twiText;
    
    private List<PieChart> userTypeData;
    
    private List<PieChart> userGenderData;
    
    private List<PieChart> repostRatioData;
    
    private List<PieChart> locationData;
    
    private List<PieChart> topRepostData;
    
    private List<PieChart> topFollowersData;
    
    private List<String> verifiedUsers;

    private int progress;

    TwiService weiboService;
    
    Resource  mediaListFile;
    
    private String errorMsg;

	public static final int MAX_NEW_NUM = 200;

	/**
     * Cancel a running build.
     * @return forward to build status
     */
    public String execute() {
    	progress = 0;
    	weiboService = null;
    	this.errorMsg = null;
        return INPUT;
    }

	public String analysis() {
    	this.errorMsg = null;
		weiboService = super.createTwiService();
		if (weiboService == null) {
			errorMsg = "请先使用微博账号登录才能使用分析功能";
			return "ajax";
		}
		List<String> medias = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					mediaListFile.getInputStream()));
			String line = reader.readLine();
			while (line != null) {
				medias.add(line);
				line = reader.readLine();
			}
			weiboService.queryTweet(twiMid, this.uid, medias);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "ajax";
	}

	public String progress() {
		Tweet twi = null;
		String twiPath = getResourceRootPath() + Constants.TWIT_FILE_DIR;
		if (weiboService == null) {
			twi = new TweetPersistence().readJson2Entity(twiPath + File.separator + twiMid + ".txt");
			progress = 100;
		} else {
			progress = weiboService.getProgress();
			if (progress < 100) {
				return "ajax";
			}
			twi = weiboService.getTwi();
			if (twi != null) {
				new TweetPersistence().writeEntityJSON(twi, twiPath + File.separator + twi.getTwiMid() + ".txt");
			}
		}
		if (twi != null) {
			TwiToChart ttc = new TwiToChart(twi);
			ttc.analysis();
			twiText = twi.getText();
			userTypeData = ttc.getUserTypeChart();
			userGenderData = ttc.getUserGenderChart();
			repostRatioData = ttc.getRepostRatioChart();
			locationData = ttc.getUserLocationChart();
			topRepostData = ttc.getTopRepostChart();
			topFollowersData = ttc.getTopFollowersChart();
			verifiedUsers = ttc.getVerifiedUsers();
			// TwiDao dao = TwiDao.getInstance();
			// dao.addTwi(twi);
		}

		return "ajax";
	}

    public String chart(){
    	weiboService = null;
    	progress = 0;
    	this.errorMsg = null;
    	return "ajax";
    }

    public void setTwiMid(String twiMid) {		
		String[] ids = WeiboUtil.urlToTwiMidUid(twiMid);
		this.uid = ids[0];
		this.twiMid = ids[1];
	}

    public List<PieChart> getUserTypeData() {
		return userTypeData;
	}

	public List<PieChart> getLocationData() {
		return locationData;
	}

	public int getProgress() {
		return progress;
	}

	public String getTwiText() {
		return twiText;
	}

	public List<PieChart> getUserGenderData() {
		return userGenderData;
	}

	public List<PieChart> getRepostRatioData() {
		return repostRatioData;
	}

	public List<PieChart> getTopRepostData() {
		return topRepostData;
	}

	public List<PieChart> getTopFollowersData() {
		return topFollowersData;
	}

	public List<String> getVerifiedUsers() {
		return verifiedUsers;
	}

	public void setMediaListFile(Resource mediaListFile) {
		this.mediaListFile = mediaListFile;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}

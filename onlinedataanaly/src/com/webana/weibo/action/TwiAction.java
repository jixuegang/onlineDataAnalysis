package com.webana.weibo.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;

import com.webana.weibo.action.model.PieChart;
import com.webana.weibo.action.model.Tweet;
import com.webana.weibo.action.service.TwiToChart;
import com.webana.weibo.action.service.TwiService;

/**
 *
 * @author
 */
public class TwiAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private String twiMid;
    
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

	public static final int MAX_NEW_NUM = 200;

	/**
     * Cancel a running build.
     * @return forward to build status
     */
    public String execute() {
       
        return INPUT;
    }

    public String analysis() {
    	weiboService = super.createTwiService();
    	if(weiboService == null) {
    		this.addActionError("请先使用微博账号登录才能查询使用查询");
    	} else {
    		List<String> medias = new ArrayList<String>();
    	    try {
				BufferedReader	reader = new BufferedReader(new InputStreamReader(mediaListFile.getInputStream()));
				String line = reader.readLine();
	            while (line!=null) {
	            	medias.add(line);
	                line = reader.readLine();
	            }
	    		weiboService.queryTweet(twiMid, medias);
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
    	}
    	return "ajax";
    }

    public String progress() {
    	progress = weiboService.getProgress();
    	if(progress >= 100) {
    		Tweet twi = weiboService.getTwi();
        	if(twi != null) {
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
//        		TwiDao dao = TwiDao.getInstance();
//        		dao.addTwi(twi);
        	}
    	}
    	return "ajax";
    }

    public void setTwiMid(String twiMid) {
		this.twiMid = twiMid;
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

}

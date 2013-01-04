package com.webana.weibo.action;

import java.util.List;

import com.webana.weibo.action.model.PieChart;
import com.webana.weibo.action.model.Tweet;
import com.webana.weibo.action.service.TwiToChart;
import com.webana.weibo.action.service.WeiboService;

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
    
    WeiboService weiboService;

	public static final int MAX_NEW_NUM = 200;

	/**
     * Cancel a running build.
     * @return forward to build status
     */
    public String execute() {
       
        return INPUT;
    }

    public String analysis() {
    	weiboService = super.createWeiBoService();
    	if(weiboService == null) {
    		this.addActionError("请先使用微博账号登录才能查询使用查询");
    	} else {
    		weiboService.queryTweet(twiMid);
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

}

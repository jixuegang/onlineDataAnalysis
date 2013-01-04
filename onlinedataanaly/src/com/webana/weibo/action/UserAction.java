package com.webana.weibo.action;

import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;

import com.webana.weibo.action.service.OAuth4Code;


/**
 * Cancel running build action.
 *
 * @author
 */
public class UserAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    private String code;
    
    public String execute() {
    	OAuth4Code auth = new OAuth4Code();
    	AccessToken accessToken = auth.authorize(code);
		if (accessToken !=null){
			try {
				super.getSession().setAttribute("accessToken", accessToken.getAccessToken());
				String screenName = super.getUsers().showUserById(accessToken.getUid()).getScreenName();
				super.getSession().setAttribute("screenName", screenName);
			} catch (WeiboException e) {
				e.printStackTrace();
			}
		}else {
			
		}
        return SUCCESS;
    }
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}

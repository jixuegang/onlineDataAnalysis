package com.webana.weibo.action.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
/**
 * 
 * @author Andrew
 *
 */
public class OAuth4Code {
	Oauth oauth;
	private static final Logger logger = LoggerFactory.getLogger(OAuth4Code.class);
	public OAuth4Code(){
		oauth = new Oauth();
	}
	
	public String getAccessUrl() throws WeiboException {
		return oauth.authorize("code", "onlinedataanaly");
	}

	public AccessToken authorize(String code) {
		try {
			return oauth.getAccessTokenByCode(code);
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		
		return 	null;
	}

}

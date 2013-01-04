package com.webana.weibo.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import weibo4j.Timeline;
import weibo4j.Users;
import weibo4j.Weibo;

import com.opensymphony.xwork2.ActionSupport;
import com.webana.weibo.action.service.WeiboService;

/**
 * Base action to provide basic functions for all struts action.
 *
 * @author 28851470
 *
 */
public class BaseAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

   protected HttpSession getSession() {
	   return ServletActionContext.getRequest().getSession();
   }
   
   protected Users getUsers() {
	   if (this.getSession().getAttribute("accessToken") == null) {
		   return null;
	   }
	   String token = this.getSession().getAttribute("accessToken").toString();
	   Users um = new Users();
	   um.setToken(token);
	   return um;
   }

   protected Timeline getTimeline() {
	   if (this.getSession().getAttribute("accessToken") == null) {
		   return null;
	   }
	   String token = this.getSession().getAttribute("accessToken").toString();
	   Timeline tl = new Timeline();
	   tl.setToken(token);
	   return tl;
   }
   
   protected WeiboService createWeiBoService() {
	   if (this.getSession().getAttribute("accessToken") == null) {
		   return null;
	   }
	   String token = this.getSession().getAttribute("accessToken").toString();
	   WeiboService service = new WeiboService(token);
	   return service;
   }
}

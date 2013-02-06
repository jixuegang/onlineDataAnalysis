package com.webana.weibo.action;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import weibo4j.Timeline;
import weibo4j.Users;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.webana.weibo.action.service.BeijingGovService;
import com.webana.weibo.action.service.TwiService;

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
   
   protected TwiService createTwiService() {
	   if (this.getSession().getAttribute("accessToken") == null) {
		   return null;
	   }
	   String token = this.getSession().getAttribute("accessToken").toString();
	   TwiService service = new TwiService(token);
	   return service;
   }
   
   protected BeijingGovService createBeijingGovService() {
	   if (this.getSession().getAttribute("accessToken") == null) {
		   return null;
	   }
	   String token = this.getSession().getAttribute("accessToken").toString();
	   BeijingGovService service = new BeijingGovService(token);
	   return service;
   }
   
   protected void setApplicationAttibute(String name, Object value) {
	   ActionContext.getContext().getApplication().put(name, value);
   }
   
   protected Object getApplicationAttibute(String name) {
	   return ActionContext.getContext().getApplication().get(name);
   }
   
   protected String getResourceRootPath() {
		return ServletActionContext.getServletContext().getRealPath("/") + "resources"  + File.separator;
   }
}

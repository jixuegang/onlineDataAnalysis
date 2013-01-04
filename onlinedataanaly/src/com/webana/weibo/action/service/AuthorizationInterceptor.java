package com.webana.weibo.action.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Struts interceptor to verify user status.
 * @author 28851470
 *
 */
public class AuthorizationInterceptor extends AbstractInterceptor {

    /**
     * To make checkstyle happy.
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        Map<String, Object> session = ai.getInvocationContext().getSession();
//        HttpServletRequest request = ServletActionContext.getRequest();
//        String currentURL = request.getRequestURI();
//        if (request.getQueryString() != null) {
//            currentURL += "?" + request.getQueryString();
//        }
        if(session.get("screenName") == null) {
        	session.put("loginurl", new OAuth4Code().getAccessUrl());
        }
        return ai.invoke();
        
    }

}

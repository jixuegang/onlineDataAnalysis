package com.webana.weibo.action;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;

import com.webana.weibo.action.model.PieChart;
import com.webana.weibo.action.model.Tweet;
import com.webana.weibo.action.service.TwiToChart;
import com.webana.weibo.action.service.WeiboService;

/**
 *
 * @author
 */
public class BeijingGovAction extends BaseAction {

    private static final long serialVersionUID = 1L;

	/**
     * @return forward to build status
     */
    public String execute() {
       
        return INPUT;
    }



}

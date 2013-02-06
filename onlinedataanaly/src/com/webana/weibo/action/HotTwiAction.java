package com.webana.weibo.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import com.webana.weibo.action.model.Tweet;
import com.webana.weibo.excel.FileUtil;
import com.webana.weibo.util.Constants;
import com.webana.weibo.util.TweetPersistence;

/**
 *
 * @author
 */
public class HotTwiAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    
    private List<Tweet> twis = new ArrayList<Tweet>();

    @Override
	public String execute() {
		String twiPath = getResourceRootPath() + Constants.TWIT_FILE_DIR;
		TreeMap<String, String> filenames = FileUtil.listFilesWithTime(twiPath, "txt");
		Set<String> fns = filenames.descendingKeySet();
		Iterator<String> iter = fns.iterator();
		int i = 0;
		while(iter.hasNext() && i++ < 20){
			String filename = filenames.get(iter.next());
			Tweet twi = new TweetPersistence().readJson2Entity(twiPath + File.separator + filename);
			if (twi != null) {
				twis.add(twi);
			}
		}
        return INPUT;
    }

	public List<Tweet> getTwis() {
		return twis;
	}

	public void setTwis(List<Tweet> twis) {
		this.twis = twis;
	}

}

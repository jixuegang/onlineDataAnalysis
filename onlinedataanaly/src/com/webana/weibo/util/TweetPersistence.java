package com.webana.weibo.util;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.webana.weibo.action.model.Tweet;
import com.webana.weibo.excel.FileUtil;

public class TweetPersistence {

	private ObjectMapper objectMapper = new ObjectMapper();

	public void writeEntityJSON(Tweet bean, String fileName) {
		try {
			File file = new File(fileName);
			objectMapper.writeValue(file, bean);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Tweet readJson2Entity(String fileName) {
		File file = new File(fileName);
		if(!file.exists()) {
			return null;
		}
		String json = FileUtil.readTxtFile(file);
		try {
			Tweet acc = objectMapper.readValue(json, Tweet.class);
			return acc;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}


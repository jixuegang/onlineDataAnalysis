package com.webana.weibo.util;

public class WeiboUtil {

	public static String[] urlToTwiMidUid(String url) {
		if (url != null && url.contains("com")) {
			if (url.contains("?")) {
				return url.substring(url.indexOf("com/") + 4, url.indexOf("?")).split("/");
			}
			return url.substring(url.indexOf("com/") + 4).split("/");
		}
		return new String[]{"", url};
	}
	public static void main(String[] args) {
		System.out.println(urlToTwiMidUid("http://e.weibo.com/2276886594/zgHEkbkvY")[0]);
		System.out.println(urlToTwiMidUid("http://e.weibo.com/2276886594/zgHEkbkvY")[1]);
	}
}

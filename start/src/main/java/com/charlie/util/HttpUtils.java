package com.charlie.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestOperations;

public class HttpUtils {
	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	@Autowired
	private static RestOperations restOperations;

	/*
	 * public static Object httpGet(String url, Map<String, Object> param,
	 * Object object) { object = restOperations.getForEntity(url,
	 * object.getClass(), param); ResponseEntity<object.ge> token2 =
	 * restOperations.getForEntity(url, object.getClass(), param); WechatToken
	 * token = token2.getBody(); logger.info("httpGet request finish:" + url);
	 * return object; }
	 * 
	 * public static Object httpPost(String url, Map<String, Object> param,
	 * Object object) { object = restOperations.postForObject(url, "",
	 * object.getClass(), param); logger.info("httpPost request finish:" + url);
	 * return object; }
	 */
}

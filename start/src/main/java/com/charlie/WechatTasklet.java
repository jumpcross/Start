package com.charlie;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestOperations;

import com.charlie.dao.WechatTokenRepository;
import com.charlie.entity.WechatToken;

@Component
public class WechatTasklet {
	private static final Logger log = LoggerFactory.getLogger(WechatTasklet.class);
	String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type={grant_type}&appid={appid}&secret={secret}";
	@Autowired
	private WechatTokenRepository tokenRepository;
	@Autowired
	private RestOperations restOperations;

	@Scheduled(fixedRate = 2000) // Refresh every 110 minutes, max
	@Transactional // time is 120minutes
	private void getToken() {
		log.info("token update start");
		Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("grant_type", "client_credential");
		uriVariables.put("appid", "wxc07461720e0ac53d");
		uriVariables.put("secret", "aed8ee763d5078b744fc9ff28937b4cf");
		ResponseEntity<WechatToken> token2 = restOperations.getForEntity(getTokenUrl, WechatToken.class, uriVariables);
		WechatToken token = token2.getBody();
		token.setId(UUID.randomUUID().toString());
		token.setFlag("1");
		token.setReceivedDate(new Date());
		tokenRepository.setFlagFor("0");// update all the record's flag to 0

		tokenRepository.save(token);// save record which is the latest
		log.info("token update success" + token.getAccess_token());

	}
}

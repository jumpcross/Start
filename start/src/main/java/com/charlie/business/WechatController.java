package com.charlie.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestOperations;

import com.charlie.dao.CustomerDao;
import com.charlie.dao.WechatTokenRepository;
import com.charlie.entity.Customer;
import com.charlie.entity.IpList;
import com.charlie.entity.WechatToken;

@Controller
@RequestMapping("/wechat")
public class WechatController {
	private static Logger log = LoggerFactory.getLogger(WechatController.class);
	String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type={grant_type}&appid={appid}&secret={secret}";

	@Autowired
	private RestOperations restOperations;
	@Autowired
	WechatTokenRepository wechatTokenRepository;
	@Autowired
	CustomerDao cust;

	@RequestMapping("/getcallbackip")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		String token = wechatTokenRepository.findByFlag("1").get(0).getAccess_token();
		String url = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token={access_token}";
		Map<String, Object> map = new HashMap<>();
		map.put("access_token", token);
		ResponseEntity<IpList> response = restOperations.getForEntity(url, IpList.class, map);
		IpList ipList = response.getBody();
		model.addAttribute("name", name);
		List<Customer> list = cust.findByLastName("Bauer");
		if (!list.isEmpty()) {
			System.out.println(list.get(0));
		}

		return "tt";
	}

	@RequestMapping("/getToken")
	private String updateToke() {
		Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("grant_type", "client_credential");
		uriVariables.put("appid", "wxc07461720e0ac53d");
		uriVariables.put("secret", "aed8ee763d5078b744fc9ff28937b4cf");
		WechatToken token = restOperations.getForObject(getTokenUrl, WechatToken.class, uriVariables);
		log.info("update token: " + token.getAccess_token());
		return null;

	}

}

package com.charlie.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.charlie.dao.CustomerDao;
import com.charlie.entity.Customer;
import com.charlie.util.MailUtil;


@Controller
public class SendMailController {
	@Autowired
	MailUtil mailUtil;
    @RequestMapping("/sendMail")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", "send mail to 2571588134@qq.com,2279993496@qq.com /r/n subject: I love you");
         mailUtil.SendMail("Not forget you while running program.　", "I love you ", "2571588134@qq.com,2279993496@qq.com", "");
         
        return "tt";
    }
}

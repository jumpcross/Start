package com.charlie;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.charlie.dao.FamilyDao;
import com.charlie.util.format.FormatUtil;
import com.charlie.util.mail.MailUtil;


@Component
public class FamilyShedule {
	private static final Logger log = LoggerFactory.getLogger(FamilyShedule.class);
	@Autowired
	FamilyDao familyDao;
	@Autowired
	MailUtil mailUtil;
	 @Scheduled(fixedRate = 1*24*60*60*1000)//one day trigger one time
	public void sheduleTask(){
		 log.info("schedule trrigger");
		Date date1 = new Date();
		Date date2 = new Date(date1.getTime()-1*24*60*60*60*1000);
		List<FamilyMember> list = familyDao.findByBirthDayBetween(date2, date1);
		List<FamilyMember> listAll = (List<FamilyMember>) familyDao.findAll();
		for(FamilyMember member:list){
			 //mailUtil.SendMail("Happy Birthday　", "Happy birthday ", member.getMailAddress(), "");	//send by myself
		//notify all the familly
		     for(FamilyMember currMember:listAll){
		    	 if (member.getId() != currMember.getId()) {//don't send message to the guy to notify himself
		    		 mailUtil.SendMail(member.getName()+"'s birthday is on"+FormatUtil.dateFormate("MM-dd", member.getBirthDay()), "birthday notify ", currMember.getMailAddress(), "");	
				}
		    
		    	}	 
		}
	}


}

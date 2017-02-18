package com.charlie.util.mail;


import org.assertj.core.internal.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * author   : qianweifeng
 * date     : 16/4/9.
 * describe :
 */
@Component
public class MailUtil implements SmartLifecycle {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    private Logger logger = LoggerFactory.getLogger(MailUtil.class);


    private ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
    private final AtomicInteger count = new AtomicInteger(1);

    private MimeMessagePreparator Send(final String mailBody,String mailSubject,String receivpients, String fromWho) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(receivpients);
                message.setFrom(fromWho);
                message.setSubject(mailSubject);
//                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/report.vm", "UTF-8", model);
                message.setText(mailBody, true);
            }
        };
        return preparator;
    }


    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable runnable) {

    }
    /**
     * Sending email out
     * @param mailBody
     * @param mailSubject
     * @param to if it is null or blank, read from config file
     * @param from if it is null or blank, read from config file
     */
public void SendMail(String mailBody,String mailSubject,String to, String from){
	  logger.info("start send email and the index is " + count);
	  if (StringUtils.isEmpty(from)) {
		  from =  env.getProperty(ConfigConstant.EMAIL_FROM);
	}
	  if (StringUtils.isEmpty(to)) {
		  to =  env.getProperty(ConfigConstant.EMAIL_TO);
	}
	  if (!StringUtils.isEmpty(to)){
		  String address[] = to.split(",");
			for(String currAddress: address){
				javaMailSender.send(Send(mailBody,mailSubject,currAddress,from));
				 logger.info("send email to "+currAddress+" success");
		}
	  }
		
	 
   
   
}
    @Override
    public void start() {
        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    if (count.get() == 2) {
                        service.shutdown();
                        logger.info("the task is down");
                    }
                   // logger.info("start send email and the index is " + count);

                    //javaMailSender.send(Send("test !" + "*" + count.getAndIncrement()));

                    //logger.info("send email success");
                }catch (Exception e){
                    logger.error("send email fail" , e);
                }

            }
        },2000,2000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public int getPhase() {
        return 0;
    }
}

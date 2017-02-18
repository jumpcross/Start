package com.charlie.util.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

/**
 * author   : qianweifeng
 * date     : 16/4/9.
 * describe :
 */
@Configuration
public class MailConfig {

    @Autowired
    private Environment env;

    @Bean(name = "JavaMailSender")
    public JavaMailSender getSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(env.getProperty(ConfigConstant.EMAIL_FROM));
        javaMailSender.setHost(env.getProperty(ConfigConstant.EMAIL_HOST));
        javaMailSender.setPort(587);//①
        javaMailSender.setDefaultEncoding("UTF-8");
        Properties props = new Properties();//②
        props.setProperty("mail.smtp.host", env.getProperty(ConfigConstant.EMAIL_HOST));
        props.setProperty("mail.smtp.auth", "true");
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("2279993496@qq.com",env.getProperty(ConfigConstant.EMAIL_AUTH));
            }
        });
        javaMailSender.setSession(session);//③
        return javaMailSender;
    }

}

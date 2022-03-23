package org.mybatis.jpetstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private MailSender mailSender;


    // EmailControl에서 호출하는 메소드
    public void sendEmail(String toAddress, String fromAddress, String title, String content){

        SimpleMailMessage smm = new SimpleMailMessage();

        smm.setFrom(fromAddress);
        smm.setTo(toAddress);
        smm.setSubject(title);
        smm.setText(content);

        mailSender.send(smm);

    }

}
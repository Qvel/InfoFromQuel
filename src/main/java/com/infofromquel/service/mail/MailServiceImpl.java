package com.infofromquel.service.mail;

import com.infofromquel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;


@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender getJavaMailSender;

    @Override
    public void sendEmail(User user) {
        SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom("iamquel08@gmail.com");
            mail.setTo("iamquel08@gmail.com");
            mail.setSubject("test commit");
            mail.setText("Darova");
            getJavaMailSender.send(mail);

    }

    @Override
    public void sendHtmlEmail(User user,String template)  {
        MimeMessage mimeMessage = getJavaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            mimeMessage.setContent(template, "text/html");
            helper.setTo("iamquel08@gmail.com");
            helper.setSubject("This is the test message for testing gmail smtp server using spring mail");
            helper.setFrom("iamquel08@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        getJavaMailSender.send(mimeMessage);
    }

    @Override
    public String setLinksIntoMessage(String template, List<String> links){

        String correctTemplate = template;

        for(int i = 0 ; i<links.size() ; i++){
            correctTemplate = correctTemplate.replace(String.valueOf(i),links.get(i)) + " ";

        }

        return  correctTemplate;
    }
}

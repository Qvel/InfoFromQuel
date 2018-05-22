package com.infofromquel.service.mail;

import com.infofromquel.entity.EmailTemplates;
import com.infofromquel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * Service for sending emails
 * @author Serhii Zhuravlov
 */
@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender getJavaMailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender getJavaMailSender) {
        this.getJavaMailSender = getJavaMailSender;
    }

    /**
     * Method for sending message
     * @param user {@link User}
     * @param template {@link EmailTemplates}
     * @param subject message
     */
    @Override
    public void sendHtmlEmail(User user, EmailTemplates template, String subject)  {
        MimeMessage mimeMessage = getJavaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            mimeMessage.setContent(template.getTemplate(), "text/html");
            helper.setTo(user.getName());
            helper.setSubject(subject);
            helper.setFrom("iamquel08@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        getJavaMailSender.send(mimeMessage);
    }

    /**
     * Method to set into message links
     * @param template {@link EmailTemplates}
     * @param links list of links
     * @return {@link EmailTemplates} with links
     */
    @Override
    public EmailTemplates setLinksIntoMessage(EmailTemplates template, List<String> links){

        String correctTemplate = template.getTemplate();

        for(int i = 0 ; i<links.size() ; i++){
            correctTemplate = correctTemplate.replace(String.valueOf(i),links.get(i)) + " ";

        }

        return template;
    }
}

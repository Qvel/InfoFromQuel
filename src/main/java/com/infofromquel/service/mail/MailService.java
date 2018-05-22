package com.infofromquel.service.mail;

import com.infofromquel.entity.EmailTemplates;
import com.infofromquel.entity.User;

import java.util.List;

/**
 * Api for Mail Sender
 * @author Serhii Zhuravlov
 */
public interface MailService {

    void sendHtmlEmail(User user, EmailTemplates template, EmailTemplates subject);

    EmailTemplates setLinksIntoMessage(EmailTemplates template, List<String> links);

}

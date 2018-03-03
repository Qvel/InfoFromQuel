package com.infofromquel.service.mail;

import com.infofromquel.entity.EmailTemplates;
import com.infofromquel.entity.User;

import java.util.List;

public interface MailService {

    void sendHtmlEmail(User user, EmailTemplates template, String subject);

    String setLinksIntoMessage(String template, List<String> links);

}

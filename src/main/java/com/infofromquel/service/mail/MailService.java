package com.infofromquel.service.mail;

import com.infofromquel.entity.User;

import java.util.List;

public interface MailService {

    void sendEmail(User user);

    void sendHtmlEmail(User user,String template);

    String setLinksIntoMessage(String template, List<String> links);

}

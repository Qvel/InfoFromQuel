package com.infofromquel.config;

import com.infofromquel.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {


    @Bean
    public User getUser(){
        return new User(1,"Sergey",12);
    }
}

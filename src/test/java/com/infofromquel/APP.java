package com.infofromquel;

import com.infofromquel.config.SpringConfig;
import com.infofromquel.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class APP {

    public static void main(String[] args){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        User user = context.getBean(User.class);

        System.out.println("Hello " + user.getName());

    }
}

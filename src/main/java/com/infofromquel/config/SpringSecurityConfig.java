package com.infofromquel.config;

import com.infofromquel.service.security.AuthenticationUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.infofromquel.service.security")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationUsers authProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authProvider);
    }


    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                        .antMatchers("/resources/**").permitAll()
                        .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                        .antMatchers("/getUsers/**").permitAll()
                        .anyRequest().authenticated()
                .and()
                    .httpBasic()
                .and()
                   .formLogin().loginPage("/login").permitAll()
                   .usernameParameter("j_username")
                   .passwordParameter("j_password")
                .and()
                .csrf().disable()
                   .logout().permitAll()
                .and()
                    .exceptionHandling().accessDeniedPage("/permissionError");
    }

}

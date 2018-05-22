package com.infofromquel.config;

import com.infofromquel.service.security.AuthenticationUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration for Security part
 * <p>Scanning beans for com.infofromquel.service.security </p>
 * @author Serhii Zhuravlov
 */

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.infofromquel.service.security")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationUsers authProvider;

    @Autowired
    public SpringSecurityConfig(AuthenticationUsers authProvider) {
        this.authProvider = authProvider;
    }

    /**
     * Override spring security user fields mechanism
     * @param auth {@link AuthenticationManagerBuilder}
     * @throws Exception if spring have problems with configuration
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authProvider);
    }

    /**
     * Main configuration of access to resources
     * @param httpSecurity {@link HttpSecurity}
     * @throws Exception if spring have problems with configuration
     */
    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                        .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                        .antMatchers("/**").permitAll()
                .and()
                    .httpBasic()
                .and()
                   .formLogin().loginPage("/login").permitAll()
                   .usernameParameter("j_username")
                   .passwordParameter("j_password")
                .and()
                   .logout().logoutSuccessUrl("/?logout")
                .and()
                    .rememberMe().alwaysRemember(true)
                    .tokenValiditySeconds(1209600)
                .and()
                    .exceptionHandling().accessDeniedPage("/permissionError");

    }



}

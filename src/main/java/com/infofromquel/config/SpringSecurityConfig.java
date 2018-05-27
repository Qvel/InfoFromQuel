package com.infofromquel.config;

import com.infofromquel.service.security.AuthenticationUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration for Security part
 * <p>Scanning beans for com.infofromquel.service.security </p>
 * @author Serhii Zhuravlov
 */

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.infofromquel.service.security")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationUsers userDetailsService;

    @Autowired
    public SpringSecurityConfig(AuthenticationUsers userDetailsService) {
        this.userDetailsService = userDetailsService;
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
                   .failureUrl("/")
                .and()
                   .logout().logoutSuccessUrl("/?logout")
                .and()
                    .rememberMe().key("uniqueAndSecret").userDetailsService(userDetailsService)
                    .tokenValiditySeconds(1209600)
                .and()
                    .exceptionHandling().accessDeniedPage("/permissionError");

    }

    /**
     * Bean for password Encoder
     * @return {@link PasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean for {@link DaoAuthenticationProvider} where was set custom user datails service {@link AuthenticationUsers} and password encoder {@link BCryptPasswordEncoder}
     * @return {@link DaoAuthenticationProvider}
     */
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Override spring security user fields mechanism
     * @param auth {@link AuthenticationManagerBuilder}
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authProvider());
    }

}

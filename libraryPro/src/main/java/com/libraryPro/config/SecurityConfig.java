package com.libraryPro.config;

import com.libraryPro.service.UsersUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService usersUserDetailsService() {

        return new UsersUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider usersAuthenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usersUserDetailsService());
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(usersAuthenticationProvider());



    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().usernameParameter("name")
                .successHandler(loginSuccessHandler)
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/showAccessDeniedPage")
                .and()
                .logout().permitAll();
        System.out.println("Http Configure..........!!");

    }
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

}

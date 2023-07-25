package com.luv2code.springboot.demoSecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {
    //Add Jdbc support--no more hard coded the user
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        theUserDetailsManager.setUsersByUsernameQuery("select user_id,pw,active from members where user_id=? ");
        theUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id=?");
        return theUserDetailsManager;

        //this tells spring security to use jdbc authentication with our dataSource
        //By default it will take users and authorities table and mapped it.
    }
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails john = User.builder()
//                .username("john")
//                .password("{noop}test123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","MANAGER")
//                .build();
//
//        UserDetails susan = User.builder()
//                .username("susan")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","MANAGER","ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(john,mary,susan);
//    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form->
                        form.loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll())
                .logout(logout->logout.permitAll())
                .exceptionHandling(configurer->
                        configurer.accessDeniedPage("/access-denied"));

        return http.build();

    }
}

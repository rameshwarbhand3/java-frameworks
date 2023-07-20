package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

//Add Access based on roles
@Configuration
public class DemoSecurityConfig {
  //Add Jdbc user access ---no more hardcoded user
   //@Bean   //This is for default user and authentication
   //public UserDetailsManager userDetailsManager(DataSource dataSoure){
      //return new JdbcUserDetailsManager(dataSoure);//this tell spring to use jdbc authentication with datasource
   //}


    @Bean //This is for custom user and authorities table
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        //define query to retrieve user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id,pw,active from members where user_id=?");
        //define query to retrieve roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id=?");
        return jdbcUserDetailsManager;
    }
   //Restricting api access based on role
   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.authorizeHttpRequests(configurer ->
              configurer
                      .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                      .requestMatchers(HttpMethod.GET, "/api/employees/{employeeId}").hasRole("EMPLOYEE")
                      .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                      .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                      .requestMatchers(HttpMethod.DELETE, "/api/employees/{employeeId}").hasRole("ADMIN")
      );
      //Use HTTP basic authentication
      http.httpBasic(Customizer.withDefaults());
      //disable cross site request forgery(CSRF)
      //Generally not require for stateLess REST API  that uses PUT,POST,DELETE
      http.csrf(csrf -> csrf.disable());
      return http.build();
   }


   //Hard coded user and role based on authorization
   /*@Bean
   public InMemoryUserDetailsManager userDetailsManager() {
      UserDetails john = User.builder()
              .username("john")
              .password("{noop}test123")
              .roles("EMPLOYEE").build();

      UserDetails mary = User.builder()
              .username("marry")
              .password("{noop}test123")
              .roles("EMPLOYEE", "MANAGER").build();

      UserDetails susan = User.builder()
              .username("susan")
              .password("{noop}test123")
              .roles("EMPLOYEE", "MANAGER", "ADMIN").build();

      return new InMemoryUserDetailsManager(john, mary, susan);

   }*/
}

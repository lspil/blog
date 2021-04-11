package com.example.springsecurityendpointauthorizationnew.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

  @Bean
  public UserDetailsService userDetailsService() {
    var uds = new InMemoryUserDetailsManager();
    uds.createUser(User.withUsername("bill").password("12345").authorities("read").build());
    uds.createUser(User.withUsername("john").password("12345").authorities("write").build());
    return uds;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Bean
  public SecurityFilterChain configuration(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
          .httpBasic()
            .and()
          .authorizeRequests().anyRequest().hasAuthority("read")
            .and()
          .build();
  }
}

package com.example.IdentityService.otp.config;

import com.example.IdentityService.otp.exception.SimpleAuthenticationEntryPoint;
import com.example.IdentityService.otp.service.UserService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Bean
   public UserDetailsService userDetailsService() {
      return new UserService();
   }

   @Bean
   public BCryptPasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
      authProvider.setUserDetailsService(userDetailsService());
      authProvider.setPasswordEncoder(passwordEncoder());

      return authProvider;
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.authenticationProvider(authenticationProvider());
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.exceptionHandling().authenticationEntryPoint(new SimpleAuthenticationEntryPoint());
      http.csrf().disable();
            http.authorizeRequests()
                  .antMatchers("/register").permitAll()
                  .antMatchers("/confirm-account").permitAll()
                  .antMatchers("/fun").permitAll()
                    .antMatchers(HttpMethod.POST,"/addBlog").authenticated()
                  .anyRequest().permitAll()//authenticated()
                  .and()
                  .httpBasic();
   }
}

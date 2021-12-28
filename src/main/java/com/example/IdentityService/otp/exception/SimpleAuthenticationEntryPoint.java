package com.example.IdentityService.otp.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;

@Component("simpleAuthenticationEntryPoint")
@CrossOrigin("*")
public class SimpleAuthenticationEntryPoint implements AuthenticationEntryPoint {

   ObjectMapper mapper = new ObjectMapper();
   @Override
   public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
      res.setContentType("application/json");
      String str = "{\"message\": \"verify mail and password\"}";
      res.getWriter().write(str);
      res.flushBuffer();
   }
}

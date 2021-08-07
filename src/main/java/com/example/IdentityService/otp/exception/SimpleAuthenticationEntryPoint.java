package com.example.IdentityService.otp.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class SimpleAuthenticationEntryPoint implements AuthenticationEntryPoint {

   @Override
   public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
         AuthenticationException e) throws IOException, ServletException {
      httpServletResponse.setContentType("application/json");
      httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      httpServletResponse.getOutputStream().println("{ \"error\": \"" + e.getMessage() + "\" }");

   }
}

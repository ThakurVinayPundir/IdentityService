package com.example.IdentityService.otp.exception;

import org.springframework.security.core.AuthenticationException;

public class EmailNotVerifiedException extends AuthenticationException {
   String message;
   public EmailNotVerifiedException(String message) {
      super(message);
      this.message = message;
   }

   @Override
   public String getMessage() {

      return message;
   }

   public void setMessage(String message) {

      this.message = message;
   }
}

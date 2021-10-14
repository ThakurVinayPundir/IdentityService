package com.example.IdentityService.otp.exception;

public class BaseException extends Exception{
   private static final long serialVersionUID = 7718828512143293558L;
   private String message;
   private String code;

   public BaseException(){
      super();
   }

   public BaseException(String message) {
      this.message = message;
   }

   public BaseException(String message, String code){
      this.message = message;
      this.code = code;
   }

   @Override
   public String getMessage() {

      return message;
   }

   public void setMessage(String message) {

      this.message = message;
   }

   public String getCode() {

      return code;
   }

   public void setCode(String code) {

      this.code = code;
   }
}

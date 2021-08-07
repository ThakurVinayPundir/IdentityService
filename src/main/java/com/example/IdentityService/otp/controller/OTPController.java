package com.example.IdentityService.otp.controller;

import com.example.IdentityService.otp.model.EmailTemplate;
import com.example.IdentityService.otp.service.EmailService;
import com.example.IdentityService.otp.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class OTPController {
   @Autowired
   public OTPService otpService;

   @Autowired
   public EmailService emailService;

   @GetMapping("/fun")
   public static void fun(){
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      String rawPassword = "Vinay@123";
      String encodedPassword = encoder.encode(rawPassword);
      System.out.println("value of encoded password is "+encodedPassword);
   }

   @PostMapping("/sendOtp/{username}")
   public Map<String, Boolean> sendOTP(@PathVariable String emailId) throws MessagingException {
//      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//      String username = auth.getName();
//      int otp = otpService.generateOTP(username);
      int otp = otpService.generateOTP(emailId);
      //Generate The Template to send OTP
      EmailTemplate template = new EmailTemplate("SendOtp.html");
//      Map<String,String> replacements = new HashMap<String,String>();
//      replacements.put("user", username);
//      replacements.put("otpnum", String.valueOf(otp));
//      String message = template.getTemplate(replacements);
      emailService.sendOtpMessage(emailId, " OTP ", ""+otp);

      Map<String, Boolean> map = new HashMap<>();
      map.put("success",true);
      return map;
   }

   @RequestMapping(value ="/validateOtp", method = RequestMethod.GET)
   public @ResponseBody
   String validateOtp(@RequestParam("otpnum") int otpnum){

      final String SUCCESS = "Entered Otp is valid";
      final String FAIL = "Entered Otp is NOT valid. Please Retry!";
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String username = auth.getName();
      //Validate the Otp
      if(otpnum >= 0){

         int serverOtp = otpService.getOtp(username);
         if(serverOtp > 0){
            if(otpnum == serverOtp){
               otpService.clearOTP(username);

               return (SUCCESS);
            }
            else {
               return FAIL;
            }
         }else {
            return FAIL;
         }
      }else {
         return FAIL;
      }
   }
}

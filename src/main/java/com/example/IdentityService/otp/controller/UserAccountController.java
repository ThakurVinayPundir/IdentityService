package com.example.IdentityService.otp.controller;

import com.example.IdentityService.otp.exception.SimpleAuthenticationEntryPoint;
import com.example.IdentityService.otp.model.ConfirmationToken;
import com.example.IdentityService.otp.model.User;
import com.example.IdentityService.otp.model.UserCredential;
import com.example.IdentityService.otp.repo.ConfirmationTokenRepository;
import com.example.IdentityService.otp.service.EmailService;
import com.example.IdentityService.otp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserAccountController {
   @Autowired
   private UserService userService;

   @Autowired
   private ConfirmationTokenRepository confirmationTokenRepository;

   @Autowired
   private EmailService emailService;

   @PostMapping("/register")
   @ResponseBody
   public Map<String, String> registerUser(@RequestBody User user) {
      User existingUser = userService.findByEmailIdIgnoreCase(user.getEmailId());
      if( existingUser == null){
         userService.addUser(user);
         ConfirmationToken confirmationToken = new ConfirmationToken(user);
         confirmationTokenRepository.save(confirmationToken);
         SimpleMailMessage mailMessage = new SimpleMailMessage();
         mailMessage.setTo(user.getEmailId());
         mailMessage.setSubject("Complete Registration!");
         mailMessage.setText("To confirm your account, please click here : "
               +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());
         emailService.sendEmail(mailMessage);

         Map<String, String> map = new HashMap<>();
         map.put("emailId", user.getEmailId());
         map.put("message", "successfulRegisteration");
         return map;
      }else{
         Map<String, String> map = new HashMap<>();
         map.put("emailId", user.getEmailId());
         map.put("message", "This email already exists!");
         return map;
      }
   }

   @RequestMapping(value="/confirm-account", method= { RequestMethod.GET, RequestMethod.POST})
   @ResponseBody
   public Map<String, String> confirmUserAccount(@RequestParam("token") String confirmationToken ) {
      ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

      if(token != null){
         User user =  userService.findByEmailIdIgnoreCase(token.getUser().getEmailId());
         user.setEnabled(true);
         userService.addUser(user);
         Map<String, String> map = new HashMap<>();
         map.put("message", "Account Verified");
         return map;
      } else {
         Map<String, String> map = new HashMap<>();
         map.put("message", "The link is invalid or broken!");
         return map;
      }
   }

   @PostMapping("/login")
   @ResponseBody
   public User login()  {
      UserCredential authentication = (UserCredential) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      User user = authentication.getUser();
      if(!user.isEnabled()) {
//         throw new EmailNotVerifiedException("please verify email id");
      }
      return userService.findByEmailIdIgnoreCase(user.getEmailId());
   }

}

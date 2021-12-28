package com.example.IdentityService.otp.controller;

import com.example.IdentityService.otp.entity.ConfirmationToken;
import com.example.IdentityService.otp.entity.Image;
import com.example.IdentityService.otp.entity.User;
import com.example.IdentityService.otp.entity.UserCredential;
import com.example.IdentityService.otp.repo.ConfirmationTokenRepository;
import com.example.IdentityService.otp.service.EmailService;
import com.example.IdentityService.otp.service.ImageDataService;
import com.example.IdentityService.otp.service.OTPService;
import com.example.IdentityService.otp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserAccountController {
   @Autowired
   private UserService userService;

   @Autowired
   private ConfirmationTokenRepository confirmationTokenRepository;

   @Autowired
   private EmailService emailService;

   @Autowired
   private ImageDataService imageDataService;

   @Autowired
   private OTPService otpService;

   @PostMapping("/register")
   @ResponseBody
   @Transactional
   public Map<String, String> registerUser(@RequestBody User user) throws Exception{
      List<User> existingUser = userService.findByEmailIdIgnoreCase(user.getEmailId());
      if( existingUser.size() == 0 || !existingUser.get(0).isEnabled()){
         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
         String rawPassword = user.getPassword();
         String encodedPassword = encoder.encode(rawPassword);
         user.setPassword(encodedPassword);
         user.setRole("USER");
         userService.addUser(user);
         ConfirmationToken confirmationToken = new ConfirmationToken(user);
         confirmationTokenRepository.save(confirmationToken);
         SimpleMailMessage mailMessage = new SimpleMailMessage();
         mailMessage.setTo(user.getEmailId());
         mailMessage.setSubject("Complete Registration!");
         mailMessage.setText("To confirm your account, please click here : "
               +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken()+" \n or enter otp "+ otpService.generateOTP(user.getEmailId()));
         emailService.sendEmail(mailMessage);
         Map<String, String> map = new HashMap<>();
         map.put("emailId", user.getEmailId());
         map.put("message", "successfulRegisteration");
         return map;
      }else{
         Map<String, String> map = new HashMap<>();
         map.put("emailId", user.getEmailId());
         map.put("message", "This email already exists!");
//         throw new EmailException("This email already exists!");
         return map;
      }
   }

   @RequestMapping(value="/confirm-account", method= { RequestMethod.GET, RequestMethod.POST})
   @ResponseBody
   public Map<String, String> confirmUserAccount(@RequestParam("token") String confirmationToken ) {
      ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

      if(token != null){
         User user =  userService.findByEmailIdIgnoreCase(token.getUser().getEmailId()).get(0);
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

   @RequestMapping(value="/validate-otp-account", method = { RequestMethod.POST })
   @ResponseBody
   public Map<String, String> validateUsingOTP(@RequestParam("otp") String otp, @RequestParam("emailId") String emailId) {

      //ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
      int otpFromCache = otpService.getOtp(emailId);
      if(otp != null && (String.valueOf(otpFromCache)).equals(otp)){
         User user =  userService.findByEmailIdIgnoreCase(emailId).get(0);
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

   @GetMapping("/login")
   @ResponseBody
   public User login()  {

      UserCredential authentication = null;
      try {
         authentication = (UserCredential) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      } catch (Exception e){
//         throw new EmailNotVerifiedException("please verify email id and password");
      }
      User user = authentication.getUser();
      return userService.findByEmailIdIgnoreCase(user.getEmailId()).get(0);
   }

   @PostMapping(value = "/upload", consumes = "multipart/form-data")
   @ResponseBody
   public Image upload(@RequestParam(value = "myFile") MultipartFile file, @RequestHeader HttpHeaders headers) throws IOException {
      return imageDataService.upload(file);
   }

   @PostMapping(value = "/getImage")
   @ResponseBody
   public Image getImage() {
      return imageDataService.getImage();
   }
}

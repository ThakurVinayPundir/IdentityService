package com.example.IdentityService.otp.service;


import com.example.IdentityService.otp.exception.EmailNotVerifiedException;
import com.example.IdentityService.otp.exception.SimpleAuthenticationEntryPoint;
import com.example.IdentityService.otp.model.User;
import com.example.IdentityService.otp.model.UserCredential;
import com.example.IdentityService.otp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
   @Autowired
   private UserRepo userRepository;

   @Override
   public UserDetails loadUserByUsername(String username)
         throws UsernameNotFoundException {
      User user = userRepository.getUserByUsername(username);

      if (user == null) {
         throw new DisabledException("please verify email");
      }

      if(!user.isEnabled()){
         throw new EmailNotVerifiedException("please verify email");
      }

      return new UserCredential(user);
   }

   public User addUser(User user){
      try {
         userRepository.save(user);
         return user;
      }catch (Exception e){
         System.out.println(e);
         return null;
      }
   }

   public User getUser(String username){
      return userRepository.getUserByUsername(username);
   }

   public User findByEmailIdIgnoreCase(String emailId) { return userRepository.findByEmailIdIgnoreCase(emailId);}
}
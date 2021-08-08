package com.example.IdentityService.otp.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class UserCredential implements UserDetails {

   private User user;

   public User getUser() {

      return user;
   }

   public void setUser(User user) {

      this.user = user;
   }

   public UserCredential(User user) {
      this.user = user;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
      return Arrays.asList(authority);
   }

   @Override
   public String getPassword() {
      return user.getPassword();
   }

   @Override
   public String getUsername() {
      return user.getFirstName()+" "+user.getLastName();
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return user.isEnabled();
   }
}

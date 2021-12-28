package com.example.IdentityService.otp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name="userId")
   private long userid;

   private String emailId;

   private String password;

   @Column(name="firstName")
   private String firstName;

   @Column(name="lastName")
   private String lastName;
   private boolean enabled;

   private String role;

   public String getRole() {

      return role;
   }

   public void setRole(String role) {

      this.role = role;
   }

   public long getUserid() {

      return userid;
   }

   public void setUserid(long userid) {

      this.userid = userid;
   }

   public String getEmailId() {

      return emailId;
   }

   public void setEmailId(String emailId) {

      this.emailId = emailId;
   }

   public String getPassword() {

      return password;
   }

   public void setPassword(String password) {

      this.password = password;
   }

   public String getFirstName() {

      return firstName;
   }

   public void setFirstName(String firstName) {

      this.firstName = firstName;
   }

   public String getLastName() {

      return lastName;
   }

   public void setLastName(String lastName) {

      this.lastName = lastName;
   }

   public boolean isEnabled() {

      return enabled;
   }

   public void setEnabled(boolean enabled) {

      this.enabled = enabled;
   }
}

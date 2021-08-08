package com.example.IdentityService.otp.repo;

import com.example.IdentityService.otp.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends CrudRepository<User, Long> {
   @Query("SELECT u FROM User u WHERE u.emailId = :username")
   public User getUserByUsername(@Param("username") String username);

   User findByEmailIdIgnoreCase(String emailId);
}

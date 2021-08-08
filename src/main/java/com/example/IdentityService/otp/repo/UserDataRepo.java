package com.example.IdentityService.otp.repo;

import com.example.IdentityService.otp.entity.User;
import com.example.IdentityService.otp.entity.UserData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepo extends CrudRepository<UserData, Long> {
    UserData findAllByUser(User user);
}

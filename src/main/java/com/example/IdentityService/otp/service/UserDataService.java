package com.example.IdentityService.otp.service;

import com.example.IdentityService.otp.entity.User;
import com.example.IdentityService.otp.entity.UserData;
import com.example.IdentityService.otp.repo.UserDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataService {
    @Autowired
    private UserDataRepo userDataRepo;

    public UserData getUserData(User user){
        return userDataRepo.findAllByUser(user);
    }

    public UserData addUserData(UserData userData) {
        return userDataRepo.save(userData);
    }
}

package com.example.IdentityService.otp.controller;

import com.example.IdentityService.otp.entity.User;
import com.example.IdentityService.otp.entity.UserCredential;
import com.example.IdentityService.otp.entity.UserData;
import com.example.IdentityService.otp.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class UserDataController {

    @Autowired
    private UserDataService userDataService;

    @GetMapping("/user_data")
    @ResponseBody
    public UserData getUserData() {
        UserCredential authentication = (UserCredential) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = authentication.getUser();
        return userDataService.getUserData(user);
    }

    @PostMapping("/user_data")
    @ResponseBody
    public UserData addUserData(@RequestBody UserData userData) {
        UserCredential authentication = (UserCredential) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = authentication.getUser();
        userData.setUser(user);
        return userDataService.addUserData(userData);
    }
}

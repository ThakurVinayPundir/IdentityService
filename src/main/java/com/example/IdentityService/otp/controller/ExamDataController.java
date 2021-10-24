package com.example.IdentityService.otp.controller;

import com.example.IdentityService.otp.entity.ExamData;
import com.example.IdentityService.otp.entity.User;
import com.example.IdentityService.otp.entity.UserCredential;
import com.example.IdentityService.otp.models.ExamUpdateRequest;
import com.example.IdentityService.otp.service.ExamDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExamDataController {
    @Autowired
    private ExamDataService examDataService;

    @GetMapping("/exams")
    @ResponseBody
    public List<ExamData> getExamData() {
        UserCredential authentication = (UserCredential) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = authentication.getUser();
        return examDataService.getExamDataForUserId(user.getUserid());
    }

    @PostMapping("/exams")
    @ResponseBody
    public Map<String, Object> addExamData(@RequestBody ExamUpdateRequest request) {
        UserCredential authentication = (UserCredential) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = authentication.getUser();
        request.setUserId(user.getUserid());
        ExamData examData1 = new ExamData();
        examData1.setExamDate(request.getExamDate());
        examData1.setAdmitCardReleaseDate(request.getAdmitCardReleaseDate());
        examData1.setExamName(request.getExamName());
        examData1.setUserId(request.getUserId());
        examDataService.addExamData(examData1);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        return response;
    }
}

package com.example.IdentityService.otp.service;

import com.example.IdentityService.otp.entity.ExamData;
import com.example.IdentityService.otp.repo.ExamDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamDataService {
    @Autowired
    private ExamDataRepo examDataRepo;

    public List<ExamData> getExamDataForUserId(long userId) {
        return examDataRepo.findAllByUserId(userId);
    }

    public void addExamData(ExamData examData) {
        examDataRepo.save(examData);
    }
}

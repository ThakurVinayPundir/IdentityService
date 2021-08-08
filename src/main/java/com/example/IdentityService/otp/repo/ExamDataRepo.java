package com.example.IdentityService.otp.repo;

import com.example.IdentityService.otp.entity.ExamData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamDataRepo extends CrudRepository<ExamData, Long> {
    public List<ExamData> findAllByUserId(long id);
}

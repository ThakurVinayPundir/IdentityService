package com.example.IdentityService.otp.repo;

import com.example.IdentityService.otp.entity.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends CrudRepository<Image, Long> {
}

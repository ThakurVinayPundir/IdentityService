package com.example.IdentityService.otp.repo;

import com.example.IdentityService.otp.entity.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
   ConfirmationToken findByConfirmationToken(String confirmationToken);
}

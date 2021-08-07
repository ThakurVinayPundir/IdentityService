package com.example.IdentityService.otp.repo;

import com.example.IdentityService.otp.model.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
   ConfirmationToken findByConfirmationToken(String confirmationToken);
}

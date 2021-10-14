package com.example.IdentityService.otp.service;

import com.example.IdentityService.otp.entity.Image;
import com.example.IdentityService.otp.entity.User;
import com.example.IdentityService.otp.entity.UserCredential;
import com.example.IdentityService.otp.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageDataService {

    @Autowired
    private ImageRepo imageRepo;

    public Image upload(MultipartFile file) throws IOException {
        UserCredential authentication = (UserCredential) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = authentication.getUser();
        Long id = user.getUserid();
        Image image = new Image(id, file.getOriginalFilename(), file.getContentType(), file.getBytes());
        final Image savedImage = imageRepo.save(image);

        return savedImage;
    }

    public Image getImage() {
        UserCredential authentication = (UserCredential) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = authentication.getUser();
        Long id = user.getUserid();
        final Image image = imageRepo.findById(id).get();
        return image;
    }
}

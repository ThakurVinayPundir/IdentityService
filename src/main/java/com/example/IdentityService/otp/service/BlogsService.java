package com.example.IdentityService.otp.service;

import com.example.IdentityService.otp.entity.Blogs;
import com.example.IdentityService.otp.repo.BlogsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogsService {
    @Autowired
    private BlogsRepo blogsRepo;

    public List<Blogs> getFiveBlogs(long idx) {
        return blogsRepo.findFiveBlogs(idx);
    }

    public void addBlog(Blogs blogs) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        blogs.setAuthName(username);
        blogs.setCreatedAt(new Date());
        blogs.setUpdatedAt(new Date());
        blogsRepo.save(blogs);
    }
}

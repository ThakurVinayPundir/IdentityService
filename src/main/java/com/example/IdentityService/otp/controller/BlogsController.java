package com.example.IdentityService.otp.controller;

import com.example.IdentityService.otp.entity.Blogs;
import com.example.IdentityService.otp.service.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BlogsController {

    @Autowired
    private BlogsService blogsService;

    @GetMapping("/getBlogs/{id}")
    @ResponseBody
    public List<Blogs> getBlogs(@PathVariable("id") long id) {
        return blogsService.getFiveBlogs(id);
    }

    @PostMapping("/addBlog")
    @ResponseBody
    public Map<String, Boolean> addBlog(@RequestBody Blogs blogs) throws Exception {
        blogsService.addBlog(blogs);
        Map<String, Boolean> response = new HashMap<>();
        response.put("success",true);
        return response;
    }
}

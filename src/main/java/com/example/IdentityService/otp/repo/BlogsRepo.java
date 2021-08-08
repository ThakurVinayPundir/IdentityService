package com.example.IdentityService.otp.repo;

import com.example.IdentityService.otp.entity.Blogs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogsRepo extends CrudRepository<Blogs, Long> {
    @Query(value = "select * from blogs b where id >= :idx order by id limit 5", nativeQuery = true)
    public List<Blogs> findFiveBlogs(long idx);
}

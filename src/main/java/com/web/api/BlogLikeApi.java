package com.web.api;

import com.web.dto.request.BlogRequest;
import com.web.entity.Blog;
import com.web.entity.BlogLike;
import com.web.service.BlogLikeService;
import com.web.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog-like")
@CrossOrigin
public class BlogLikeApi {

    @Autowired
    private BlogLikeService blogLikeService;

    @PostMapping("/all/save")
    public ResponseEntity<?> save(@RequestParam Long blogId){
        blogLikeService.save(blogId);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @GetMapping("/public/find-by-blog")
    public ResponseEntity<?> findAll(@RequestParam Long blogId){
        List<BlogLike> list =blogLikeService.findByBlog(blogId);
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    @DeleteMapping("/all/delete")
    public void delete(@RequestParam Long idBlogLike){
        blogLikeService.unlike(idBlogLike);
    }
}

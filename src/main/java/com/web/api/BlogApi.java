package com.web.api;

import com.web.dto.request.BlogRequest;
import com.web.entity.Blog;
import com.web.enums.ActiveStatus;
import com.web.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blog")
@CrossOrigin
public class BlogApi {

    @Autowired
    private BlogService blogService;

    @PostMapping("/all/save")
    public ResponseEntity<?> saveOrUpdate(@RequestBody BlogRequest blogRequest){
        Blog blog = blogService.save(blogRequest);
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    @PostMapping("/all/update")
    public ResponseEntity<?> update(@RequestBody BlogRequest blogRequest){
        Blog blog = blogService.update(blogRequest);
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    @GetMapping("/blog-manager/find-all")
    public ResponseEntity<?> findAll(Pageable pageable){
        Page<Blog> blog = blogService.findAll(pageable);
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    @DeleteMapping("/all/delete")
    public void delete(@RequestParam Long blogID){
        blogService.deleteBlog(blogID);
    }

    @GetMapping("/public/get-all-blog")
    public ResponseEntity<?> getBlogActived(@RequestBody Pageable pageable){
        Page<Blog> page = blogService.getBlogActived(pageable);
        return new ResponseEntity<>(page, HttpStatus.CREATED);
    }

    @GetMapping("/public/search-blog")
    public ResponseEntity<?> searchBlog(@RequestParam String search, Pageable pageable){
        Page<Blog> page = blogService.searchBlog(search,pageable);
        return new ResponseEntity<>(page, HttpStatus.CREATED);
    }

    @GetMapping("/public/get-blog-by-category")
    public ResponseEntity<?> getBlogByCategory(@RequestParam Long categoryId, Pageable pageable){
        Page<Blog> page = blogService.getBlogByCategory(categoryId,pageable);
        return new ResponseEntity<>(page, HttpStatus.CREATED);
    }

    @GetMapping("/blog-manager/active-or-unacative")
    public ResponseEntity<?> activeOrUnactive(@RequestParam Long blogId){
        ActiveStatus activeStatuse = blogService.activeOrUnactive(blogId);
        return new ResponseEntity<>(activeStatuse, HttpStatus.CREATED);
    }
}

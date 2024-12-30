package com.example.blogapp.controller;

import com.example.blogapp.model.Blog;
import com.example.blogapp.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/blogs")
public class BlogRestController {
    @Autowired
    private IBlogService blogService;

    @GetMapping
    public ResponseEntity<Iterable<Blog>> getBlogs() {
        Iterable<Blog> blogs = blogService.findAll();
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (!blog.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blog.get(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Blog>> getBlogsByTitleApi(
            @RequestParam("q") String title,
            @PageableDefault(size = 4, sort = "publishedDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return  new ResponseEntity<>(blogService.findAllByTitle(pageable, title), HttpStatus.OK);
    }
}

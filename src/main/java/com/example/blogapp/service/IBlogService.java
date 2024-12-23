package com.example.blogapp.service;

import com.example.blogapp.model.Blog;
import com.example.blogapp.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlogService extends IGenerateService<Blog>{
    Page<Blog> findAllByTitle(Pageable pageable, String name);

    Page<Blog> findAllByCategory(Pageable pageable, Category category);
}

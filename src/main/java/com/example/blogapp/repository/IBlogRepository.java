package com.example.blogapp.repository;

import com.example.blogapp.model.Blog;
import com.example.blogapp.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBlogRepository extends PagingAndSortingRepository<Blog, Long> {
    Page<Blog> findAll(Pageable pageable);

    Page<Blog> findAllByTitleContainingIgnoreCase(Pageable pageable, String name);

    Page<Blog> findAllByCategory(Pageable pageable, Category category);
}

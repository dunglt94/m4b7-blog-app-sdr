package com.example.blogapp.service;

import com.example.blogapp.model.Blog;
import com.example.blogapp.model.Category;
import com.example.blogapp.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepository blogRepository;

    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public void save(Blog object) {
        blogRepository.save(object);
    }

    @Override
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Page<Blog> findAllByTitle(Pageable pageable, String name) {
        return blogRepository.findAllByTitleContainingIgnoreCase(pageable, name);
    }

    @Override
    public Page<Blog> findAllByCategory(Pageable pageable, Category category) {
        return blogRepository.findAllByCategory(pageable, category);
    }
}

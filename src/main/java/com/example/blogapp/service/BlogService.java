package com.example.blogapp.service;

import com.example.blogapp.model.Blog;
import com.example.blogapp.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepository blogRepository;

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findById(int id) {
        return blogRepository.findById(id);
    }

    @Override
    public void save(Blog object) {
        blogRepository.save(object);
    }

    @Override
    public void delete(int id) {
        blogRepository.delete(id);
    }
}

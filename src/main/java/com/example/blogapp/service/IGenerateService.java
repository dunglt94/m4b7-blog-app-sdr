package com.example.blogapp.service;

import java.util.List;

public interface IGenerateService<T> {
    List<T> findAll();

    T findById(int id);

    void save(T object);

    void delete(int id);
}

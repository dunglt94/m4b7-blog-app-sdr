package com.example.blogapp.repository;

import java.util.List;

public interface IGenerateRepository<T> {
    List<T> findAll();

    T findById(int id);

    void save(T object);

    void delete(int id);
}

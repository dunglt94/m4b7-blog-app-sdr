package com.example.blogapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> findAll();

    Page<T> findAll(Pageable pageable);

    Optional<T> findById(Long id);

    void save(T object);

    void delete(Long id);
}

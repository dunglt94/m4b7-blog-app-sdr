package com.example.blogapp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishedDate;

    private String author;

    @Column(columnDefinition = "TEXT")
    private String content;

    public Blog() {
    }

    public Blog(String title, LocalDate publishedDate, String author, String content) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.author = author;
        this.content = content;
    }

    public Blog(int id, String title, LocalDate publishedDate, String author, String content) {
        this.id = id;
        this.title = title;
        this.publishedDate = publishedDate;
        this.author = author;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

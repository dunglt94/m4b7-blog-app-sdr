package com.example.blogapp.controller;

import com.example.blogapp.model.Blog;
import com.example.blogapp.model.Category;
import com.example.blogapp.service.IBlogService;
import com.example.blogapp.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
@CrossOrigin("*")
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> getCategories() {
        return categoryService.findAll();
    }

    @GetMapping
    public String showBlogList(
            @PageableDefault(size = 4, sort = "publishedDate", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam("search") Optional<String> search, ModelMap model) {
        Page<Blog> blogs;
        if (search.isPresent()) {
            blogs = blogService.findAllByTitle(pageable, search.get());
        } else {
            blogs = blogService.findAll(pageable);
        }
        model.addAttribute("blogs", blogs);
        return "blog/index";
    }

    @GetMapping("/create")
    public String showCreateForm(ModelMap model) {
        model.addAttribute("blog", new Blog());
        return "blog/create";
    }

    @PostMapping("/save")
    public String create(Blog blog, RedirectAttributes redirectAttributes) {
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Blog created successfully");
        return "redirect:/blogs";
    }

    @GetMapping("/{id}/view")
    public String showDetails(ModelMap model, @PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            model.addAttribute("blog", blog.get());
            return "blog/view";
        } else {
            return "error";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(ModelMap model, @PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            model.addAttribute("blog", blog.get());
            return "blog/update";
        } else {
            return "error";
        }
    }

    @PostMapping("/{id}/edit")
    public String update(Blog blog, RedirectAttributes redirectAttributes) {
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Blog updated successfully");
        return "redirect:/blogs";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        blogService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Blog deleted successfully");
        return "redirect:/blogs";
    }
}

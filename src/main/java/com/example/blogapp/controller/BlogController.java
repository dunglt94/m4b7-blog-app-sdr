package com.example.blogapp.controller;

import com.example.blogapp.model.Blog;
import com.example.blogapp.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @GetMapping
    public String showBlogList(ModelMap model) {
        model.addAttribute("blogs", blogService.findAll());
        return "index";
    }

    @GetMapping("/create")
    public String showCreateForm(ModelMap model) {
        model.addAttribute("blog", new Blog());
        return "create";
    }

    @PostMapping("/save")
    public String create(Blog blog, RedirectAttributes redirectAttributes) {
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("success", "Blog created successfully");
        return "redirect:/blogs";
    }

    @GetMapping("/{id}/view")
    public String showDetails(ModelMap model, @PathVariable int id) {
        model.addAttribute("blog", blogService.findById(id));
        return "view";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(ModelMap model, @PathVariable int id) {
        model.addAttribute("blog", blogService.findById(id));
        return "update";
    }

    @PostMapping("/{id}/edit")
    public String update(Blog blog, RedirectAttributes redirectAttributes) {
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("success", "Blog updated successfully");
        return "redirect:/blogs";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        blogService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Blog deleted successfully");
        return "redirect:/blogs";
    }
}

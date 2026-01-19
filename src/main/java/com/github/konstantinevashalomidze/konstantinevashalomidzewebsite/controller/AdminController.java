package com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.controller;

import com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.model.Profile;
import com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.service.ProfileService;
import com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.model.Post;
import com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.repository.PostRepository;
import com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.service.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final ProfileService profileService;

    public AdminController(PostService postService, PostRepository postRepository, ProfileService profileService) {
        this.postService = postService;
        this.postRepository = postRepository;
        this.profileService = profileService;
    }

    @GetMapping
    public String dashboard(Model model) {
        var posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "admin/dashboard";
    }

    @GetMapping("/profile")
    public String editProfile(Model model) {
        var profile = profileService.getProfile()
                .orElseThrow(() -> new IllegalStateException("Profile not found. The data seeder should have created it."));
        model.addAttribute("profile", profile);
        return "admin/edit-profile";
    }

    @PostMapping("/profile")
    public String saveProfile(@ModelAttribute Profile profile) {
        profileService.save(profile);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String newPost(Model model) {
        model.addAttribute("post", new Post());
        return "admin/edit";
    }

    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable Long id, Model model) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        model.addAttribute("post", post);
        return "admin/edit";
    }

    @PostMapping("/save")
    public String savePost(@Valid @ModelAttribute Post post, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/edit";
        }
        if (post.isPublished() && post.getPublishedDate() == null) {
            post.setPublishedDate(LocalDateTime.now());
        }
        postService.save(post);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return "redirect:/admin";
    }
}

package com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.controller;

import com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.model.Profile;
import com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.service.ProfileService;
import com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.repository.PostRepository;
import com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.service.MarkdownService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class PublicController {

    private final PostRepository postRepository;
    private final MarkdownService markdownService;
    private final ProfileService profileService;

    public PublicController(PostRepository postRepository, MarkdownService markdownService, ProfileService profileService) {
        this.postRepository = postRepository;
        this.markdownService = markdownService;
        this.profileService = profileService;
    }

    @GetMapping("/")
    public String home(Model model) {
        var posts = postRepository.findByIsPublishedTrueOrderByPublishedDateDesc();
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/posts/{slug}")
    public String post(@PathVariable String slug, Model model) {
        var post = postRepository.findBySlug(slug)
                .filter(p -> p.isPublished())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        
        var contentAsHtml = markdownService.toHtml(post.getContent());

        model.addAttribute("post", post);
        model.addAttribute("contentAsHtml", contentAsHtml);
        return "post";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        var profile = profileService.getProfile()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("profile", profile);
        model.addAttribute("taglineHtml", markdownService.toHtml(profile.getTagline()));
        model.addAttribute("aboutMeHtml", markdownService.toHtml(profile.getAboutMe()));
        model.addAttribute("technicalSkillsHtml", markdownService.toHtml(profile.getTechnicalSkills()));
        model.addAttribute("educationHtml", markdownService.toHtml(profile.getEducation()));
        model.addAttribute("contactHtml", markdownService.toHtml(profile.getContact()));
        model.addAttribute("experienceHtml", markdownService.toHtml(profile.getExperience()));
        model.addAttribute("projectsHtml", markdownService.toHtml(profile.getProjects()));
        return "profile";
    }
}

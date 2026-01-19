package com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.service;

import com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.model.Post;
import com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.Normalizer;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class PostService {

    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Post> findBySlug(String slug) {
        return postRepository.findBySlug(slug);
    }

    @Transactional
    public Post save(Post post) {
        var postToSave = post; // Use 'var' for modern Java style

        // Generate slug if it's empty or only whitespace
        if (!StringUtils.hasText(postToSave.getSlug())) {
            postToSave.setSlug(generateSlug(postToSave.getTitle()));
        }

        return postRepository.save(postToSave);
    }

    private String generateSlug(String input) {
        if (input == null) {
            return "";
        }

        var noWhitespace = WHITESPACE.matcher(input).replaceAll("-");
        var normalized = Normalizer.normalize(noWhitespace, Normalizer.Form.NFD);
        var slug = NON_LATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }
}

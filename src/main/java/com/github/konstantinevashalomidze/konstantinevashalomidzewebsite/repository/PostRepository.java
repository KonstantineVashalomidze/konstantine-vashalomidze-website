package com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.repository;

import com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findBySlug(String slug);

    List<Post> findByIsPublishedTrueOrderByPublishedDateDesc();
}
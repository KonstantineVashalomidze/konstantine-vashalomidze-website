package com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.repository;

import com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}

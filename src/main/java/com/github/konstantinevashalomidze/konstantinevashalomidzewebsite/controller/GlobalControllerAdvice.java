package com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.controller;

import com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.model.Profile;
import com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.service.ProfileService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    private final ProfileService profileService;

    public GlobalControllerAdvice(ProfileService profileService) {
        this.profileService = profileService;
    }

    @ModelAttribute("backgroundMusicUrl")
    public String getBackgroundMusicUrl() {
        return profileService.getProfile()
                .map(Profile::getBackgroundMusicUrl)
                .orElse(null);
    }
}

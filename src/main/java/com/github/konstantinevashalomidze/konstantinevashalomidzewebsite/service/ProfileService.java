package com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.service;

import com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.model.Profile;
import com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Profile> getProfile() {
        // There is only one profile, so we fetch the first one.
        return profileRepository.findAll().stream().findFirst();
    }

    @Transactional
    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }
}

package com.github.konstantinevashalomidze.konstantinevashalomidzewebsite.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String tagline;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String aboutMe;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String technicalSkills;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String education;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String contact;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String experience;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String projects;

    private String resumeUrl;

    private String backgroundMusicUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getTechnicalSkills() {
        return technicalSkills;
    }

    public void setTechnicalSkills(String technicalSkills) {
        this.technicalSkills = technicalSkills;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public String getBackgroundMusicUrl() {
        return backgroundMusicUrl;
    }

    public void setBackgroundMusicUrl(String backgroundMusicUrl) {
        this.backgroundMusicUrl = backgroundMusicUrl;
    }
}

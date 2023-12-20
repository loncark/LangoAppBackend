package com.loncark.langoapp.dto;

import com.loncark.langoapp.domain.User;

public class UserDTO {
    private Long id;
    private String name;
    private String password;
    private String roles;
    private String country;
    private String bio;
    private String languages;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.roles = user.getRoles();
        this.country = user.getCountry();
        this.bio = user.getBio();
        this.languages = user.getLanguages();
    }

    public UserDTO(Long id, String name, String password, String roles, String country, String bio, String languages) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.country = country;
        this.bio = bio;
        this.languages = languages;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}

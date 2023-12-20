package com.loncark.langoapp.dto;

import com.loncark.langoapp.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
}

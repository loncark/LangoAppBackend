package com.loncark.langoapp.service;

import com.loncark.langoapp.domain.User;
import com.loncark.langoapp.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> findAll();

    Optional<UserDTO> findById(Long id);

    Optional<UserDTO> save(User user);

    void deleteById(Long id);

    Optional<UserDTO> findByName(String name);

    List<UserDTO> findByLanguage(String language);
}

package com.loncark.langoapp.service;

import com.loncark.langoapp.domain.User;
import com.loncark.langoapp.dto.UserDTO;
import com.loncark.langoapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id).map(user -> new UserDTO(user));
    }

    @Override
    public Optional<UserDTO> save(User user) {
        Optional<User> savedUser = Optional.of(userRepository.save(user));

        if(savedUser.isPresent()) {
            return Optional.of(new UserDTO(savedUser.get()));
        }
        else return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}

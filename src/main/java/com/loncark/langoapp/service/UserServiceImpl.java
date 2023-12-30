package com.loncark.langoapp.service;

import com.loncark.langoapp.domain.User;
import com.loncark.langoapp.dto.UserDTO;
import com.loncark.langoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        Optional<User> savedUser = Optional.of(userRepository.save(user));

        if(savedUser.isPresent()) {
            return Optional.of(new UserDTO(savedUser.get()));
        }
        else return Optional.empty();
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id).map(user -> new UserDTO(user));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserDTO> findByName(String name) {
        return userRepository.findByName(name).map(user -> new UserDTO(user));
    }

    @Override
    public List<UserDTO> findByLanguage(String language) {
        language = language.toUpperCase();
        return userRepository.findByLanguagesContaining(language).stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDTO> userDTO = findByName(username);
        if(userDTO.isPresent()) {
            return UserDtoToUserDetails(userDTO);
        }
        else return null;
    }

    @Override
    public UserDetails UserDtoToUserDetails(Optional<UserDTO> userDTO){
        if (userDTO.isEmpty()) { return null; }

        List<GrantedAuthority> authorities = Arrays.stream(userDTO.get().getRoles().split(","))
                .map(role -> (GrantedAuthority) () -> role)
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                userDTO.get().getName(),
                userDTO.get().getPassword(),
                authorities
        );
    }
}

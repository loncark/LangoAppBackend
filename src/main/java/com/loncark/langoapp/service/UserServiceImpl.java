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
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final AppointmentService aptService;
    private final ReviewService reviewService;
    private final MessageService messageService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder encoder, AppointmentService aptService, ReviewService reviewService, MessageService messageService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.aptService = aptService;
        this.reviewService = reviewService;
        this.messageService = messageService;
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
        Optional<User> user = userRepository.findByName(name);

        if(user.isPresent()) {
            return Optional.of(new UserDTO(user.get()));
        }
        else return Optional.empty();
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
    public List<UserDTO> findByUserIdList(List<Long> idList) {
        return userRepository.findByIdIn(idList).stream().map(UserDTO::new).collect(Collectors.toList());
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

    @Override
    @Transactional
    public void deleteAllTracesOfUserWithId(String userId) {
        Long id = Long.parseLong(userId);
        aptService.deleteByUserId(id);
        reviewService.deleteReviewsTiedToUserWithId(id);
        messageService.deleteMessagesTiedToUserWithId(id);
        deleteById(id);
    }
}

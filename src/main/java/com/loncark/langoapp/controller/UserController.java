package com.loncark.langoapp.controller;

import com.loncark.langoapp.domain.User;
import com.loncark.langoapp.dto.UserDTO;
import com.loncark.langoapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping
    public List<UserDTO> getAll() {
        return userService.findAll();
    }

    @GetMapping("{id}")
    public UserDTO getById(@PathVariable final String id) {
        return userService.findById(Long.parseLong(id))
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User was not found by that id")
                );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<UserDTO> save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping
    public UserDTO update(@Valid @RequestBody final User user) {
        return userService.save(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "An user was not found by that id"));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        userService.deleteById(Long.parseLong(id));
    }

    // Additional methods for frontend

    @GetMapping(params = "name")
    public Optional<UserDTO> getByName(@RequestParam final String name) {
        return userService.findByName(name);
    }

    @GetMapping(params = "language")
    public List<UserDTO> getByLanguage(@RequestParam final String language) {
        return userService.findByLanguage(language);
    }

    @PostMapping("idList")
    public List<UserDTO> getUsersByUserIdList(@RequestBody List<Long> idList) {
        return userService.findByUserIdList(idList);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(params = "userId")
    public void deleteTracesOfUserWithId(@RequestParam final String userId) {
        userService.deleteAllTracesOfUserWithId(userId);
    }

}

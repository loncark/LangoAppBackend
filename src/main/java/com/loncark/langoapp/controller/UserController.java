package com.loncark.langoapp.controller;

import com.loncark.langoapp.domain.User;
import com.loncark.langoapp.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.loncark.langoapp.service.UserService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
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
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User was not found by that code")
                );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDTO save(@Valid @RequestBody final User user) {
        return userService.save(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "A User with the same code already exists"));
    }

    @PutMapping
    public UserDTO update(@Valid @RequestBody final User user) {
        return userService.save(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A User was not found by that id"));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        userService.deleteById(Long.parseLong(id));
    }
}

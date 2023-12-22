package com.loncark.langoapp.controller;

import com.loncark.langoapp.domain.Appointment;
import com.loncark.langoapp.dto.AppointmentDTO;
import com.loncark.langoapp.dto.UserDTO;
import com.loncark.langoapp.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService aptService;

    public AppointmentController(AppointmentService aptService) {
        this.aptService = aptService;
    }

    @RequestMapping
    public List<AppointmentDTO> getAll() {
        return aptService.findAll();
    }

    @GetMapping("{id}")
    public AppointmentDTO getById(@PathVariable final String id) {
        return aptService.findById(Long.parseLong(id))
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment was not found by that code")
                );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AppointmentDTO save(@Valid @RequestBody final Appointment apt) {
        return aptService.save(apt)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "An appointment with the same id already exists"));
    }

    @PutMapping
    public AppointmentDTO update(@Valid @RequestBody final Appointment apt) {
        return aptService.save(apt)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "An appointment was not found by that id"));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        aptService.deleteById(Long.parseLong(id));
    }

    @GetMapping(params = "userId")
    public List<AppointmentDTO> getByUserId(@RequestParam final String userId) {
        return aptService.findByUserId(Long.parseLong(userId));
    }
}

package com.loncark.langoapp.service;

import com.loncark.langoapp.domain.Appointment;
import com.loncark.langoapp.dto.AppointmentDTO;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<AppointmentDTO> findAll();

    Optional<AppointmentDTO> findById(Long id);

    Optional<AppointmentDTO> save(Appointment apt);

    void deleteById(Long id);

    List<AppointmentDTO> findByUserId(Long userId);
}

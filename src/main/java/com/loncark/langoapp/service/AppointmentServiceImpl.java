package com.loncark.langoapp.service;

import com.loncark.langoapp.domain.Appointment;
import com.loncark.langoapp.dto.AppointmentDTO;
import com.loncark.langoapp.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    private final AppointmentRepository aptRepository;

    public AppointmentServiceImpl(AppointmentRepository aptRepository) {
        this.aptRepository = aptRepository;
    }

    @Override
    public List<AppointmentDTO> findAll() {
        return aptRepository.findAll().stream().map(AppointmentDTO::new).collect(Collectors.toList());
    }

    @Override
    public Optional<AppointmentDTO> findById(Long id) {
        return aptRepository.findById(id).map(apt -> new AppointmentDTO(apt));
    }

    @Override
    public Optional<AppointmentDTO> save(Appointment apt) {
        Optional<Appointment> savedAppointment = Optional.of(aptRepository.save(apt));

        if(savedAppointment.isPresent()) {
            return Optional.of(new AppointmentDTO(savedAppointment.get()));
        }
        else return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        aptRepository.deleteById(id);
    }

    @Override
    public List<AppointmentDTO> findByUserId(Long userId) {
        return aptRepository.findByUserId1EqualsOrUserId2Equals(userId, userId).stream().map(AppointmentDTO::new).collect(Collectors.toList());
    }
}

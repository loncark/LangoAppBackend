package com.loncark.langoapp.repository;

import com.loncark.langoapp.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAll();

    Optional<Appointment> findById(Long id);

    void deleteById(Long id);

    Appointment save(Appointment apt);

    List<Appointment> findByUserId1EqualsOrUserId2Equals(Long userId1, Long userId2);

    void deleteByUserId1EqualsOrUserId2Equals(Long userId1, Long userId2);
}

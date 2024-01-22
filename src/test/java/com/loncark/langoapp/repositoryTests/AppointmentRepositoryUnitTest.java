package com.loncark.langoapp.repositoryTests;

import com.loncark.langoapp.MockDataTest;
import com.loncark.langoapp.domain.Appointment;
import com.loncark.langoapp.repository.AppointmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AppointmentRepositoryUnitTest extends MockDataTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    public void testFindAll() {
        List<Appointment> actualAppointments = appointmentRepository.findAll();
        assertEquals(mockAppointments, actualAppointments);
    }

    @Test
    public void testFindById() {
        Optional<Appointment> actualAppointment = appointmentRepository.findById(1L);
        assertEquals(Optional.of(mockAppointments.get(0)), actualAppointment);
    }

    @Test
    public void testDeleteById() {
        appointmentRepository.deleteById(2L);
        assertFalse(appointmentRepository.findById(2L).isPresent());
    }

    @Test
    public void testSave() {
        Appointment newAppointment = new Appointment(6L, 5L, 8L, LocalDate.parse("2025-01-15"), "New appointment description.");
        Appointment savedAppointment = appointmentRepository.save(newAppointment);
        assertEquals(newAppointment, savedAppointment);
    }

    @Test
    public void testFindByUserId1EqualsOrUserId2Equals() {
        List<Appointment> actualAppointments = appointmentRepository.findByUserId1EqualsOrUserId2Equals(3L, 3L);
        assertEquals(3, actualAppointments.size());
    }
}


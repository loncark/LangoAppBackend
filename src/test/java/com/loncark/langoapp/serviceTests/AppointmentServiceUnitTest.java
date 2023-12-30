package com.loncark.langoapp.serviceTests;

import com.loncark.langoapp.MockDataTest;
import com.loncark.langoapp.domain.Appointment;
import com.loncark.langoapp.dto.AppointmentDTO;
import com.loncark.langoapp.repository.AppointmentRepository;
import com.loncark.langoapp.service.AppointmentService;
import com.loncark.langoapp.service.AppointmentServiceImpl;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(EasyMockRunner.class)
public class AppointmentServiceUnitTest extends MockDataTest {

    private final AppointmentRepository aptRepository = EasyMock.createMock(AppointmentRepository.class);
    private final AppointmentService appointmentService = new AppointmentServiceImpl(aptRepository);

    @Test
    public void GivenValidId_WhenFindById_ThenReturnAppointmentDTO() {
        Long aptId = 1L;
        Optional<Appointment> mockAppointment = Optional.of(mockAppointments.get(0));
        expect(aptRepository.findById(aptId)).andReturn(mockAppointment);
        replay(aptRepository);

        Optional<AppointmentDTO> actualResult = appointmentService.findById(aptId);

        Optional<AppointmentDTO> expectedResult = Optional.of(mockAppointmentDTOs.get(0));
        assertEquals(actualResult, expectedResult);
        verify(aptRepository);
    }

    @Test
    public void GivenValidAppointment_WhenSave_ThenReturnSavedAppointmentDTO() {
        Appointment appointment = new Appointment(null, 3L, 7L, LocalDate.parse("2024-07-08"), "Practicing Russian");
        Appointment savedAppointment = new Appointment(6L, 3L, 7L, LocalDate.parse("2024-07-08"), "Practicing Russian");
        expect(aptRepository.save(appointment)).andReturn(savedAppointment);
        replay(aptRepository);

        Optional<AppointmentDTO> actualResult = appointmentService.save(appointment);

        Optional<AppointmentDTO> expectedResult = Optional.of(new AppointmentDTO(savedAppointment));
        assertEquals(actualResult.get(), expectedResult.get());
        verify(aptRepository);
    }

    @Test
    public void GivenValidId_WhenDeleteById_ThenRepositoryCalled() {
        Long aptId = 3L;
        aptRepository.deleteById(aptId);
        expectLastCall().once();
        replay(aptRepository);

        appointmentService.deleteById(aptId);

        verify(aptRepository);
    }

    @Test
    public void GivenValidUserId_WhenFindByUserId_ThenReturnAppointmentDTOList() {
        Long userId = 6L;
        expect(aptRepository.findByUserId1EqualsOrUserId2Equals(userId, userId)).andReturn(
                Arrays.asList(mockAppointments.get(0), mockAppointments.get(2), mockAppointments.get(3))
        );
        replay(aptRepository);

        List<AppointmentDTO> actualResult = appointmentService.findByUserId(userId);

        assertEquals(actualResult, Arrays.asList(mockAppointmentDTOs.get(0), mockAppointmentDTOs.get(2), mockAppointmentDTOs.get(3)));
        verify(aptRepository);
    }

    @Test
    public void GivenValidId_WhenFindAll_ThenReturnAllAppointmentDTOs() {
        expect(aptRepository.findAll()).andReturn(mockAppointments);
        replay(aptRepository);

        List<AppointmentDTO> actualResult = appointmentService.findAll();

        assertEquals(actualResult, mockAppointmentDTOs);
        verify(aptRepository);
    }
}


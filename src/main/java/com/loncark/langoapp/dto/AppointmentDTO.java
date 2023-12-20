package com.loncark.langoapp.dto;

import com.loncark.langoapp.domain.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    Long id;
    Long userId1;
    Long userId2;
    LocalDate aptDate;
    String description;

    public AppointmentDTO(Appointment apt){
        this.aptDate = apt.getAptDate();
        this.description = apt.getDescription();
        this.id = apt.getId();
        this.userId1 = apt.getUserId1();
        this.userId2 = apt.getUserId2();
    }
}

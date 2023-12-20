package com.loncark.langoapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "appointment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "user_id_1")
    Long userId1;
    @Column(name = "user_id_2")
    Long userId2;
    @Column(name = "apt_date")
    LocalDate aptDate;
    String description;
}

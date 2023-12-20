package com.loncark.langoapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "review")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "reviewer_id")
    Long reviewerId;
    @Column(name = "reviewee_id")
    Long revieweeId;
    @Column(name = "review_date")
    LocalDate reviewDate;
    Long stars;
    @Column(name = "review_text")
    String reviewText;
}

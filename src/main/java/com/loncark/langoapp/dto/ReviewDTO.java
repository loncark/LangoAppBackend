package com.loncark.langoapp.dto;

import com.loncark.langoapp.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    Long id;
    Long reviewerId;
    Long revieweeId;
    LocalDate reviewDate;
    Long stars;
    String reviewText;

    public ReviewDTO(Review r){
        this.id = r.getId();
        this.reviewDate = r.getReviewDate();
        this.stars = r.getStars();
        this.reviewerId = r.getReviewerId();
        this.revieweeId = r.getRevieweeId();
        this.reviewText = r.getReviewText();
    }
}

package com.loncark.langoapp.service;

import com.loncark.langoapp.domain.Review;
import com.loncark.langoapp.dto.ReviewDTO;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<ReviewDTO> findAll();

    Optional<ReviewDTO> findById(Long id);

    Optional<ReviewDTO> save(Review review);

    void deleteById(Long id);
}

package com.loncark.langoapp.service;

import com.loncark.langoapp.domain.Review;
import com.loncark.langoapp.dto.ReviewDTO;
import com.loncark.langoapp.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll().stream().map(ReviewDTO::new).collect(Collectors.toList());
    }

    @Override
    public Optional<ReviewDTO> findById(Long id) {
        return reviewRepository.findById(id).map(review -> new ReviewDTO(review));
    }

    @Override
    public Optional<ReviewDTO> save(Review review) {
        Optional<Review> savedReview = Optional.of(reviewRepository.save(review));

        if(savedReview.isPresent()) {
            return Optional.of(new ReviewDTO(savedReview.get()));
        }
        else return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
}

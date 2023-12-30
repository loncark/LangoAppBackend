package com.loncark.langoapp.repositoryTests;

import com.loncark.langoapp.MockDataTest;
import com.loncark.langoapp.domain.Review;
import com.loncark.langoapp.repository.ReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewRepositoryUnitTest extends MockDataTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void testFindAll() {
        List<Review> actualReviews = reviewRepository.findAll();
        assertEquals(mockReviews, actualReviews);
    }

    @Test
    public void testFindById() {
        Optional<Review> actualReview = reviewRepository.findById(1L);
        assertEquals(Optional.of(mockReviews.get(0)), actualReview);
    }

    @Test
    public void testDeleteById() {
        reviewRepository.deleteById(2L);
        assertFalse(reviewRepository.findById(2L).isPresent());
    }

    @Test
    public void testSave() {
        Review newReview = new Review(6L, 5L, 1L, LocalDate.parse("2022-01-15"), 4L, "Helpful and knowledgeable. Enjoyed the session.");
        Review savedReview = reviewRepository.save(newReview);
        assertEquals(newReview, savedReview);
    }

    @Test
    public void testFindByRevieweeId() {
        List<Review> actualReviews = reviewRepository.findByRevieweeId(6L);
        assertEquals(2, actualReviews.size());
    }
}

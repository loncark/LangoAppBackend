package com.loncark.langoapp.serviceTests;

import com.loncark.langoapp.MockDataTest;
import com.loncark.langoapp.domain.Review;
import com.loncark.langoapp.dto.ReviewDTO;
import com.loncark.langoapp.repository.ReviewRepository;
import com.loncark.langoapp.service.ReviewService;
import com.loncark.langoapp.service.ReviewServiceImpl;
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
public class ReviewServiceUnitTest extends MockDataTest {

    private final ReviewRepository reviewRepository = EasyMock.createMock(ReviewRepository.class);
    private final ReviewService reviewService = new ReviewServiceImpl(reviewRepository);

    @Test
    public void GivenValidId_WhenFindById_ThenReturnReviewDTO() {

        Long reviewId = 1L;
        Optional<Review> mockReview = Optional.of(mockReviews.get(0));
        expect(reviewRepository.findById(reviewId)).andReturn(mockReview);
        replay(reviewRepository);

        Optional<ReviewDTO> actualResult = reviewService.findById(reviewId);

        Optional<ReviewDTO> expectedResult = Optional.of(mockReviewDTOs.get(0));

        assertEquals(actualResult, expectedResult);
        verify(reviewRepository);
    }

    @Test
    public void GivenValidReview_WhenSave_ThenReturnSavedReviewDTO() {

        Review review = new Review(null, 2L, 6L, LocalDate.parse("2022-02-06"), 5L, "Great guy. Wish I had met him sooner. Great speaker and teacher!");
        Review savedReview = new Review(6L, 2L, 6L, LocalDate.parse("2022-02-06"), 5L, "Great guy. Wish I had met him sooner. Great speaker and teacher!");
        expect(reviewRepository.save(review)).andReturn(savedReview);
        replay(reviewRepository);

        Optional<ReviewDTO> actualResult = reviewService.save(review);

        Optional<ReviewDTO> expectedResult = Optional.of(new ReviewDTO(savedReview));

        assertEquals(actualResult.get(), expectedResult.get());
        verify(reviewRepository);
    }

    @Test
    public void GivenValidId_WhenDeleteById_ThenRepositoryCalled() {

        Long reviewId = 3L;
        reviewRepository.deleteById(reviewId);
        expectLastCall().once();
        replay(reviewRepository);

        reviewService.deleteById(reviewId);

        verify(reviewRepository);
    }

    @Test
    public void GivenValidRevieweeId_WhenFindByRevieweeId_ThenReturnReviewDTOList() {

        Long revieweeId = 7L;
        expect(reviewRepository.findByRevieweeId(revieweeId)).andReturn(Arrays.asList(mockReviews.get(1), mockReviews.get(4)));
        replay(reviewRepository);

        List<ReviewDTO> actualResult = reviewService.findByRevieweeId(revieweeId);

        assertEquals(actualResult, Arrays.asList(mockReviewDTOs.get(1), mockReviewDTOs.get(4)));
        verify(reviewRepository);
    }

    @Test
    public void GivenValidId_WhenFindAll_ThenReturnReviewDTOList() {

        expect(reviewRepository.findAll()).andReturn(mockReviews);
        replay(reviewRepository);

        List<ReviewDTO> actualResult = reviewService.findAll();

        assertEquals(actualResult, mockReviewDTOs);
        verify(reviewRepository);
    }
}


package com.loncark.langoapp.repository;

import com.loncark.langoapp.domain.Review;
import com.loncark.langoapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAll();

    Optional<Review> findById(Long id);

    void deleteById(Long id);

    Review save(Review review);

    List<Review> findByRevieweeId(Long id);
}

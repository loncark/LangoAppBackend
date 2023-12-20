package com.loncark.langoapp.controller;

import com.loncark.langoapp.domain.Review;
import com.loncark.langoapp.dto.ReviewDTO;
import com.loncark.langoapp.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @RequestMapping
    public List<ReviewDTO> getAll() {
        return reviewService.findAll();
    }

    @GetMapping("{id}")
    public ReviewDTO getById(@PathVariable final String id) {
        return reviewService.findById(Long.parseLong(id))
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review was not found by that id")
                );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ReviewDTO save(@Valid @RequestBody final Review review) {
        return reviewService.save(review)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "A review with the same id already exists"));
    }

    @PutMapping
    public ReviewDTO update(@Valid @RequestBody final Review review) {
        return reviewService.save(review)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A review was not found by that id"));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        reviewService.deleteById(Long.parseLong(id));
    }
}

package com.loncark.langoapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loncark.langoapp.dto.ReviewDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/reviews/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reviewerId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.revieweeId").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stars").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reviewDate").value("2022-02-06"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reviewText").value("Great guy. Wish I had met him sooner. Great speaker and teacher!"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/reviews/9999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/reviews/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/reviews/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testSave() throws Exception {
        ReviewDTO newReview = new ReviewDTO();
        newReview.setReviewerId(3L);
        newReview.setRevieweeId(7L);
        newReview.setStars(4L);
        newReview.setReviewDate(LocalDate.parse("2022-08-01"));
        newReview.setReviewText("We got off on the wrong foot but the guy is still great. Would not recommend to complete beginners.");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newReview)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reviewerId").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.revieweeId").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stars").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reviewDate").value("2022-08-01"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reviewText").value("We got off on the wrong foot but the guy is still great. Would not recommend to complete beginners."));
    }

    @Test
    public void testUpdate() throws Exception {
        ReviewDTO updatedReview = new ReviewDTO();
        updatedReview.setId(4L);
        updatedReview.setReviewerId(6L);
        updatedReview.setRevieweeId(8L);
        updatedReview.setStars(2L);
        updatedReview.setReviewDate(LocalDate.parse("2022-03-04"));
        updatedReview.setReviewText("Total prick. Knowing four languages does not mean you should boast all the time. Never again.");

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedReview)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reviewerId").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.revieweeId").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stars").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reviewDate").value("2022-03-04"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reviewText").value("Total prick. Knowing four languages does not mean you should boast all the time. Never again."));
    }
}


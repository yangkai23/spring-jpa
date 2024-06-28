package com.clvt.jpa.service;

import com.clvt.jpa.model.Review;
import com.clvt.jpa.records.Response;
import com.clvt.jpa.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    public Response<Review> saveReview(Review review) {
        Review review1 = reviewRepository.saveReview(review);
        return new Response<>(List.of(review1), HttpStatus.OK.value());
    }

    public Response<Review> findReviewById(String id) {
        Review review = reviewRepository.findById(Long.parseLong(id));
        return new Response<>(List.of(review), HttpStatus.OK.value());
    }

    public Response<Review> deleteReviewById(String id) {
        Review review = reviewRepository.deleteReview(Long.parseLong(id));
        return new Response<>(List.of(review), HttpStatus.OK.value());
    }


    public void saveReviews(List<Review> reviews) {
        for(Review review : reviews){
            saveReview(review);
        }
    }

    public Response<Review> findAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return new Response<>(reviews,HttpStatus.OK.value())    ;
    }
}

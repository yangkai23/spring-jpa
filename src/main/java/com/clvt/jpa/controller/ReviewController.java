package com.clvt.jpa.controller;

import com.clvt.jpa.model.Course;
import com.clvt.jpa.model.Review;
import com.clvt.jpa.records.Response;
import com.clvt.jpa.service.CourseService;
import com.clvt.jpa.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("review")
public class ReviewController {
    @Autowired
    ReviewService service;


    @PostMapping("/save")
    public Response<Review> saveReview(@RequestBody Review review) {
        return service.saveReview(review);
    }

   @GetMapping("/findCandidate")
    public Response<Review> findReviewById(@RequestParam String id) {
        return service.findReviewById(id);
    }
    @DeleteMapping("/deleteReview")
    public Response<Review> deleteReviewById(@RequestParam String id) {
        return service.deleteReviewById(id);
    }
    @GetMapping("/findAllReviews")
    public Response<Review> findAllReviews() {
        return service.findAllReviews();
    }

}

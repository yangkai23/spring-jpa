package com.clvt.jpa.service;

import com.clvt.jpa.model.Course;
import com.clvt.jpa.records.Response;
import com.clvt.jpa.repository.CourseJpaRepository;
import com.clvt.jpa.repository.CourseRepository;
import com.clvt.jpa.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    ReviewService reviewService;
    @Autowired
    CourseJpaRepository courseJpaRepository;
    public Response<Course> saveCourse(Course course) {
        reviewService.saveReviews(course.getReviews());
        Course course1 = courseJpaRepository.save(course);
        return new Response<>(List.of(course1), HttpStatus.OK.value());
    }

    public Response<Course> findCourseById(String id) {
        Course course = courseRepository.findById(Long.parseLong(id));
        return new Response<>(List.of(course), HttpStatus.OK.value());
    }

    public Response<Course> deleteCourseById(String id) {
        Course course = courseRepository.deleteCourse(Long.parseLong(id));
        return new Response<>(List.of(course), HttpStatus.OK.value());
    }


    public Response<Course> findAllCourses() {
        List<Course> allCourses = courseRepository.findAll();
        return new Response<>(allCourses,HttpStatus.OK.value());
    }
    /*public Specification<Course> isNull(String parameter,String value,Long id){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.isNull(criteriaBuilder.equal(root.get("id"),id)).i);
    }*/
}

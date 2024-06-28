package com.clvt.jpa.controller;

import com.clvt.jpa.model.Course;
import com.clvt.jpa.records.Response;
import com.clvt.jpa.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    CourseService service;

    @PostMapping("/save")
    public Response<Course> saveCourse(@RequestBody Course course) {
        return service.saveCourse(course);
    }

   @GetMapping("/findCourse")
    public Response<Course> findCourseById(@RequestParam String id) {
        return service.findCourseById(id);
    }
    @DeleteMapping("/deleteCourse")
    public Response<Course> deleteCourseById(@RequestParam String id) {
        return service.deleteCourseById(id);
    }
    @GetMapping("/findAllCourses")
    public Response<Course> findAllCourses() {
        return service.findAllCourses();
    }
}

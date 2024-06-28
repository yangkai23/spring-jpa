package com.clvt.jpa.controller;

import com.clvt.jpa.model.Course;
import com.clvt.jpa.model.Student;
import com.clvt.jpa.records.Response;
import com.clvt.jpa.service.CourseService;
import com.clvt.jpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentService service;


    @PostMapping("/save")
    public Response<Student> savePassportAndCandidate(@RequestBody Student student) {
        return service.saveStudent(student);
    }
    /*@PostMapping("/savestudentwithpassport")
    public Response<Student> saveStudentWithPassport(@RequestBody Student student) {
        return service.saveStudent(student);
    }*/
   @GetMapping("/findStudent")
    public Response<Student> findStudentById(@RequestParam String id) {
        return service.findStudentById(id);
    }
    @DeleteMapping("/deleteStudent")
    public Response<Student> deleteStudentById(@RequestParam String id) {
        return service.deleteStudentById(id);
    }

}

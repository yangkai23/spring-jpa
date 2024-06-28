package com.clvt.jpa.service;

import com.clvt.jpa.model.Course;
import com.clvt.jpa.model.Student;
import com.clvt.jpa.records.Response;
import com.clvt.jpa.repository.CourseRepository;
import com.clvt.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PassportService passportService;
    public Response<Student> saveStudent(Student student) {
        String passport = passportService.constructPassportNumber(student.getPassport().getNumber());
        student.getPassport().setNumber(passport);
        Student student1 = studentRepository.saveStudent(student);
        return new Response<>(List.of(student1), HttpStatus.OK.value());
    }

    public Response<Student> findStudentById(String id) {
        Student student = studentRepository.findById(Long.parseLong(id));
        return new Response<>(List.of(student), HttpStatus.OK.value());
    }

    public Response<Student> deleteStudentById(String id) {
        Student Student = studentRepository.deleteStudent(Long.parseLong(id));
        return new Response<>(List.of(Student), HttpStatus.OK.value());
    }



}

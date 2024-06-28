package com.clvt.jpa.controller;

import com.clvt.jpa.model.Course;
import com.clvt.jpa.model.Passport;
import com.clvt.jpa.records.Response;
import com.clvt.jpa.service.CourseService;
import com.clvt.jpa.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("passport")
public class PassportController {
    @Autowired
    PassportService service;


    @PostMapping("/save")
    public Response<Passport> savePassport(@RequestBody Passport passport) {
        return service.savePassport(passport);
    }

   @GetMapping("/findPassport")
    public Response<Passport> findPassportById(@RequestParam String id) {
        return service.findPassportById(id);
    }
    @DeleteMapping("/deletePassport")
    public Response<Passport> deletePassportById(@RequestParam String id) {
        return service.deletePassportById(id);
    }
    @GetMapping("/findAllPassports")
    public Response<Passport> findAllPassports() {
        return service.findAllPassports();
    }

}

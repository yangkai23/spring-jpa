package com.clvt.jpa.service;

import com.clvt.jpa.model.Passport;
import com.clvt.jpa.records.Response;
import com.clvt.jpa.repository.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PassportService {
    @Autowired
    PassportRepository passportRepository;

    public Response<Passport> savePassport(Passport passport) {
        String number=passport.getNumber();
        passport.setNumber(constructPassportNumber(number));
        Passport passport1 = passportRepository.savePassport(passport);
        return new Response<>(List.of(passport1), HttpStatus.OK.value());
    }

    public Response<Passport> findPassportById(String id) {
        Passport passport = passportRepository.findByIdNumber(id);
        return new Response<>(List.of(passport), HttpStatus.OK.value());
    }

    public Response<Passport> deletePassportById(String id) {
        Passport passport = passportRepository.deletePassport(Long.parseLong(id));
        return new Response<>(List.of(passport), HttpStatus.OK.value());
    }
    public String constructPassportNumber(String idNum){
        return getRandomSequence(5) + idNum + getRandomSequence(1);
    }

    private String getRandomSequence(int numOfChar) {
        Random random = new Random();
        StringBuilder seq = new StringBuilder();
        for (int i = 1; i <= numOfChar; i++)
            seq.append((char) (random.nextInt(26) + 'A'));
        return seq.toString();
    }

    public Response<Passport> findAllPassports() {
        List<Passport> passports= passportRepository.findAll();
        return new Response<>(passports,HttpStatus.OK.value()) ;
    }
}

package com.clvt.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Embeddable
@NamedQueries(value = {
        @NamedQuery(name = "all_passports", query = "select c from Passport c "),
        @NamedQuery(name = "filter_passport", query = "select c from Passport c where c.number = :name")})
//@Inheritance(strategy = InheritanceType.JOINED)
public class Passport {


    @Id
    @GeneratedValue(generator = "passport_seq", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "passport_seq", sequenceName = "PASSPORT_SEQ", allocationSize = 1,initialValue = 100)
    private Long id;
    @Column(name="passport_number",nullable = false,unique = true)
    private String number;

    public Passport(String number) {
        this.number=number;
    }
}

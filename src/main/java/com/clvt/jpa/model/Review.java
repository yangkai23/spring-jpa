package com.clvt.jpa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "all_reviews", query = "select c from Review c ")
public class Review {


    @Id
    @GeneratedValue(generator = "review_seq", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "review_seq", sequenceName = "REVIEW_SEQ", allocationSize = 1,initialValue = 5000)
    private Long id;
    @Column(nullable = false)
    private String rating;
    private String description;

    public Review(String rating) {
        this.rating=rating;
    }
}

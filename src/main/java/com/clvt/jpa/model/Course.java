package com.clvt.jpa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries(value = {
        @NamedQuery(name = "all_courses", query = "select c from Course c "),
        @NamedQuery(name = "filter_course", query = "select c from Course c where c.name = :name")})
public class Course {

    @Id
    @GeneratedValue(generator = "course_seq", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "course_seq", sequenceName = "COURSE_SEQ", allocationSize = 1)
    private Long id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(name = "created_date", updatable = false)
//    @CreationTimestamp
    private Timestamp createdDate;
    @Column(name = "modified_date")
//    @UpdateTimestamp
    private Timestamp modifiedDate;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id",referencedColumnName = "id")
    private List<Review> reviews = new ArrayList<>();

    public Course(String microservices) {
        this.name = microservices;
    }
    public void addReview(Review review){
        reviews.add(review);
    }
    public void deleteReview(Review review){
        reviews.remove(review);
    }
    @PrePersist
    protected void onPersist(){
        if(this.createdDate==null)
            this.createdDate=new Timestamp(System.currentTimeMillis());
    }
    @PreUpdate
    protected void preUpdate(){
            this.modifiedDate=new Timestamp(System.currentTimeMillis());
    }
}

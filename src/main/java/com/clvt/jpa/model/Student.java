package com.clvt.jpa.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries(value = {@NamedQuery(name = "all_students", query = "select c from Student c "), @NamedQuery(name = "filter_student", query = "select c from Student c where c.name = :name")})
public class Student {


    @Id
    @GeneratedValue(generator = "student_seq", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "student_seq", sequenceName = "STUDENT_SEQ", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String name;
//    @Embedded
//    @AttributeOverrides(@AttributeOverride(name = "id", column = @Column(name = "passport_id")))
    @OneToOne
    private Passport passport;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "STUDENT_COURSE",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    private List<Course> courses = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

}

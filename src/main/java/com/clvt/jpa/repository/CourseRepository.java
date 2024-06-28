package com.clvt.jpa.repository;

import com.clvt.jpa.model.Course;
import com.clvt.jpa.model.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {
    @Autowired
    EntityManager em;
    @Autowired
    ReviewRepository reviewRepository;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }
@Transactional
    public Course saveCourse(Course course) {
        List<Review> reviews = course.getReviews();
        persistNewReviews(reviews);
        List<Review> results = setReviews(reviews);
        course.setReviews(results);
        if (course.getId() == null) em.persist(course);
        else em.merge(course);
        em.flush();
        return course.getId() == null ? (Course) (em.createNativeQuery("select * from course where id in (select max(id) from course)", Course.class).getSingleResult()) : findById(course.getId());
    }

    private List<Review> setReviews(List<Review> reviews) {
        List<Review> results=new ArrayList<>();
        for(Review review:reviews){
            Review byId = reviewRepository.findById(review.getId());
            results.add(byId);
        }
        return results;
    }

    private void persistNewReviews(List<Review> reviews) {
        if (reviews != null) {
            reviewRepository.saveReviews(reviews);
        }
    }

    public Course deleteCourse(Long id) {
        Course course = findById(id);
        em.remove(course);
        return course;
    }

    public void playWithEntityManager() {
        Course microservices = new Course("Microservices");
        logger.info("course : {} ", microservices);
        em.persist(microservices);
        microservices.setName("Microservices - Updated");
        logger.info("course : {} ", microservices);
    }

    public void playWithEntityManager2() {
//        TypedQuery<Course> courseList = em.createQuery("select c from Course c where c.name= ?1 ", Course.class);
        TypedQuery<Course> courseList = em.createQuery("select c from Course c where c.name= :name ", Course.class);
        logger.info("jpql where2 : {} ", courseList.setParameter("name", "java-in-depth").getSingleResult());
    }

    public List<Course> findAll() {
        TypedQuery<Course> allCourses = em.createNamedQuery("all_courses", Course.class);
        return allCourses.getResultList();
    }
}
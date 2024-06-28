package com.clvt.jpa.repository;

import com.clvt.jpa.model.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ReviewRepository {
    @Autowired
    EntityManager em;
    Logger logger= LoggerFactory.getLogger(this.getClass());

    public Review findById(Long id){
        return em.find(Review.class,id);
    }
    public Review saveReview(Review review){
        if(review.getId()==null)
        em.persist(review);
        else
            em.merge(review);
        em.flush();
        return findById(review.getId());
    }
    public Review deleteReview(Long id){
        Review review = findById(id);
        em.remove(review);
        return review;
    }
    /*public void playWithEntityManager(){
        Review microservices = new Review("Microservices");
        logger.info("review : {} ",microservices);
        em.persist(microservices);
        microservices.set("Microservices - Updated");
        logger.info("review : {} ",microservices);
    }*/
    public void playWithEntityManager2(){
//        TypedQuery<Review> reviewList = em.createQuery("select c from Review c where c.name= ?1 ", Review.class);
        TypedQuery<Review> reviewList = em.createQuery("select c from Review c where c.name= :name ", Review.class);
        logger.info("jpql where2 : {} ",reviewList.setParameter("name","java-in-depth").getSingleResult());}

    public List<Review> findAll() {
        TypedQuery<Review> allReviews = em.createNamedQuery("all_reviews", Review.class);
        return allReviews.getResultList();
    }

    public void saveReviews(List<Review> reviews) {
        for(Review review:reviews)
            saveReview(review);
    }
}
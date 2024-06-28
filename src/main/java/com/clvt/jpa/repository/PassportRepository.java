package com.clvt.jpa.repository;

import com.clvt.jpa.model.Passport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Repository
@Transactional
public class PassportRepository {
    @Autowired
    EntityManager em;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Passport findById(Long id) {
        return em.find(Passport.class, id);
    }
    public Passport findByIdNumber(String num) {
        TypedQuery<Passport> filterPassport = em.createNamedQuery("filter_passport", Passport.class);
        return filterPassport.setParameter("name",num).getSingleResult();
    }

    public Passport savePassport(Passport passport) {
        if (passport.getId() == null)
            em.persist(passport);
        else
            em.merge(passport);
        em.flush();
        return findById(passport.getId());
    }

    public Passport deletePassport(Long id) {
        Passport passport = findById(id);
        em.remove(passport);
        return passport;
    }

    public void playWithEntityManager() {
        Passport microservices = new Passport("Microservices");
        logger.info("passport : {} ", microservices);
        em.persist(microservices);
        microservices.setNumber("Microservices - Updated");
        logger.info("passport : {} ", microservices);
    }

    public void playWithEntityManager2() {
//        TypedQuery<Passport> passportList = em.createQuery("select c from Passport c where c.name= ?1 ", Passport.class);
        TypedQuery<Passport> passportList = em.createQuery("select c from Passport c where c.name= :name ", Passport.class);
        logger.info("jpql where2 : {} ", passportList.setParameter("name", "java-in-depth").getSingleResult());
    }

    public List<Passport> findAll() {
        new Specification<>() {
            @Override
            public Predicate toPredicate(Root<Object> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
        TypedQuery<Passport> passportList = em.createNamedQuery("all_passports", Passport.class);
        return passportList.getResultList();
    }
}
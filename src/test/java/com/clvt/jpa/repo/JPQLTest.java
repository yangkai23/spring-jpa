package com.clvt.jpa.repo;

import com.clvt.jpa.model.Course;
import com.clvt.jpa.repository.CourseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

//@ExtendWith(SpringExtension.class)
@Repository
@SpringBootTest
@Transactional
public class JPQLTest {
    @Autowired
    EntityManager em;
    Logger logger= LoggerFactory.getLogger(this.getClass());

    @Test
    public void jpql_basic() {
        Query selectCFromCourseC = em.createQuery("select c from Course c");
        logger.info("jpql basic : {} ",selectCFromCourseC.getResultList());
    }

    @Test
    public void jpql_typed() {
        TypedQuery<Course> courseList = em.createQuery("select c from Course c", Course.class);
        logger.info("jpql basic : {} ",courseList.getResultList());
    }
    @Test
    public void jpql_where() {
        TypedQuery<Course> courseList = em.createQuery("select c from Course c where c.name='java-in-depth'", Course.class);
        logger.info("jpql where : {} ",courseList.getResultList());
    }
    @Test
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void jpql_typed_named() {
        TypedQuery<Course> courseList = em.createNamedQuery("all_courses", Course.class);
        logger.info(" jpql_typed_named : {} ",courseList.getResultList());
    }
    @Test
    public void jpql_typed_named_param() {
        TypedQuery<Course> courseList = em.createNamedQuery("filter_course", Course.class);
        courseList.setParameter("name","Microservices");
        logger.info(" jpql_typed_named_param : {} ",courseList.getResultList());
    }
    @Test
    public void jpql_typed_param() {
        TypedQuery<Course> courseList = em.createQuery("select c from Course c where c.name = ?1", Course.class);
        courseList.setParameter(1,"Hypermesh guide");
        logger.info(" jpql_typed_param : {} ",courseList.getResultList());
    }
}

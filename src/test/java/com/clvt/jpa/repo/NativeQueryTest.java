package com.clvt.jpa.repo;

import com.clvt.jpa.model.Course;
import com.clvt.jpa.repository.CourseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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
@Transactional(Isolation.READ_COMMITTED)
public class NativeQueryTest {
    @Autowired
    EntityManager em;
    Logger logger= LoggerFactory.getLogger(this.getClass());

    @Test
    public void native_query_basic() {
        Query nativeQuery =em.createNativeQuery("select * from course where id in (select max(id) from course)",Course.class);
        logger.info(" native query : {} ",nativeQuery.getResultList());
    }


}

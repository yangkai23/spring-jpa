package com.clvt.jpa.repo;

import com.clvt.jpa.model.Course;
import com.clvt.jpa.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

//@ExtendWith(SpringExtension.class)
@Repository
@SpringBootTest
@Transactional
public class CourseRepositoryTest {
    @Autowired
    CourseRepository courseRepository;

    @Test
    public void findById_basic() {
        Course course = courseRepository.findById(1L);
        assertEquals("Hypermesh guide", course.getName());
    }

    @Test
    @DirtiesContext
    public void deleteById_basic() {
        courseRepository.deleteCourse(1L);
        assertNull(courseRepository.findById(1L));
    }

    @Test
    public void playWithEntityManager() {
        courseRepository.playWithEntityManager();
    }
}

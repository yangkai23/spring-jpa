package com.clvt.jpa.repository;

import com.clvt.jpa.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseJpaRepository extends JpaRepository<Course,Long>, JpaSpecificationExecutor<Course> , PagingAndSortingRepository<Course,Long> {

}

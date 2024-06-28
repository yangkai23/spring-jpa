package com.clvt.jpa.repository;

import com.clvt.jpa.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StudentRepository {
    @Autowired
    EntityManager em;
    Logger logger= LoggerFactory.getLogger(this.getClass());

    public Student findById(Long id){
        return em.find(Student.class,id);
    }
    public Student saveStudent(Student student){
        if(student.getId()==null)
        em.persist(student);
        else
            em.merge(student);
        em.flush();
        return findById(student.getId());
    }
    public Student deleteStudent(Long id){
        Student student = findById(id);
        em.remove(student);
        return student;
    }
    public void playWithEntityManager(){
        Student microservices = new Student("Microservices");
        logger.info("student : {} ",microservices);
        em.persist(microservices);
        microservices.setName("Microservices - Updated");
        logger.info("student : {} ",microservices);
    }
    public void playWithEntityManager2(){
//        TypedQuery<Student> studentList = em.createQuery("select c from Student c where c.name= ?1 ", Student.class);
        TypedQuery<Student> studentList = em.createQuery("select c from Student c where c.name= :name ", Student.class);
        logger.info("jpql where2 : {} ",studentList.setParameter("name","java-in-depth").getSingleResult());}}
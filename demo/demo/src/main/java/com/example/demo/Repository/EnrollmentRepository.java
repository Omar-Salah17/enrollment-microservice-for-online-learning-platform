package com.example.demo.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    Optional<Enrollment> findByid(long id);

    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

    List<Enrollment>  findByCourseId(Long courseId);

    List<Enrollment> findByStatus(boolean status);

    List<Enrollment> findByCourseIdAndStatus(Long courseId, boolean status);
    
    List<Enrollment> findByStatusAndStudentId(boolean status, Long studentId);


 

}
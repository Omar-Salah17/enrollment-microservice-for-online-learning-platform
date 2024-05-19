package com.example.demo.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.example.demo.Repository.EnrollmentRepository;
import com.example.demo.Entity.Enrollment;

@Service
public class EnrollmentService {

     @Autowired
   private  EnrollmentRepository enrollmentRepository;

     public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public String enrollStudent(Long studentId, Long courseId) throws Exception {
        // Check if the student is already enrolled in the course
        if (isEnrolled(studentId, courseId)) {
          return  "Student is already enrolled in the course.";
        }
        
        
        // Create a new enrollment object
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(studentId);
        enrollment.setCourseId(courseId);
        
        // Save the enrollment in the database
        enrollmentRepository.save(enrollment);
        return "Request has been sent Successfully";
    }

    public String cancelEnrollment(Long enrollmentId) throws Exception {
        // Check if the enrollment exists
        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findById(enrollmentId);
        if (optionalEnrollment.isPresent()) {
            enrollmentRepository.delete(optionalEnrollment.get());
        } else {
            throw new Exception("Enrollment with id " + enrollmentId + " not found.");
        }
		return "cancel Enrollment is Done!";
    }
    
    private boolean isEnrolled(Long studentId, Long courseId) {
        
        return enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId);
    }


    public List<Enrollment> getPendingEnrollments(Long courseId) {
        // Fetch all pending enrollments for the specified course ID
        return enrollmentRepository.findByCourseIdAndStatus(courseId, false);
    }

    

    public String updateEnrollment(Long enrollmentId, boolean status) {
        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findById(enrollmentId);
        
        if (optionalEnrollment.isPresent()) {
            Enrollment enrollment = optionalEnrollment.get();
            enrollment.setStatus(status);
            enrollmentRepository.save(enrollment);
            return "Enrollment status updated successfully";
        } else {
            return "Enrollment not found with ID: " + enrollmentId;
        }
    }


    public List<Enrollment> GetAcceptEnrollment(Long studentId) {
        // Assuming you have a field named studentId in the Enrollment entity
        return enrollmentRepository.findByStatusAndStudentId(true, studentId);
    }


    
  
   



}

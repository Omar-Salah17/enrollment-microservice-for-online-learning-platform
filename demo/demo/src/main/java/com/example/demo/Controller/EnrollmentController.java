package com.example.demo.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.Entity.Enrollment;
import com.example.demo.Service.EnrollmentService;

@RestController
public class EnrollmentController {


    @Autowired
    private final EnrollmentService enrollmentService;
    
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/enroll")
    public String enrollStudent(@RequestParam Long studentId, @RequestParam Long courseId) throws Exception {

      
        // Implement endpoint to enroll student in a course
       String s= enrollmentService.enrollStudent(studentId, courseId);
        return s;
        
    }

    @PostMapping("/cancel")
    public String cancelEnrollment(@RequestParam Long enrollmentId) throws Exception {

        enrollmentService.cancelEnrollment(enrollmentId);
        return"cancel Enrollment";

    }

    @GetMapping("/PendingEnrollment")
    public List<Enrollment> getPendingEnrollments(@RequestParam Long courseId) throws Exception {

    
        return enrollmentService.getPendingEnrollments(courseId);
    }
    @PutMapping("/UpdateEnrollment")
    public String updateEnrollment(@RequestParam Long enrollmentId,@RequestBody boolean status)
    {
      enrollmentService.updateEnrollment(enrollmentId, status);

        return "Update enrollment";
    }

    @GetMapping("/AcceptingEnrollment")
    public List<Enrollment> getAcceptEnrollment(@RequestParam Long studentId) throws Exception {
     
        return enrollmentService.GetAcceptEnrollment(studentId);
    }



    


    // Other endpoints as needed
}
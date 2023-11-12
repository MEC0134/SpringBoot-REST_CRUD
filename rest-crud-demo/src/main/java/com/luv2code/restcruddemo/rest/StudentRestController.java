package com.luv2code.restcruddemo.rest;

import com.luv2code.restcruddemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    @PostConstruct
    public void loadData() {

        theStudents = new ArrayList<>();

        theStudents.add(new Student("Poornima", "Patel"));
        theStudents.add(new Student("Mario", "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }


    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        // check if student id smaller or bigger then list size
        if(studentId >= theStudents.size() || (studentId < 0)) {
            // call constructor of StudentNotFoundException class
            throw new StudentNotFoundException("Student " + studentId + " not Found ");
        }

        return theStudents.get(studentId);
    }



}

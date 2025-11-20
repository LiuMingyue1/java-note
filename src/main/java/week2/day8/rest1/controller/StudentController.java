package week2.day8.rest1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import week2.day8.rest1.domain.Student;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping
    public ResponseEntity<?> getAllStudent() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStuById(@PathVariable String id) {
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createStu(@RequestBody Student student) {
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }
}

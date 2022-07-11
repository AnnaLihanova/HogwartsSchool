package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;
import ru.hogwarts.school.service.impl.StudentServiceImpl;

import java.util.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createNewStudent(@RequestBody Student student) {
        return studentService.createNewStudent(student);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> findStudent(@PathVariable Long id) {
        Student foundStudent = studentService.findStudent(id);
        if (foundStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student editStudent = studentService.editStudent(student);
        if (editStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editStudent);
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping
    public Collection<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/ageFiltr/{age}")
    public ResponseEntity<Collection<Student>> getStudentsByAge(@PathVariable int age) {
        Collection<Student> studentByAge = studentService.getStudentsByAge(age);
        if (studentByAge.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(studentByAge);
    }

    @GetMapping("/ageBetween/{min}/{max}")
    public Collection<Student> findByAgeBetween(@PathVariable int min, @PathVariable int max) {
        return studentService.findStudentByAgeBetween(min, max);
    }

    @GetMapping("/getFaculty/{id}")
    public ResponseEntity getFacultyOfStudentByIdOfStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getFacultyOfStudent(id));
    }

    @GetMapping("/getStudentByName/{name}")
    public ResponseEntity findStudentByNameContainsIgnoreCase(@PathVariable String name) {
        return ResponseEntity.ok(studentService.findStudentByNameContainsIgnoreCase(name));
    }

    @GetMapping("/count")
    public int getCountOfStudents() {
        return studentService.getCountOfStudents();
    }

    @GetMapping("/avg")
    public double getAvgAgeOfStudents() {
        return studentService.getAvgAgeOfStudents();
    }

    @GetMapping("/last5Students")
    public List<Student> getLastFiveStudents() {
        return studentService.getLastFiveStudents();
    }

    @GetMapping("/StudentsNameWithA")
    public List<String> getStudentsNameWithA() {
        return studentService.getStudentsNameWithA();
    }
}

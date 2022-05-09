package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    Student createNewStudent(Student student);

    Student findStudent(Long id);

    Student editStudent(Student student);

    Student deleteStudent(Long id);

    Map<Long, Student> getAllStudents();

    List<Student> getStudentsByAge(int age);
}

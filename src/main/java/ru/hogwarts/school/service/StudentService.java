package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface StudentService {

    Student createNewStudent(Student student);

    Student findStudent(Long id);

    Student editStudent(Student student);

    void deleteStudent(Long id);

    Collection<Student> getAllStudents();

    Collection<Student> getStudentsByAge(int age);
}

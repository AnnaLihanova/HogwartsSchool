package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private long id;

    @Override
    public Student createNewStudent(Student student) {
        student.setId(++id);
        students.put(id, student);
        return student;
    }


    @Override
    public Student findStudent(Long id) {
        return students.get(id);
    }

    @Override
    public Student editStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Student deleteStudent(Long id) {
        return students.remove(id);
    }

    @Override
    public Map<Long, Student> getAllStudents() {
        return students;
    }

    @Override
    public List<Student> getStudentsByAge(int age) {
        return students.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }
}

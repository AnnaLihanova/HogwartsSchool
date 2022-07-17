package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public Student createNewStudent(Student student) {
        logger.debug("Create a new student: {}", student);
        return studentRepository.save(student);
    }

    @Override
    public Student findStudent(Long id) {
        logger.debug("Find student by id: {}", id);
        return studentRepository.findById(id).get();
    }

    @Override
    public Student editStudent(Student student) {
        logger.debug("Student was edited: {}", student);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        logger.debug("Student was deleted by id: {}", id);
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        logger.debug("Received a list of students");
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getStudentsByAge(int age) {
        logger.debug("Received a list of students by age: {}", age);
        return studentRepository.findByAge(age);
    }

    @Override
    public Collection<Student> findStudentByAgeBetween(int min, int max) {
        logger.debug("Received a list of students by between min {} and max {} age", min, max);
        return studentRepository.findStudentsByAgeBetween(min, max);
    }

    @Override
    public String getFacultyOfStudent(Long id) {
        logger.debug("Received a faculty of student by id: {}", id);
        return studentRepository.findById(id).get() +
                studentRepository.findById(id).get().getFaculty().getName();
    }

    @Override
    public Student findStudentByNameContainsIgnoreCase(String name) {
        logger.debug("Find student by name: {}", name);
        return studentRepository.findStudentByNameContainsIgnoreCase(name);
    }

    @Override
    public int getCountOfStudents() {
        logger.debug("Received a count of student");
        return studentRepository.getCountOfStudents();
    }

    @Override
    public double getAvgAgeOfStudents() {
        logger.debug("Received a average age of student");
//        return studentRepository.getAvgAgeOfStudents();
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average().getAsDouble();
    }

    @Override
    public List<Student> getLastFiveStudents() {
        logger.debug("Received last five students");
        return studentRepository.getLastFiveStudents();
    }

    @Override
    public List<String> getStudentsNameWithA() {
        logger.debug("Received students whose name start with A");
        return getAllStudents().stream()
                .parallel()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public void getStudentsThreads() {
        List<Student> studentsThreads = getAllStudents();
        System.out.println(studentsThreads.get(0).getName());
        System.out.println(studentsThreads.get(1).getName());

        new Thread(() -> {
            System.out.println(studentsThreads.get(2).getName());
            System.out.println(studentsThreads.get(3).getName());
        }).start();

        new Thread(() -> {
            System.out.println(studentsThreads.get(4).getName());
            System.out.println(studentsThreads.get(5).getName());
        }).start();
    }

    @Override
    public synchronized void run(int id) {
        String student = studentRepository.findAll().get(id).getName();
        System.out.println(student);
    }

    @Override
    public void printNameSynchronized() {
        run(0);
        run(1);

        new Thread(() -> {
            run(4);
            run(5);
        }).start();

        new Thread(() -> {
            run(2);
            run(3);
        }).start();

    }
}

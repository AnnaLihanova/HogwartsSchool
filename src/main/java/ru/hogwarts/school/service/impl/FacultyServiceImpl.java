package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public Faculty createNewFaculty(Faculty faculty) {
        logger.debug("Create a new faculty: {}", faculty);
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(Long id) {
        logger.debug("Find faculty by id: {}", id);
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        logger.debug("Faculty was edited: {}", faculty);
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long id) {
        logger.debug("Faculty was deleted by id: {}", id);
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> getAllFaculties() {
        logger.debug("Received a list of faculty");
        return facultyRepository.findAll();
    }

    @Override
    public List<Faculty> getFacultiesByColor(String color) {
        logger.debug("Received a list of faculty by color: {}", color);
        return facultyRepository.getFacultiesByColor(color);
    }

    @Override
    public Faculty findFacultyByNameOrColor(String name, String color) {
        logger.debug("Received a faculty by name {} or color {}", name, color);
        return facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    @Override
    public Collection<Student> getStudentsOfFaculty(Long id) {
        logger.debug("Received a list of students by faculty id: {}", id);
        return facultyRepository.findById(id).get().getStudents();
    }
}

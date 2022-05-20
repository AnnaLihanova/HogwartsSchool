package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface FacultyService {

    Faculty createNewFaculty(Faculty faculty);

    Faculty findFaculty(Long id);

    Faculty editFaculty(Faculty faculty);

    void deleteFaculty(Long id);

    Collection<Faculty> getAllFaculties();

    List<Faculty> getFacultiesByColor(String color);

    Faculty findFacultyByNameOrColor(String name, String color);

    Collection<Student> getStudentsOfFaculty(Long id);
}

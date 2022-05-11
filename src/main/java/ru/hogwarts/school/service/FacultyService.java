package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.List;
import java.util.Map;

public interface FacultyService {

    Faculty createNewFaculty(Faculty faculty);

    Faculty findFaculty(Long id);

    Faculty editFaculty(Faculty faculty);

    Faculty deleteFaculty(Long id);

    Map<Long, Faculty> getAllFaculties();

    List<Faculty>  getFacultiesByColor(String color);
}

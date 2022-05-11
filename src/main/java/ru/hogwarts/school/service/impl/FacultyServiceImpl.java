package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long id;

    @Override
    public Faculty createNewFaculty(Faculty faculty) {
        faculty.setId(++id);
        faculties.put(id, faculty);
        return faculty;
    }

    @Override
    public Faculty findFaculty(Long id) {
        return faculties.get(id);
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    @Override
    public Faculty deleteFaculty(Long id) {
        return faculties.remove(id);
    }

    @Override
    public Map<Long, Faculty> getAllFaculties() {
        return faculties;
    }

    @Override
    public List<Faculty> getFacultiesByColor(String color) {
        return faculties.values().stream()
                .filter(student -> student.getColor().equals(color))
                .collect(Collectors.toList());
    }
}

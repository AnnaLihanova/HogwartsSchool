package ru.hogwarts.school;


import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.impl.AvatarServiceImpl;
import ru.hogwarts.school.service.impl.FacultyServiceImpl;
import ru.hogwarts.school.service.impl.StudentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FacultyControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private StudentServiceImpl studentService;

    @MockBean
    private AvatarRepository avatarRepository;

    @MockBean
    private AvatarServiceImpl avatarService;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyServiceImpl facultyService;

    @InjectMocks
    private FacultyController facultyController;

    private Long id = 1L;
    private String name = "TestName";
    private String nameIgnoreCase = "teSTnaMe";
    private String color = "testColor";

    private Long id2 = 2L;
    private String name2 = "TestName2";
    private String color2 = "testColor2";

    private String newName = "Ravenclaw";
    private String newColor = "Blue";

    private Faculty faculty = new Faculty();
    private Faculty faculty2 = new Faculty();
    private Faculty updatedFaculty = new Faculty();

    private List<Faculty> faculties = new ArrayList<>();


    @Test
    public void createNewFacultyTest() throws Exception {

        faculty.setId(id);
        faculty.setColor(color);
        faculty.setName(name);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void getFacultyTest() throws Exception {

        faculty.setId(id);
        faculty.setColor(color);
        faculty.setName(name);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void editFacultyTest() throws Exception {

        JSONObject facultyObj = new JSONObject();
        facultyObj.put("id", id);
        facultyObj.put("name", newName);
        facultyObj.put("color", newColor);

        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        updatedFaculty.setId(id);
        updatedFaculty.setName(newName);
        updatedFaculty.setColor(newColor);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
        when(facultyRepository.save(any(Faculty.class))).thenReturn(updatedFaculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObj.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(newName))
                .andExpect(jsonPath("$.color").value(newColor));
    }

    @Test
    public void deleteFacultyTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/faculty/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        verify(facultyRepository, atLeastOnce()).deleteById(id);
    }

    @Test
    public void getAllFacultiesTest() throws Exception {

        faculty.setId(id);
        faculty.setColor(color);
        faculty.setName(name);

        faculty2.setId(id2);
        faculty2.setColor(color2);
        faculty2.setName(name2);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty2);
        when(facultyRepository.findAll()).thenReturn(faculties);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test //не работает, и я не заню как исправить.
    public void getFacultyByColorOrNameTest() throws Exception {

        faculty.setId(id);
        faculty.setColor(color);
        faculty.setName(nameIgnoreCase);

        when(facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(nameIgnoreCase, color)).thenReturn(faculty);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/findBy/{colorOrName}")
                        .queryParam("color", color)
                        .queryParam("name", nameIgnoreCase)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(faculty)));
    }
}


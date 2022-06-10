package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;



    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
        //POST
    void testCreateNewStudent() throws Exception {
        Student student = new Student();
        student.setName("Test_Student_1");
        student.setAge(15);

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class))
                .isNotNull();
    }

    @Test
        //GET
    void testGetStudentById() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student" + "$.getId()", Student.class))
                .isNotNull();
    }

//    @Test //PUT
//    void TestEditStudent() throws Exception {
//        Student editStudent = new Student();
//        editStudent.setId(1L);
//        editStudent.setName("Test_Student_1");
//        editStudent.setAge(15);
//        Assertions
//                .assertThat(this.restTemplate.put("http://localhost:" + port + "/student", editStudent, Student.class))
//                .isNotNull();
//
//    }

    @Test //DELETE
    void TestDeleteStudent() throws Exception {
        Student student = new Student();
        deleteStudent(student);
        ResponseEntity<Student> emptyResponse = restTemplate.getForEntity("http://localhost:" + port
                + "/student/" + student.getId(), Student.class);
        Assertions.assertThat(emptyResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private void deleteStudent(Student student) {
        restTemplate.delete("http://localhost:" + port + "/student/" + student.getId());
    }

    @Test
        //GET
    void testGetAllStudents() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", Student.class))
                .isNotNull();
    }

    @Test
        //GET
    void testGetStudentByAge() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/ageFiltr/" + "$.getAge()", Student.class))
                .isNotNull();
    }

    @Test
        //GET
    void testGetStudentByAgeBetween() throws Exception {
        double max = Math.random() * 30;
        double min = Math.random() * 30;
              Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/ageBetween/" + min + max, String.class))
                .isNotNull();
}

    @Test
        //GET
    void testGetFacultyOfStudentByIdOfStudent() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/getFaculty/" + "$.getId()", Student.class))
                .isNotNull();
    }

    @Test
        //GET
    void testGetStudentByName() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/getStudentByName/" + "$.getName()", Student.class))
                .isNotNull();
    }
}

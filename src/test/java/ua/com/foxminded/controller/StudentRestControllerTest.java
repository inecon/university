package ua.com.foxminded.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.com.foxminded.domain.Group;
import ua.com.foxminded.domain.Student;
import ua.com.foxminded.repository.StudentDao;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentRestController.class)
public class StudentRestControllerTest {
    private static final Integer VALID_ID = 1;
    public static final Group VALID_GROUP1 = new Group(1, "Group01", "Spring math group");
    public static final Student VALID_STUDENT1 = new Student(1, "Petro", "Kolhozin", "male", 19, VALID_GROUP1);
    public static final Student VALID_STUDENT2 = new Student(7, "Vasya", "Pupkin", "male", 29, VALID_GROUP1);
    public List<Student> VALID_STUDENTS = new ArrayList<>();

    @Inject
    private MockMvc mockMvc;

    @MockBean
    private StudentDao studentDao;

    @Before
    public void init() {
        VALID_STUDENTS.add(VALID_STUDENT1);
        VALID_STUDENTS.add(VALID_STUDENT2);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getStudentById() throws Exception {
        when(studentDao.findById(VALID_STUDENT1.getId())).thenReturn(java.util.Optional.ofNullable(VALID_STUDENT1));

        this.mockMvc.perform(get("/api/students/1")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(VALID_STUDENT1.getName()));
    }

    @Test
    public void getStudentByIdFail() throws Exception {
        when(studentDao.findById(VALID_STUDENT1.getId())).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/api/students/1")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void saveStudent() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/students/")
                .content(asJsonString(VALID_STUDENT1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void saveStudentFail() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/students/22")
                .content(asJsonString(VALID_STUDENT1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateStudent() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/api/students/")
                .content(asJsonString(VALID_STUDENT1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Petro"));
    }

    @Test
    public void deleteStudent() throws Exception {
        when(studentDao.findById(VALID_ID)).thenReturn(java.util.Optional.ofNullable(VALID_STUDENT1));

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/students/{id}", VALID_ID))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getAllStudents() throws Exception {
        when(studentDao.findAll((Sort) any())).thenReturn(VALID_STUDENTS);
        this.mockMvc.perform(get("/api/students/")).andDo(print()).andExpect(status().isOk());

        this.mockMvc.perform(get("/api/students/")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());

    }
}
package ua.com.foxminded.exceptions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.com.foxminded.controller.StudentRestController;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ExceptionControllerAdvice.class)
public class ExceptionControllerAdviceTest {
    String content = "{"+
            "id"+":1,"+
            "name"+":"+ "Student 2,"+
            "surName"+":"+"Json,"+
            "gender"+":"+"Male,"+
            "age"+":"+"23,"+
            "group"+":{"+
            "id"+":1,"+
            "title"+":"+"group1"+
            "description"+":"+"Group1"+
            "}"+
            "}";

    @Mock
    StudentRestController studentRestController;


    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new ExceptionControllerAdvice(), studentRestController).build();
    }

    @Test
    public void handleMethodArgumentNotValid() throws Exception {
    }

    @Test
    public void handleBindException() {
    }

    @Test
    public void handleConstraintViolation() {
    }

    @Test
    public void handleMethodArgumentTypeMismatch() {
    }

    @Test
    public void handleNoHandlerFoundException() {
    }

    @Test
    public void handleHttpRequestMethodNotSupported() {
    }

    @Test
    public void handleHttpMessageNotReadable() {
        when(studentRestController.saveStudent(any())).thenThrow(new RuntimeException("Unexpected exception"));

       /*mockMvc.perform(post("/api/students/22").accept(TEXT_PLAIN))
                .andDo(print())
                .andExpect(status().isNotAcceptable());*/

    }
//406
    @Test
    public void handleHttpMediaTypeNotAcceptable() throws Exception {
        when(studentRestController.saveStudent(any())).thenThrow(new RuntimeException("HttpMediaTypeNotSupported"));

        mockMvc.perform(post("/api/students/").accept(TEXT_PLAIN))
                .andDo(print())
                .andExpect(status().isNotAcceptable());
    }
//500
    @Test
    public void handleAll() throws Exception {
        when(studentRestController.getStudent(anyInt())).thenThrow(new Exception("Unexpected exception"));

        mockMvc.perform(get("/api/students/1").accept(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void handleMissingPathVariable() {
    }
}
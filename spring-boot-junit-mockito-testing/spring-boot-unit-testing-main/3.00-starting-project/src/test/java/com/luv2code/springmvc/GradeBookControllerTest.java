package com.luv2code.springmvc;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.models.GradebookCollegeStudent;
import com.luv2code.springmvc.repository.StudentDao;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class GradeBookControllerTest {
    @Autowired
    private JdbcTemplate jdbc;
    @Mock
    private MockMvc mockMvc;
    @Autowired
    private StudentAndGradeService studentCreateServiceMock;
    @Autowired
    private StudentDao studentDao;

    private static MockHttpServletRequest request;

    public GradeBookControllerTest() {
    }

//    @BeforeAll
//    public static void setUp(){
//        request = new MockHttpServletRequest();
//        request.setParameter("firstname","Chad");
//        request.setParameter("lastname","darby");
//        request.setParameter("emailAddress","chad@luv2code.com");
//    }

    @BeforeEach
    public void beforeEach() {
        jdbc.execute("insert into student (id,firstname,lastname,email_address) values(1,'ram','Bhand','ram@luv2code.com')");
    }

    @Test
    public void getStudentHttpRequests()throws Exception {
        CollegeStudent studentOne = new GradebookCollegeStudent("Ram", "Bhand", "ram@luv2code.com");
        CollegeStudent studentTwo = new GradebookCollegeStudent("Sham", "Bhand", "sham@luv2code.com");
        ArrayList<CollegeStudent> collegeStudentList = new ArrayList<>(Arrays.asList(studentOne, studentTwo));
         when(studentCreateServiceMock.getGradeBook()).thenReturn(collegeStudentList);
        assertIterableEquals(collegeStudentList,studentCreateServiceMock.getGradeBook());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk()).andReturn();
        ModelAndView mav = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(mav,"index");
    }

    @Test
    public void createStudentHttpRequest() throws Exception{
        CollegeStudent studentOne = new GradebookCollegeStudent("Onkar","Aher","onkar@luv2code.com");
        ArrayList<CollegeStudent>collegeStudentList = new ArrayList<>(Arrays.asList(studentOne));
        when(studentCreateServiceMock.getGradeBook()).thenReturn(collegeStudentList);
        assertIterableEquals(collegeStudentList,studentCreateServiceMock.getGradeBook());

        MvcResult mvcResult =  mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("firstname",request.getParameter("firstname"))
                .queryParam("lastname",request.getParameter("lastname"))
                .queryParam("emailAddress",request.getParameter("emailAddress")))
                .andExpect(status().isOk()).andReturn();
        ModelAndView mav = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(mav,"index");
        CollegeStudent verifyStudent = studentDao.findByEmailAddress("chad@luv2code.com");
        assertNotNull(verifyStudent,"Student should be found");
    }
    @Test
    public void deleteStudentHttpRequest() throws Exception {
        Optional<CollegeStudent> collegeStudent = studentDao.findById(1);
        assertTrue(collegeStudent.isPresent());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/delete/student/{id}",1))
                .andExpect(status().isOk()).andReturn();
        ModelAndView mav = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(mav,"index");
        assertFalse(studentDao.findById(1).isPresent());
    }

    @Test
    void deleteStudentHttpRequestErrorPage() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/delete/student/{id}",0))
                .andExpect(status().isOk()).andReturn();
        ModelAndView mav = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(mav,"error");
    }
    @AfterEach
    public void AfterEach() {
        jdbc.execute("DELETE FROM student");
    }

}

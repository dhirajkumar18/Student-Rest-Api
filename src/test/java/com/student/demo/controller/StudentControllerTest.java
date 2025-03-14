package com.student.demo.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.demo.entity.Student;
import com.student.demo.service.StudentService;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;
    

    @Test
    void testGetAllStudents() throws Exception {
        List<Student> students = Arrays.asList(
                new Student(1L, "dhiraj kumar", "9899195519", "dhiraj@gmail.com", "noida"),
                new Student(2L, "shan", "9840142282", "eakshan@gmail.com", "Bangalore"),
                new Student(3L,"ramesh","78388389389","ramesh@gmail.com","delhi")
        );

        when(studentService.getAllStudent()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/allStudent"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("dhiraj kumar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].contactNumber").value("9899195519"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("dhiraj@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].location").value("noida"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("shan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].contactNumber").value("9840142282"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("eakshan@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].location").value("Bangalore"));
    }
    
    
    @Test
    void createStudent() throws Exception {
    	Student student1=new Student(1L, "dhiraj kumar", "9899195519", "dhiraj@gmail.com", "noida");
    //	Student student2=new Student(2L, "shan", "9840142282", "eakshan@gmail.com", "Bangalore");
    	
        when(studentService.createStudent(any(Student.class))).thenReturn(student1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/saveStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(student1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("dhiraj kumar"))
                .andExpect(jsonPath("$.contactNumber").value("9899195519"))
                .andExpect(jsonPath("$.email").value("dhiraj@gmail.com"))
                .andExpect(jsonPath("$.location").value("noida"));
    }
    
    
}

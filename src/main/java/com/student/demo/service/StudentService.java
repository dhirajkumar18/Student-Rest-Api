package com.student.demo.service;

import java.util.List;

import com.student.demo.entity.Student;

public interface StudentService {
	
	public Student createStudent(Student student);
	public List<Student> getAllStudent();

}

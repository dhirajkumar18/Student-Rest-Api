package com.student.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.demo.entity.Student;
import com.student.demo.repository.StudentRepo;
import com.student.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentRepo studentRepo;

	@Override
	public Student createStudent(Student student) {
		
		return studentRepo.save(student);
	}

	
	public List<Student> getAllStudent() {
		
		return studentRepo.findAll();
	}

	

}

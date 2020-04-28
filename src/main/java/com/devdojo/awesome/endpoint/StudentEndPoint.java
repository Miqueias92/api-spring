package com.devdojo.awesome.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devdojo.awesome.util.DateUtil;
import com.devdojo.awesome.error.CustomErrorType;
import com.devdojo.awesome.model.Student;

import java.util.List;
import static java.util.Arrays.asList;

import java.time.LocalDateTime;

@RestController
@RequestMapping("students")
public class StudentEndPoint {

	//@Autowired essa forma ou usando o construtor
	private final DateUtil dateutil;
	
	@Autowired
	public StudentEndPoint(DateUtil dateutil) {
		this.dateutil = dateutil;
	}
	
	//@RequestMapping(method = RequestMethod.GET, path = "/list")
	//@RequestMapping(method = RequestMethod.GET)
	@GetMapping
	public ResponseEntity<?> ListAll() {
		//System.out.println(dateutil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
		//return asList(new Student("Deku"), new Student("Todoroki"));
		return new ResponseEntity<>(Student.studentList, HttpStatus.OK);
	}
	
	//@RequestMapping(method = RequestMethod.GET, path = "{id}")
	@GetMapping(path = "{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") int id){
//		Student student = Student.studentList
//				                 .stream()
//				                 .filter(m -> m.getId() == id)
//				                 .findAny()
//				                 .orElse(null);
		
		Student student = new Student();
		student.setId(id);
		
		int index = Student.studentList.indexOf(student);
		if (index == -1)
			return new ResponseEntity<>(new CustomErrorType("student not found"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(student.studentList.get(index), HttpStatus.OK);
	}
	
	//@RequestMapping(method = RequestMethod.POST)
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Student student){
		Student.studentList.add(student);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	//@RequestMapping(method = RequestMethod.DELETE)
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody Student student){
		Student.studentList.remove(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//@RequestMapping(method = RequestMethod.PUT)
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Student student){
		Student.studentList.remove(student);
		Student.studentList.add(student);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
}

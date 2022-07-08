package com.student.student.controller;

import com.student.student.error.StudentNotFoundException;
import com.student.student.model.Student;
import com.student.student.servicelayer.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/student")
public class StudentController {
    private final StudentService studentService;
    private final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public List<Student> getStudents() throws StudentNotFoundException {
        return studentService.getStudent();
    }
    @GetMapping(path="{studentId}")
    public Optional<Student> getTheEmployeeById(@PathVariable("studentId") Long studentId) throws StudentNotFoundException {
        LOGGER.info("Inside get request in student controller");
        return studentService.getStudentById(studentId);
    }
    @GetMapping("/name/{studentName}")
    public Optional<Student> getTheStudentByName(@PathVariable("studentName") String studentName) throws StudentNotFoundException {
        return studentService.getStudentByName(studentName);
    }
    @GetMapping("/matricnumber/{matricNumber}")
    public Optional<Student> getTheStudentByMatricNumber(@PathVariable("matricNumber") String matricNumber) throws StudentNotFoundException {
        return studentService.getStudentByMatricNumber(matricNumber);
    }
    @PostMapping
    public String registerNewStudent(@Valid @RequestBody Student student){
        LOGGER.info("Inside post request in student controller");
        studentService.addNewStudent(student);
        return "New Student added successfully";
    }
    @DeleteMapping(path = "{studentId}")
    public String deleteStudent(@PathVariable("studentId") Long studentId) throws StudentNotFoundException {
        studentService.deleteStudent(studentId);
        return "student with ID "+studentId+" deleted successfully";
    }
    @PutMapping (path ="{studentId}")
    public String updateEmployee(@PathVariable("studentId") Long studentId, @RequestBody Student student
    ) throws StudentNotFoundException {
       studentService.updateStudent(studentId, student);
        return "student record with ID "+studentId+" updated successfully";
    }
}

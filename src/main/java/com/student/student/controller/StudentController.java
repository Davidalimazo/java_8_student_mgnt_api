package com.student.student.controller;

import com.student.student.model.Student;
import com.student.student.servicelayer.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/student")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudent();
    }
    @GetMapping(path="{studentId}")
    public Optional<Student> getTheEmployeeById(@PathVariable("studentId") Long studentId){
        return studentService.getStudentById(studentId);
    }
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }
    @PutMapping (path ="{studentId}")
    public void updateEmployee(@PathVariable("studentId") Long studentId,
                               @RequestParam (required = false) String firstName,
                               @RequestParam (required = false) String lastName,
                               @RequestParam (required = false) String email,
                               @RequestParam (required = false) int level
    ){
       studentService.updateStudent(studentId, firstName, lastName, email, level);
    }
}

package com.student.student.servicelayer;

import com.student.student.model.Student;
import com.student.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String generatetMatricNumber() {
        return "ETZ" + (int) Math.floor(Math.random() * 1000000);
    }

    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id){
        return studentRepository.findById(id);
    }

    public void addNewStudent(Student student){

        try {
            student.setMatricNumber(generatetMatricNumber());
            studentRepository.save(student);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteStudent(Long studentId){
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw  new IllegalStateException("No student record for "+studentId);
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentId, String firstName, String lastName, String email, int level){
        Student student = studentRepository.findById(studentId).orElseThrow(()->new IllegalStateException("No employee record for: "+ studentId));

        student.setFirstName(firstName);
        student.setEmail(email);
        student.setLastName(lastName);
        student.setLevel(level);
    }

}

package com.student.student.servicelayer;

import com.student.student.error.StudentNotFoundException;
import com.student.student.model.Student;
import com.student.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public List<Student> getStudent() throws StudentNotFoundException {
        List<Student> allStudent = studentRepository.findAll();
        if(allStudent.size() > 0){
            return allStudent;
        }else throw new StudentNotFoundException("No Student record for now");
    }

    public Optional<Student> getStudentById(Long id) throws StudentNotFoundException {
        Optional<Student> singleStudent = studentRepository.findById(id);
        if(singleStudent.isPresent()){
            return singleStudent;
        }else throw new StudentNotFoundException("No student record for: "+ id);
    }

    public void addNewStudent(Student student){

        try {
            student.setMatricNumber(generatetMatricNumber());
            studentRepository.save(student);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteStudent(Long studentId) throws StudentNotFoundException {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw  new StudentNotFoundException("No student record for: "+ studentId);
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentId, Student student) throws StudentNotFoundException {
        Student studentFromDB = studentRepository.findById(studentId).orElseThrow(()->new StudentNotFoundException("No student record for: "+ studentId));
            if(Objects.nonNull(student.getFirstName()) && !"".equalsIgnoreCase(student.getFirstName())){
                studentFromDB.setFirstName(student.getFirstName());
            }
            if(Objects.nonNull(student.getLastName()) && !"".equalsIgnoreCase(student.getLastName())){
                studentFromDB.setLastName(student.getLastName());
            }
        if(Objects.nonNull(student.getLevel()) && student.getLevel() > 0){
            studentFromDB.setLevel(student.getLevel());
        }
    }
    public Optional<Student> getStudentByName(String firstName) throws StudentNotFoundException {

        Optional<Student> singleStudent = Optional.ofNullable(studentRepository.findByFirstName(firstName));
        if(singleStudent.isPresent()){
            return singleStudent;
        }else throw new StudentNotFoundException("No student record for: "+ firstName);
    }
    public Optional<Student> getStudentByMatricNumber(String matricNumber) throws StudentNotFoundException {

        Optional<Student> singleStudent = Optional.ofNullable(studentRepository.findByMatricNumber(matricNumber));
        if(singleStudent.isPresent()){
            return singleStudent;
        }else throw new StudentNotFoundException("No student record for: "+ matricNumber);
    }

}

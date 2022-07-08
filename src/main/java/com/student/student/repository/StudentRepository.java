package com.student.student.repository;

import com.student.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public Student findByFirstName(String firstName);
    public Student findByFirstNameIgnoreCase(String firstName);
    public Student findByMatricNumber(String matricNumber);
    public Student findByMatricNumberIgnoreCase(String matricNumber);

}

package com.student.student.repository;

import com.student.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByMatricNumber(int matricNumber);
    Optional<Student> findStudentByEmail(String email);
}

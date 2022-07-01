package com.student.student.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email", "matricNumber"}))
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String matricNumber;
    private Double CGPA;
    private int level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricNumber() {
        return matricNumber;
    }

    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
    }

    public Double getCGPA() {
        return CGPA;
    }

    public void setCGPA(Double CGPA) {
        this.CGPA = CGPA;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", matricNumber='" + matricNumber + '\'' + ", CGPA=" + CGPA + ", level=" + level + '}';
    }
}

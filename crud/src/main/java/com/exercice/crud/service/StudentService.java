package com.exercice.crud.service;

import java.util.*;

import com.exercice.crud.model.Student;
import com.exercice.crud.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public List<Student> getAllStudents() {
        ArrayList<Student> studentList = new ArrayList<>();
        studentRepo.findAll().forEach(std -> studentList.add(std));

        return studentList;
    }

    public Student getStudentById(Long id) {
        return studentRepo.findById(id).get();
    }

    public boolean saveOrUpdateStudent(Student std) {
        Student obj = studentRepo.save(std);
        if(getStudentById(obj.getId()) != null) {
            return true;
        }
        return false;

    }

    public boolean deleteStudent(Long id) {
        try{
            studentRepo.deleteById(id);
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }
}


package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Iterable<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(Integer studentId)
    {
        Optional<Student> studentById = studentRepository.findById( studentId );
        if ( !studentById.isPresent() ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Student with the id "+ studentId +" does not exist.");
        }
        return studentById.get();
    }

    public void addNewStudent(Student student)
    {
        /*
        Optional<Student> studentByEmail = studentRepository
                .findStudentByEmail( student.getEmail() );
        if ( studentByEmail.isPresent() ) {
            throw new IllegalStateException("Email taken");
        }
         */
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Integer studentId)
    {
        boolean exists = studentRepository.existsById( studentId );
        if ( ! exists ) {
            throw new IllegalStateException(
                    "student with id "+ studentId +" does not exist."
            );
        }
        studentRepository.deleteById( studentId );
    }

    //@Transactional
    public void updateStudent(Integer studentId, String name, String email)
    {
        Student student = studentRepository.findById( studentId )
                .orElseThrow( () -> new IllegalStateException("student with id "+ studentId +" does not exist.") );

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository
                    .findStudentByEmail( email );
            if ( studentOptional.isPresent() )
                throw new IllegalStateException("email taken");
            student.setEmail( email );
        }
    }


}

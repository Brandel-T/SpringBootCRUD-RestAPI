package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
public interface StudentRepository
        extends JpaRepository<Student, Integer> {

    /*
    cette requette sql va nous permettre au travers de la methode
    findStudentByEmail d'obetenir une adresse e-mail dans la base
    de donnée en filtrant avec une email passée en parametre qui
    remplacera ?1 de la requete sql de l'annotation QUERY
     */
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    /*
    @Query(
            value = "SELECT s FROM student s WHERE s.email = ?1",
            nativeQuery = true
    )
     */
    Optional<Student> findStudentByEmail(String email);
}
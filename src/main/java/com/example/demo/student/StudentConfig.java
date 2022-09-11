package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig
{
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student mariam = new Student(
                    1,
                    "mariam",
                    "mariam@mail.com",
                    LocalDate.of(2001, Month.JANUARY, 4),
                    34
            );
            Student alex = new Student(
                    "alex",
                    "alex@mail.com",
                    LocalDate.of(2004, Month.MARCH, 6),
                    14
            );

            repository.saveAll(List.of(mariam, alex));
        };
    }
}

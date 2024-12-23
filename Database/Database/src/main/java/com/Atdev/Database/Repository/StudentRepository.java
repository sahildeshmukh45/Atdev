package com.Atdev.Database.Repository;
import com.Atdev.Database.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
package com.Atdev.fileSystem.Controller;
import com.Atdev.fileSystem.Model.Student;
import com.Atdev.fileSystem.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StorageService fileStorageService;

    @GetMapping
    public List<Student> getAllStudents() {
        return fileStorageService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = fileStorageService.getStudentById(id);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> createStudent(@RequestBody Student student) {
        fileStorageService.createStudent(student);
        return ResponseEntity.created(URI.create("/api/students/" + student.getId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);
        fileStorageService.updateStudent(id, student);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        fileStorageService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
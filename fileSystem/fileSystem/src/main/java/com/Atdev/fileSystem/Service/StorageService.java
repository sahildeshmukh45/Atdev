package com.Atdev.fileSystem.Service;
import com.Atdev.fileSystem.Model.Student;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorageService {
    private final String DIRECTORY = "students/";

    public StorageService() {
        try {
            Files.createDirectories(Paths.get(DIRECTORY));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        File folder = new File(DIRECTORY);
        for (File file : folder.listFiles()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    Student student = new Student();
                    student.setId(Long.parseLong(data[0]));
                    student.setName(data[1]);
                    student.setEmail(data[2]);
                    student.setCourse(data[3]);
                    student.setAge(Integer.parseInt(data[4]));
                    students.add(student);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return students;
    }

    public Student getStudentById(Long id) {
        File file = new File(DIRECTORY + id + ".txt");
        if (!file.exists()) {
            return null;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            String[] data = line.split(",");
            Student student = new Student();
            student.setId(Long.parseLong(data[0]));
            student.setName(data[1]);
            student.setEmail(data[2]);
            student.setCourse(data[3]);
            student.setAge(Integer.parseInt(data[4]));
            return student;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createStudent(Student student) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DIRECTORY + student.getId() + ".txt"))) {
            writer.write(student.getId() + "," + student.getName() + "," + student.getEmail() + "," + student.getCourse() + "," + student.getAge());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Long id, Student student) {
        createStudent(student); // Overwrite the existing file
    }

    public void deleteStudent(Long id) {
        File file = new File(DIRECTORY + id + ".txt");
        if (file.exists()) {
            file.delete();
        }
    }
}
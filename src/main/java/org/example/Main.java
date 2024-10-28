package org.example;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentDao dao = new StudentDao();
        Scanner scanner = new Scanner(System.in);
        boolean check=true;
        while (check) {
            System.out.println("\nChoose an operation:");
            System.out.println("1: Add a new student");
            System.out.println("2: Retrieve all students");
            System.out.println("3: Retrieve a student by ID");
            System.out.println("4: Update a student");
            System.out.println("5: Delete a student");
            System.out.println("6: Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:{
                    System.out.print("Enter student name: ");
                    String name = scanner.next();
                    System.out.print("Enter student age (18-25): ");
                    int age = scanner.nextInt();
                    System.out.print("Enter student grade (0.0 - 4.0): ");
                    double grade = scanner.nextDouble();
                    System.out.print("Enter student course: ");
                    String course = scanner.next();

                    Student newStudent = new Student();
                    newStudent.setName(name);
                    newStudent.setAge(age);
                    newStudent.setGrade(grade);
                    newStudent.setCourse(course);
                    dao.addStudent(newStudent);
                    System.out.println("Student added successfully.");
                    break;
                }
                case 2:{
                    List<Student> students = dao.getAllStudents();
                    System.out.println("All students:");
                    students.forEach(System.out::println);
                    break;
                }
                case 3:{
                    System.out.print("Enter student ID to retrieve: ");
                    int id = scanner.nextInt();
                    Optional<Student> studentOpt = dao.getStudentById(id);
                    studentOpt.ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Student not found with ID: " + id)
                    );
                    break;
                }
                case 4:{
                    System.out.print("Enter student ID to update: ");
                    int id = scanner.nextInt();
                    Optional<Student> studentOpt = dao.getStudentById(id);
                    if (studentOpt.isPresent()) {
                        System.out.print("Enter new name: ");
                        String name = scanner.next();
                        System.out.print("Enter new age (18-25): ");
                        int age = scanner.nextInt();
                        System.out.print("Enter new grade (0.0 - 4.0): ");
                        double grade = scanner.nextDouble();
                        System.out.print("Enter new course: ");
                        String course = scanner.next();

                        Student updatedStudent = studentOpt.get();
                        updatedStudent.setName(name);
                        updatedStudent.setAge(age);
                        updatedStudent.setGrade(grade);
                        updatedStudent.setCourse(course);
                        dao.updateStudent(id, updatedStudent);
                        System.out.println("Student updated successfully.");
                    } else {
                        System.out.println("Student not found with ID: " + id);
                    }
                    break;
                }
                case 5:{
                    System.out.print("Enter student ID to delete: ");
                    int id = scanner.nextInt();
                    dao.deleteStudent(id);
                    System.out.println("Student deleted successfully.");
                    break;
                }
                case 6:{
                    System.out.println("Exiting...");
                    check=false;
                    scanner.close();
                    break;
                }
                default: {
                    System.out.println("Invalid choice, please try again.");
                    break;
                }
            }
        }
    }
}

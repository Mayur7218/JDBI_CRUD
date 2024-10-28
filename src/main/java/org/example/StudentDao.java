package org.example;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.Handle;
import java.util.List;
import java.util.Optional;

public class StudentDao {
    private final Jdbi jdbi;

    public StudentDao() {
        this.jdbi = DatabaseConfig.getJdbiInstance();
    }

    public void addStudent(Student student) {
        String sql = "INSERT INTO students (name, age, grade, course) VALUES (:name, :age, :grade, :course)";
        try (Handle handle = jdbi.open()) {
            handle.createUpdate(sql)
                    .bind("name", student.getName())
                    .bind("age", student.getAge())
                    .bind("grade", student.getGrade())
                    .bind("course", student.getCourse())
                    .execute();
        } catch (Exception e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM students";
        try (Handle handle = jdbi.open()) {
            return handle.createQuery(sql).mapToBean(Student.class).list();
        } catch (Exception e) {
            System.err.println("Error retrieving students: " + e.getMessage());
            return List.of();
        }
    }

    public Optional<Student> getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id = :id";
        try (Handle handle = jdbi.open()) {
            return handle.createQuery(sql)
                    .bind("id", id)
                    .mapToBean(Student.class)
                    .findOne();
        } catch (Exception e) {
            System.err.println("Error retrieving student with ID " + id + ": " + e.getMessage());
            return Optional.empty();
        }
    }

    public void updateStudent(int id, Student student) {
        String sql = "UPDATE students SET name = :name, age = :age, grade = :grade, course = :course WHERE id = :id";
        try (Handle handle = jdbi.open()) {
            handle.createUpdate(sql)
                    .bind("id", id)
                    .bind("name", student.getName())
                    .bind("age", student.getAge())
                    .bind("grade", student.getGrade())
                    .bind("course", student.getCourse())
                    .execute();
        } catch (Exception e) {
            System.err.println("Error updating student with ID " + id + ": " + e.getMessage());
        }
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = :id";
        try (Handle handle = jdbi.open()) {
            handle.createUpdate(sql)
                    .bind("id", id)
                    .execute();
        } catch (Exception e) {
            System.err.println("Error deleting student with ID " + id + ": " + e.getMessage());
        }
    }
}

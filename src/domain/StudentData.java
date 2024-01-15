package domain;

import java.util.ArrayList;
import java.util.List;

public class StudentData { //싱글톤 패턴

    private static StudentData studentData;
    private static List<Student> students;

    private StudentData() {
        students = new ArrayList<>();
    }

    public static StudentData getInstance() {
        if(studentData == null)
            studentData = new StudentData();

        return studentData;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public static List<Student> getStudents() {
        return students;
    }

}
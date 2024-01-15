package domain;

import java.util.ArrayList;
import java.util.List;

public class StudentData {

    private static StudentData studentData;
    private final List<Student> students;

    private StudentData() {
        students = new ArrayList<>();
    }

    public static StudentData getInstance() {
        if(studentData == null)
            studentData = new StudentData();

        return studentData;
    }

}
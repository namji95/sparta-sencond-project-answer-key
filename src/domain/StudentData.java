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

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }
    
    public Student getStudentInfo(long accountId) {
        for(Student student : students) {
            if(student.getAccountId() == accountId)
                return student;
        }
        
        // 그 전에 미리 예외처리를 해서 여기까지는 안 올거임
        throw new IllegalArgumentException("[ERROR] 해당 학생 정보가 없습니다.");
    }

}

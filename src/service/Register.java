package service;

import domain.StudentCreateRead;
import domain.StudentData;

// 등록하는 클래스
public class Register {
    private final StudentData studentData = StudentData.getInstance();
    private final StudentCreateRead studentCreateRead = new StudentCreateRead();

    public void registerStudent() {
        studentCreateRead.StudentCreate(studentData);
    }
}

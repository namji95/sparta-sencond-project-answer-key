package service;

import domain.StudentCreateRead;
import domain.StudentData;

// 정보 조회하는 클래스
public class Information {

    private final StudentData studentData = StudentData.getInstance();
    private final StudentCreateRead studentCreateRead = new StudentCreateRead();

    public void lookUpInformation() {
        studentCreateRead.studentRead(studentData);
    }
}

package domain;

import java.util.ArrayList;
import java.util.List;

public class StudentData {

    private static StudentData studentData;
    //list 저장소 역할 - > 확장성
    //singletonpatton
    private final List<Student> students;

    private StudentData() {
        students = new ArrayList<>();
    }

    public static StudentData getInstance() {
        if(studentData == null)
            studentData = new StudentData();

        return studentData;
    }


    // 수정한 부분

    public void inquireStudent(long seq) {
        System.out.println("\n수강생 목록을 조회합니다.....");
        //조회할 학생정보
        Student foundStudent = null;
        //조회한 학생 ID와 일치하는지 비교하고 일치하는 student 인스턴스를 foundstudent에 할당
        for (Student student : students) {
            if (student.getAccountId() == seq) {
                foundStudent = student;

            }
            //foundStudent에 값이 있을 경우 학생데이터를 출력, 없을 경우 false
            if (foundStudent != null) {
                //점수에 따라 상태가 달라짐
                //Student 클래스 안에 들어감, enum 클래스로 만들기
                //String studentStatus = student.getStatus;
                //String studentStatus = (String)student.status;
                System.out.println("\n수강생 목록 조회 성공!");
                System.out.println("학생 Id  : " + student.getAccountId());
                System.out.println("학생 이름 : " + student.getName());
                System.out.print("수강 과정 : " );
                for (Course course : student.getMyCourse()) {
                    System.out.print(course.getCourseName() + " ");
                }
                System.out.println();
                System.out.println("학생 상태 : ");
            } else {
                System.out.println("조회한 번호의 수강생이 없습니다.");
            }
        }
    }
    public void  deleteStudent(long seq) {
        Student foundStudent = null;

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getAccountId() == seq) {
                foundStudent = student;
                break;  // 학생을 찾으면 반복문 종료
            }
        }

        if (foundStudent != null) {
            students.remove(foundStudent);
            System.out.println("학생 정보가 삭제되었습니다.");
        } else {
            System.out.println("학생 정보가 없습니다. 다시 입력해주세요.");
        }
    }

}




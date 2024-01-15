package service;

import domain.Course;
import domain.CourseType;
import domain.Student;
import domain.StudentData;
import invalidate.Invalidate;
import view.Input;

import java.util.List;

// 수정하는 클래스
public class Modify {

    private final StudentData studentData = StudentData.getInstance();

    public void modify() {
        while(true) {
            System.out.println("어떤 학생의 정보를 수정하시겠습니까? (0. 나가기)");
            printStudentList();
            String number = Input.inputStudentNumber();

            if(number.equals("0"))
                break;

            if(!Invalidate.invalidateNumber(number) || Invalidate.invalidateStudent(number))
                continue;

            modifyStudentScore(studentData.getStudentInfo(Long.parseLong(number)));    // 학생의 점수를 등록 및 설정
        }
    }

    private void modifyStudentScore(Student student) {
        while(true) {
            System.out.println("특정 과목의 점수를 입력하시겠습니까? (0. 나가기");
            printCourseList(student.getTypeCourseList(CourseType.MANDATORY),CourseType.MANDATORY);
            printCourseList(student.getTypeCourseList(CourseType.OPTIONAL),CourseType.OPTIONAL);
            String idNumber = Input.inputCourseNumber();

            if(idNumber.equals("0"))
                break;

            if(!Invalidate.invalidateNumber(idNumber)) {

            }

        }
    }

    private void printCourseList(List<Course> courses, CourseType type) {
        if(type == CourseType.MANDATORY)
            System.out.println("== 필수 과정 리스트 ==");
        else if(type == CourseType.OPTIONAL)
            System.out.println("== 선택 과정 리스트 ==");

        String format = "%3d | %s\n";
        for(Course course : courses)
            System.out.printf(format, course.getIdNumber(), course.getCourseName());

    }

    private void printStudentList() {
        List<Student> students = studentData.getStudents();
        String format = "%-3d | %s\n";

        for(Student student : students)
            System.out.printf(format,student.getAccountId(),student.getName());

        System.out.println();
    }
}

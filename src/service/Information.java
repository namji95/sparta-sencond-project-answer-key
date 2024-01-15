package service;

import console.Console;
import domain.CourseType;
import domain.Student;
import domain.Course;
import domain.StudentData;
import invalidate.Invalidate;

import java.util.List;

// 정보 조회하는 클래스
public class Information {

    public void lookUpInformation(StudentData students) {
        Student student = insertStudentAccountId(students);      // 해당 수강생 정보가 존재한다.

        System.out.println("\n수강생 목록 조회 성공!");
        System.out.println("학생 Id  : " + student.getAccountId());
        System.out.println("학생 이름 : " + student.getName());
        System.out.println("학생 상태 : " + student.getStatus());

        printCourseList(student.getTypeCourseList(CourseType.MANDATORY), CourseType.MANDATORY);
        printCourseList(student.getTypeCourseList(CourseType.OPTIONAL), CourseType.OPTIONAL);
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

    private Student insertStudentAccountId(StudentData students) {
        while(true) {
            System.out.print("\n조회할 수강생 번호를 입력하세요: ");
            String number = Console.inputString();
            System.out.println("\n수강생 목록을 조회합니다.....");

            if(!Invalidate.invalidateNumber(number) || Invalidate.invalidateStudent(number))
                continue;

            return students.getStudentInfo(Long.parseLong(number));
        }
    }
}

package service;

import console.Console;
import domain.CourseType;
import domain.StudentData;
import domain.Student;
import domain.Course;

import java.util.ArrayList;
import java.util.List;

import static domain.StudentData.getInstance;

// 정보 조회하는 클래스
public class Information {

    public static void lookUpInformation(List<Student> students) {
        System.out.println("\n 조회할 수강생 번호를 입력하세요");
        long accountId = Console.inputInt();
        System.out.println("\n수강생 목록을 조회합니다.....");
        Student foundStudent = null;

        for (Student student : students) {
            if (student.getAccountId() == accountId) {
                foundStudent = student;
                break;
            }
        }
        if (foundStudent != null) {
            System.out.println("\n수강생 목록 조회 성공!");
            System.out.println("학생 Id  : " + foundStudent.getAccountId());
            System.out.println("학생 이름 : " + foundStudent.getName());
            System.out.print("수강 과정 : " );
            for (Course course : foundStudent.getMyCourse()) {
                System.out.print(course.getCourseName() + " ");
            }
            //split() : ,별로 자르기 마지막 인덱스에 있는 1없애기
            //string str = " "; 맨마지막 인덱스만 자르기...
            //string.length
            //substring
            System.out.println();
            System.out.println("학생 상태 : " + foundStudent.getStatus());
        } else {
            System.out.println("조회한 번호의 수강생이 없습니다.");
        }
    }
}

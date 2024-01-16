package service;

import console.Console;
import domain.course.Course;
import domain.course.CourseList;
import domain.course.CourseType;
import domain.student.Student;
import domain.student.StudentData;
import invalidate.Invalidate;

import java.util.ArrayList;
import java.util.List;

// 등록하는 클래스
public class Register {
    private final StudentData studentData = StudentData.getInstance();

    public void registerStudent() {
        StudentCreate(studentData);
    }

    private void StudentCreate (StudentData studentData) {
        System.out.print("학생 이름을 입력하세요: ");
        String studentName = Console.inputString();

        if (studentName.equals("end"))
            return;

        long countId = countIdCreate();                                             // 고유 번호를 받고
        List<Course> selectCourses = selectCourse();                                // 과목 목록을 받고
        studentData.addStudent(new Student(countId, studentName, selectCourses));   // 학생 데이터에 저장
        System.out.println("학생이 성공적으로 생성되었습니다!\n");
    }
    private void printStudentInformation(Student student) {
        while(true) {
            studentInformation(student);
            System.out.print("이전 화면으로 넘어가고 싶으시면 0번을 입력하세요: ");
            String number = Console.inputString();

            if(!Invalidate.invalidateNumber(number))
                continue;

            if(number.equals("0"))
                break;
        }
    }

    private void studentInformation(Student student) {
        System.out.println("고유 번호: " + student.getAccountId());
        System.out.println("학생 이름: " + student.getName());
        System.out.println("학생 상태: " + student.getStatus());

        printTypeCourseList(student.getTypeCourseList(CourseType.MANDATORY),CourseType.MANDATORY);
        printTypeCourseList(student.getTypeCourseList(CourseType.OPTIONAL),CourseType.OPTIONAL);
    }

    private void printStudentList(StudentData studentData) {
        System.out.println("== 등록된 수강생 정보들 ==");

        for(Student student : studentData.getStudents()) {
            System.out.print("수강생 고유번호: " + student.getAccountId());
            System.out.println("수강생 이름: " + student.getName());
            printTypeCourseList(student.getTypeCourseList(CourseType.MANDATORY),CourseType.MANDATORY);
            printTypeCourseList(student.getTypeCourseList(CourseType.OPTIONAL),CourseType.OPTIONAL);
        }
    }

    private void printTypeCourseList(List<Course> courses, CourseType type) {
        if(type == CourseType.MANDATORY)
            System.out.println("===== 필수 과목 =====");
        else if(type == CourseType.OPTIONAL)
            System.out.println("===== 선택 과묙 =====");

        System.out.println("Number | CourseName");

        for(Course course : courses)
            System.out.printf("%-6d | %s\n",course.getIdNumber(), course.getCourseName());

        System.out.println();
    }

    private List<Course> selectCourse() {
        printCourseList();
        return saveCourseList();
    }

    // 과목의 목록을 조회하는 메서드
    private void printCourseList() {
        System.out.printf("%-6s | %-20s | %s\n","Number","CourseName","CourseType");

        System.out.println("===== [선택 가능 과목 목록] =====");
        for (CourseList selectCourseList : CourseList.values()) {
            String format = "%-6d | %-20s | %s\n";
            System.out.printf(format, selectCourseList.getIdNumber(),
                    selectCourseList.getCourseName(), selectCourseList.changeCourseType());
        }
    }

    // 과목들에 대한 정보를 저장해줌
    private List<Course> saveCourseList() {
        List<Course> courseList = new ArrayList<>();

        while (true) {
            System.out.println("수강할 과목 고유번호를 선택하세요.");
            System.out.println("쉼표를 붙여서 필수과목 3, 선택과목 2개 이상 고르시오( 예시) 1,2,3  ,종료는 'exit')");
            String studentInput = Console.inputString();

            // 과목을 선택할 때, 필수과목 3개 이상, 선택과목 2개 이상 골라야합니다.
            if(!Invalidate.invalidateCourse(studentInput)) continue;

            String[] numbers = studentInput.split(",");

            // 과목들을 저장
            for(String number : numbers)
                courseList.add(CourseList.getCourseList(CourseList.getChangeName(Long.parseLong(number))));

            return courseList;
        }
    }

    private long countIdCreate() {
        while(true) {
            System.out.print("등록할 수강생의 고유 번호를 입력하세요: ");
            String number = Console.inputString();

            if(!Invalidate.invalidateNumber(number) || Invalidate.invalidateExistStudent(number))
                continue;

            return Long.parseLong(number);
        }
    }
    
}

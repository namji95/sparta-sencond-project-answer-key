package domain;

import console.Console;
import invalidate.Invalidate;

import java.util.ArrayList;
import java.util.List;

public class StudentCreateRead {

    // 학생에 대한 데이터 생성
    public void StudentCreate (StudentData studentData) {
        System.out.print("학생 이름을 입력하세요: ");
        String studentName = Console.inputString();

        if (studentName.equals("end"))
            return;

        long countId = countIdCreate();                                             // 고유 번호를 받고
        List<Course> selectCourses = selectCourse();                                // 과목 목록을 받고
        studentData.addStudent(new Student(countId, studentName, selectCourses));   // 학생 데이터에 저장
        System.out.println("학생이 성공적으로 생성되었습니다!\n");
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

            for(String number : numbers)
                saveCourses(courseList,Long.parseLong(number));

            return courseList;
        }
    }

    // 과목들을 Course 배열에 저장하는 메서드
    private void saveCourses(List<Course> courseList,long idNumber) {
        for(CourseList courses : CourseList.values()) {
            if(courses.getIdNumber() == idNumber)
                courseList.add(courses.getCourse());
        }
    }

    private long countIdCreate() {
        while(true) {
            System.out.print("등록할 수강생의 고유 번호를 입력하세요: ");
            String number = Console.inputString();

            if(!Invalidate.invalidateNumber(number) && Invalidate.invalidateExistStudent(number))
                continue;

            return Long.parseLong(number);
        }
    }

    public void studentRead() {
        while (true) {
            System.out.println("등록된 학생 목록 : ");
            String studentSearch = Console.inputString();
            if (studentSearch.equals("end")) {
                break;
            }

            for (Student student : StudentData.getInstance().getStudents()) {
                if (student.getName().equals(studentSearch)) {
                    System.out.println("계정 ID: " + student.getAccountId());
                    System.out.println("이름: " + student.getName());
                    System.out.println("수강 과목:");

                    for (Course course : student.getMyCourse()) {
                        System.out.println("- " + course.getCourseName());
                    }
                }
            }
            System.out.println("----------------------------------");
        }
    }
}

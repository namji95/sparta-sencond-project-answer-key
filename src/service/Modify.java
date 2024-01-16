package service;

import domain.Grade;
import domain.course.Course;
import domain.course.CourseType;
import domain.student.Student;
import domain.student.StudentData;
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

            searchStudentCourse(studentData.getStudentInfo(Long.parseLong(number)));    // 학생의 점수를 등록 및 설정
        }
    }

    private void searchStudentCourse(Student student) {
        while(true) {
            System.out.println("특정 과목의 점수를 입력하시겠습니까? (0. 나가기)");
            printCourseList(student.getTypeCourseList(CourseType.MANDATORY),CourseType.MANDATORY);
            printCourseList(student.getTypeCourseList(CourseType.OPTIONAL),CourseType.OPTIONAL);
            String idNumber = Input.inputCourseNumber();

            if(idNumber.equals("0")) break;

            if(!Invalidate.invalidateNumber(idNumber)) continue;

            Course course = Invalidate.invalidateCourseNumber(idNumber,student.getMyCourse());

            if(course == null) continue;

            modifyCourseScore(course);              // 특정 과목의 회차 점수를 수정 및 등록
        }
    }

    private void modifyCourseScore(Course course) {
        String format = "%d회차 점수를 등록하시겠습니까? (1. 등록하기   2. 다른 회차 수정하기   0. 나가기)\n";

        while(true) {
            System.out.printf(format,course.getTurn() + 1);
            String number = Input.inputNumber();

            if(number.equals("0")) break;

            if(!Invalidate.invalidateNumber(number)
                    || !Invalidate.invalidateNumberRange(Integer.parseInt(number),0,2))
                continue;

            int choice = Integer.parseInt(number);

            if(choice == 1)
                registerScore(course);
            else
                modifyScore(course);
        }
    }

    private void registerScore(Course course) {
        if(course.fulfillSize()) {
            System.out.println("더이상 회차를 등록할 수 없습니다!");
            return;
        }

        while(true) {
            String score = Input.inputScore();

            if(!Invalidate.invalidateNumber(score)
                    || !Invalidate.invalidateNumberRange(Integer.parseInt(score),0,100))
                continue;

            course.insertScore(Integer.parseInt(score));
            System.out.println("점수가 등록되었습니다!");
            return;
        }
    }

    private void modifyScore(Course course) {
        if(course.getGrades().isEmpty()) {
            System.out.println("등록된 점수가 없습니다!");
            return;
        }

        while(true) {
            printGrades(course.getGrades());
            System.out.println("어떤 회차의 정보를 수정하시겠습니까? (0. 나가기)");
            String number = Input.inputNumber();

            if(number.equals("0"))
                break;

            if(!Invalidate.invalidateNumber(number)
                    || !Invalidate.invalidateNumberRange(Integer.parseInt(number),1,course.getTurn()))
                continue;

            insertScore(course.getGrades().get(Integer.parseInt(number) - 1));
        }
    }

    private void insertScore(Grade grade) {
        String number = Input.inputScore();

        if(!Invalidate.invalidateNumber(number)
                || !Invalidate.invalidateNumberRange(Integer.parseInt(number),0,100)) {
            System.out.println("점수 입력하는데에 오류가 발생했습니다!!");
            return;
        }

        grade.setScore(Integer.parseInt(number));
        System.out.println("점수를 수정하였습니다!!");
    }

    public static void printGrades(List<Grade> grades) {
        String format = "%-2d times | %c \n";

        int count = 1;
        for(Grade grade : grades) {
            System.out.printf(format, count,grade.getRank());
            count++;
        }
        System.out.println();
    }


    private void printCourseList(List<Course> courses, CourseType type) {
        if(type == CourseType.MANDATORY)
            System.out.println("== 필수 과정 리스트 ==");
        else if(type == CourseType.OPTIONAL)
            System.out.println("== 선택 과정 리스트 ==");

        String format = "%3d | %s\n";
        for(Course course : courses)
            System.out.printf(format, course.getIdNumber(), course.getCourseName());

        System.out.println();
    }

    private void printStudentList() {
        List<Student> students = studentData.getStudents();
        String format = "%-3d | %s\n";

        for(Student student : students)
            System.out.printf(format,student.getAccountId(),student.getName());

        System.out.println();
    }
}

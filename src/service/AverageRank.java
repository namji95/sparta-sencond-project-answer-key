package service;

import domain.Grade;
import domain.course.Course;
import domain.course.CourseList;
import domain.course.CourseType;
import domain.student.Student;
import domain.student.StudentData;
import domain.student.StudentStatus;
import invalidate.Invalidate;
import view.Input;

import java.util.ArrayList;
import java.util.List;

import static service.Modify.printGrades;

public class AverageRank {

    public void selectChoice(StudentData studentData) {
        while(true) {
            printList();
            String number = Input.inputNumber();

            if(number.equals("6"))
                break;

            if(!Invalidate.invalidateNumber(number)
                    || !Invalidate.invalidateNumberRange(Integer.parseInt(number),1,5))
                continue;

            caseOfAvg(studentData,Integer.parseInt(number));
        }
    }

    private void caseOfAvg(StudentData studentData,int choice) {
        switch (choice) {
            case 1 : printCourseAvgScore(studentData.getStudents(),1);    // 특정 과목의 전체 평균 점수
                break;

            case 2 : printCourseAvgScore(studentData.getStudents(),2);    // 특정 과목 특정 회차의 평균 등급
                break;

            case 3: studentStatusType(studentData,CourseType.MANDATORY);  // 특정 상태의 필수 과목 회차별 평균 등급
                break;

            case 4: studentStatusType(studentData,CourseType.OPTIONAL);   // 특정 상태의 선택 과목 회차별 평균 등급
                break;

            case 5: studentInfo(studentData);                              // 특정 학생의 특정 과목의 회차별 등급
                break;
        }
    }

    private void studentInfo(StudentData studentData) {
        while(true) {
            System.out.println("정보 조회를 하고 싶은 학생의 번호를 입력하세요. ( 0. 나가기 )");
            String number = Input.inputStudentNumber();

            if(number.equals("0"))
                return;

            if(!Invalidate.invalidateNumber(number) || Invalidate.invalidateStudent(number))
                continue;

            studentCourseInfo(studentData.getStudentInfo(Long.parseLong(number)).getMyCourse());
        }
    }

    private void studentCourseInfo(List<Course> courses) {
        while(true) {
            printCourseList(courses);
            System.out.println("나가길 원하면 0 을 입력하세요");
            String number = Input.inputCourseNumber();

            if(number.equals("0"))
                return;

            if(!Invalidate.invalidateNumber(number)|| !Invalidate.invalidateCourses(courses,Long.parseLong(number)))
                continue;

            printScoreInfo(getCourse(courses,Long.parseLong(number)));
        }
    }

    private void printScoreInfo(Course course) {
        if(course.getGrades().isEmpty()) {
            System.out.println("등록된 점수가 없습니다!");
            return;
        }

        printGrades(course.getGrades());
    }

    private Course getCourse(List<Course> courses, long idNumber) {
        for (Course course : courses) {
            if(course.getIdNumber() == idNumber)
                return course;
        }
        // 이미 예외처리를 다 해서 여기까지 갈 일은 없음
        throw new IllegalArgumentException();
    }

    private void printCourseList(List<Course> courses) {
        String format = " %-3d | %s \n";
        for(Course course : courses) {
            System.out.printf(format,course.getIdNumber(), course.getCourseName());
        }
        System.out.println();
    }

    private void studentStatusType(StudentData studentData, CourseType type) {
        while(true) {
            System.out.println("1. 매우 기분 좋음    2. 평범함    3. 매우 심기 불편    0. 나가기");
            String number = Input.inputNumber();

            if(number.equals("0"))
                return;

            if(!Invalidate.invalidateNumber(number)
                    || !Invalidate.invalidateNumberRange(Integer.parseInt(number),1,3))
                continue;

            List<Student> typeStudentList = studentData.getStudentStatusList(getChange(Integer.parseInt(number)));
            printStatusCourseType(typeStudentList,getChange(Integer.parseInt(number)),type);
        }
    }

    private String getChange(int number) {
        if(number == 1)
            return StudentStatus.GREEN.getStatus();
        else if(number == 2)
            return StudentStatus.YELLOW.getStatus();

        return StudentStatus.RED.getStatus();
    }

    private void printStatusCourseType(List<Student> studentList,String status ,CourseType type) {
        while(true) {
            printInfo(status,type);
            String number = Input.inputCourseNumber();

            if(!Invalidate.invalidateNumber(number))
                continue;

            if(type == CourseType.MANDATORY && !Invalidate.invalidateNumberRange(Integer.parseInt(number),1,4))
                continue;
            else if(type == CourseType.OPTIONAL && !Invalidate.invalidateNumberRange(Integer.parseInt(number),100,103))
                continue;

            calculateTimesScore(studentList,Long.parseLong(number));
            return;
        }
    }

    private void printInfo(String status, CourseType type) {
        if(status.equals(StudentStatus.GREEN.getStatus()))
            System.out.println("매우 기분 좋은 학생들의 목록");
        else if(status.equals(StudentStatus.YELLOW.getStatus()))
            System.out.println("평범한 학생들의 목록");
        else if(status.equals(StudentStatus.RED.getStatus()))
            System.out.println("매우 심기 불편한 학생들의 목록");

        System.out.println();

        if(type == CourseType.MANDATORY)
            System.out.println("== 필수 과목 회차별 평균 등급 ==");
        else if(type == CourseType.OPTIONAL)
            System.out.println("== 선택 과목 회차별 평균 등급 ==");

        System.out.println();
        printTypeCourseList(type);
    }

    private void printTypeCourseList(CourseType type) {
        String format = "%-3d | %s\n";
        for(CourseList courseList : CourseList.values()) {
            if(courseList.getType() == type)
                System.out.printf(format,courseList.getIdNumber(),courseList.getCourseName());
        }
    }

    private void printCourseAvgScore(List<Student> studentList, int type) {
        while(true) {
            printCourseList();
            String courseId = Input.inputCourseNumber();

            if(!Invalidate.invalidateNumber(courseId) || !Invalidate.invalidateCourses(courseId))
                continue;

            if(type == 1)
                calculateScore(studentList,Long.parseLong(courseId));
            else if(type == 2)
                calculateTimesScore(studentList,Long.parseLong(courseId));

            return;
        }
    }

    private void calculateTimesScore(List<Student> studentList, long idNumber) {
        List<Course> courses = new ArrayList<>();

        for(Student student : studentList) {
            if(student.getCourse(idNumber) != null)
                courses.add(student.getCourse(idNumber));
        }

        if(courses.isEmpty()) {
            System.out.println("해당 정보가 없습니다!!");
            return;
        }

        calculateTurnAvg(courses, idNumber);
    }

    private void calculateTurnAvg(List<Course> courseList, long idNumber) {
        System.out.printf("== [ %s ] 회차 별 평균 등급 ==\n\n",CourseList.getChangeName(idNumber));

        for(int i = 0; i < 10; i++) {
            char rank = calculateTest(courseList, i);

            if(rank == 0)
                return;

            System.out.printf("%-2d times | %c Rank\n",i+1,rank);
        }

    }

    private char calculateTest(List<Course> courseList, int turn) {
        int total = 0;
        int count = 0;

        for (Course course : courseList) {
            if(course.getTurn() > turn) {
                total += course.getRoundScore(turn);
                count++;
            }
        }

        if(count == 0)
            return 0;

        total /= count;

        if(courseList.get(0).getType() == CourseType.MANDATORY) {
            return Grade.mandatoryRank(total);
        } else
            return Grade.optionalRank(total);
    }

    private void calculateScore(List<Student> studentList, long idNumber) {
        double total = 0;
        int count = 0;

        for(Student student : studentList) {
            if(student.getCourseScoreList(idNumber) == 0)
                continue;

            total += student.getCourseScoreList(idNumber);
            count++;
        }

        if(count == 0) {
            System.out.println("해당 과목에 대한 정보가 없습니다!!");
            return;
        }

        total = total/count;

        System.out.printf("\nAverage Score : %.1f | %c Rank\n\n", total,calculateRank(total,CourseList.getCourseType(idNumber)));
    }

    // 평균 점수의 등급을 계산해준다.
    private char calculateRank(double score, CourseType type) {
        if(type == CourseType.MANDATORY)
            return Grade.mandatoryRank((int)score);
        else
            return Grade.optionalRank((int)score);
    }

    private void printCourseList() {
        String format = "%-3d | %-15s \n";

        for(CourseList courseList : CourseList.values())
            System.out.printf(format,courseList.getIdNumber(),courseList.getCourseName());

        System.out.println();
    }

    private void printList() {
        String[] systemList = {"특정 과목의 전체 평균 점수","특정 과목의 회차별 평균 등급","특정 상태의 필수 과목 회차별 평균 등급",
        "특정 상태의 선택 과목 회차별 평균 등급","특정 학생의 특정 과목의 회차별 등급", "메인화면으로 돌아가기"};
        String format = "%d. %s\n";
        System.out.println("\n[ 점수 관리 시스템 ]");

        for(int i = 0; i < systemList.length; i++) {
            System.out.printf(format,i+1,systemList[i]);
        }
        System.out.println();
    }

}

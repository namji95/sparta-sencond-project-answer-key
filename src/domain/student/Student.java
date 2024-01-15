package domain.student;

import domain.course.Course;
import domain.course.CourseType;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private final long accountId;
    private final String name;
    private final List<Course> myCourse;
    private String status;

    public Student(long accountId, String name, List<Course> myCourse) {
        this.accountId = accountId;
        this.name = name;
        this.myCourse = myCourse;
        status = StudentStatus.GREEN.getStatus();
    }

    public long getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getMyCourse() {
        return myCourse;
    }


    //입력한 과목에 과목목록에 있으면 true 아니면 false
    public boolean course_check(long idNumber){
        for(Course course1: myCourse){
            if(course1.getIdNumber()==idNumber){
                return true;
            }
        }
        return false;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Course> getTypeCourseList(CourseType type) {
        return new ArrayList<>(myCourse.stream().
                filter(course -> course.getType() == type)
                .toList());
    }

    // 상태를 재정의
    public void redefineStatus() {
        double total = calculateAvgCourse();
        measureStatus(total);
    }

    private void measureStatus(double total) {
        if(total == 0) {
            status = StudentStatus.GREEN.getStatus();
            return;
        }

        if(total > 70)
            status = StudentStatus.GREEN.getStatus();
        else if(total > 40)
            status = StudentStatus.YELLOW.getStatus();
        else
            status = StudentStatus.RED.getStatus();
    }

    private double calculateAvgCourse() {
        int count = 0;
        double total = 0;

        for(Course course : myCourse) {
            if(course.avgScore() == 0) continue;

            count++;
            total += course.avgScore();
        }

        if(count == 0) return 0;

        return total/count;
    }
}

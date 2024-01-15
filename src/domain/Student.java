package domain;

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
        status = "Green";
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
}

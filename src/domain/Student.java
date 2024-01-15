package domain;

import java.util.List;

public class Student {
    private final long accountId;
    private final String name;
    private final List<Course> myCourse; // 다른 메모리 주소로 변경을 할 수 없을 뿐, 데이터를 확장, 축소 가능

    public Student(long accountId, String name, List<Course> myCourse) {
        this.accountId = accountId;
        this.name = name;
        this.myCourse = myCourse;
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
}

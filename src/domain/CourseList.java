package domain;

// 과목 목록에 대한 enum class입니다.
// 여기서 과목을 설정할 수 있으며, 추가적으로 과목을 넣고 싶다면 이 enum class 에 추가를 하면 됩니다.
public enum CourseList {
    JAVA("Java",CourseType.MANDATORY),
    SPRING("Spring",CourseType.MANDATORY),
    JPA("JPA",CourseType.MANDATORY),
    MYSQL("MySQL",CourseType.MANDATORY),


    DESIGNPATTERN("디자인패턴",CourseType.OPTIONAL),
    SPRINGSECURITY("Spring Security",CourseType.OPTIONAL),
    REDIS("Redis",CourseType.OPTIONAL),
    MONGODB("MongoDB",CourseType.OPTIONAL);
    private final String courseName;
    private final CourseType type;

    CourseList(String courseName, CourseType type) {
        this.courseName = courseName;
        this.type = type;
    }

    // 과목에 대한 정보를 찾고, 반환 타입으로 과목에 대한 인스턴스를 생성해서 반환
    // 과목 이름이 없다면 에러 발생
    public static Course getCourseList(String name) {
        long count = 1;
        for(CourseList courseList : CourseList.values()) {
            if(courseList.getCourseName().equals(name))
                return new Course(count,courseList.getCourseName(),courseList.getType());

            count++;
        }
        throw new IllegalArgumentException();
    }
    // 과목의 이름을 넘겨줍니다.
    public String getCourseName() {
        return courseName;
    }

    // 과목의 타입을 넘겨줍니다.
    public CourseType getType() {
        return type;
    }
}

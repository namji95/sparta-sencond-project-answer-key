package domain;

// 과목 목록에 대한 enum class입니다.
// 여기서 과목을 설정할 수 있으며, 추가적으로 과목을 넣고 싶다면 이 enum class 에 추가를 하면 됩니다.
public enum CourseList {
    JAVA(1,"Java",CourseType.MANDATORY),
    SPRING(2,"Spring",CourseType.MANDATORY),
    JPA(3,"JPA",CourseType.MANDATORY),
    MYSQL(4,"MySQL",CourseType.MANDATORY),


    DESIGNPATTERN(100,"디자인패턴",CourseType.OPTIONAL),
    SPRINGSECURITY(101,"Spring Security",CourseType.OPTIONAL),
    REDIS(102,"Redis",CourseType.OPTIONAL),
    MONGODB(103,"MongoDB",CourseType.OPTIONAL);

    private final long idNumber;
    private final String courseName;
    private final CourseType type;

    CourseList(long idNumber, String courseName, CourseType type) {
        this.idNumber = idNumber;
        this.courseName = courseName;
        this.type = type;
    }

    // 과목에 대한 정보를 찾고, 반환 타입으로 과목에 대한 인스턴스를 생성해서 반환
    // 과목 이름이 없다면 에러 발생
    public static Course getCourseList(String name) {
        for(CourseList courseList : CourseList.values()) {
            if(courseList.getCourseName().equals(name))
                return new Course(courseList.getIdNumber(),courseList.getCourseName(),courseList.getType());

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

    // 과목의 고유 번호를 넘겨줍니다.
    public long getIdNumber() {
        return idNumber;
    }

    // 과목에 대한 인스턴스를 반환합니다.
    public Course getCourse() {
        return new Course(idNumber, courseName, type);
    }

    public static CourseType getCourseType(long number) {
        for(CourseList courseList : CourseList.values()) {
            if(courseList.getIdNumber() == number)
                return courseList.getType();
        }

        throw new IllegalArgumentException("[ERROR] 해당 정보가 없습니다.");
    }

    public String changeCourseType() {
        if(type == CourseType.MANDATORY)
            return "필수 과목";

        return "선택 과목";
    }
}

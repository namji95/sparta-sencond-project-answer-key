package invalidate;

import domain.CourseList;
import domain.CourseType;
import domain.Student;
import domain.StudentData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Invalidate {
    public static boolean invalidateNumber(String number) {
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);

        try {
            if(matcher.matches()) {
                invalidateNumberRange(number);
                return true;
            }

            throw new IllegalArgumentException("[ERROR] 숫자를 입력하시지 않으셨습니다.");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    // 해당 고유번호가 있는지 확인 -> 있으면 true , 없으면 false로 반환
    public static boolean invalidateExistStudent(String number) {
        long accountId = Long.parseLong(number);

        try {
            searchStudent(accountId);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true;
        }
        return false;
    }

    public static void invalidateNumberRange(String number) {
        int num = Integer.parseInt(number);

        if(num <= 0)
            throw new IllegalArgumentException("[ERROR] 해당 범위의 숫자는 할 수 없습니다.");
    }

    public static boolean invalidateCourse(String number) {
        String[] numberList = number.split(",");

        try {
            invalidateDuplicateNumber(numberList);
            invalidateCourseSize(numberList);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println();
            return false;
        }

        return true;
    }

    public static boolean invalidateStudent(String number) {
        long accountId = Long.parseLong(number);

        try {
            checkStudent(accountId);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true;
        }

        return false;
    }

    private static void checkStudent(long number) {
        for(Student student : StudentData.getInstance().getStudents()) {
            if(student.getAccountId() == number)
                return;
        }

        throw new IllegalArgumentException("[ERROR] 해당 학생이 존재하지 않습니다!");
    }

    // 중복이 발생하면 true, 중복이 발생하지 않으면 false
    private static void invalidateDuplicateNumber(String[] numberList) {
        Set<String> numbers = new HashSet<>(List.of(numberList));

        if(numbers.size() != numberList.length)
            throw new IllegalArgumentException("[ERROR] 과목의 중복이 발생했습니다.");
    }

    // 필수 수강 과목에 대한 예외조건
    private static void invalidateCourseSize(String[] numberList) {
        int mandatorySize = 0;
        int optionalSize = 0;

        for(String number : numberList) {
            if(CourseList.getCourseType(Long.parseLong(number)) == CourseType.MANDATORY)
                mandatorySize++;
            else
                optionalSize++;
        }

        if(mandatorySize < 3 || optionalSize < 2)
            throw new IllegalArgumentException("[ERROR] 최소 수강 과목을 이수하지 않으셨습니다.");
    }

    private static void searchStudent(long accountId) {
        for(Student student : StudentData.getInstance().getStudents()) {
            if(student.getAccountId() == accountId)
                throw new IllegalArgumentException("[ERROR] 해당 수강생이 존재합니다.");
        }
    }
}

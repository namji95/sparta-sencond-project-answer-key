package view;

import console.Console;

public class Input {
    public static String inputNumber() {
        System.out.print("숫자를 입력하세요: ");
        return Console.inputString();
    }

    public static String inputStudentNumber() {
        System.out.print("학생의 번호를 입력하세요: ");
        return Console.inputString();
    }

    public static String inputCourseNumber() {
        System.out.print("과목의 번호를 입력하세요: ");
        return Console.inputString();
    }

    public static String inputScore() {
        System.out.print("점수를 입력하세요(0 ~ 100 사이): ");
        return Console.inputString();
    }
}

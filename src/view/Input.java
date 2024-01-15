package view;

import console.Console;

public class Input {
    public static String inputNumber() {
        System.out.print("숫자를 입력하세요: ");
        return Console.inputString();
    }
}

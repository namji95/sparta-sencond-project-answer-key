package console;

import java.util.Scanner;

public class Console {
    private static Scanner scanner;

    private Console() {}

    private static Scanner getInstance() {
        if(scanner == null)
            scanner = new Scanner(System.in);

        return scanner;
    }

    // Console.inputString(); -> 메서드를 부르면 Scanner 역할처럼 문자열 값을 입력 받아 해당 문자열을 반환합니다.
    public static String inputString() {
        return getInstance().nextLine();
    }

    // Console.inputInt(); -> 메서드를 호출하면 숫자를 입력받을 수 있고 반환으로 해당 숫자가 반환됩니다.
    public static int inputInt() {
        return Integer.parseInt(getInstance().nextLine());
    }

}

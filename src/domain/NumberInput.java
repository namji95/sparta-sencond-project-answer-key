package domain;
import java.util.InputMismatchException;
import java.util.Scanner;
public class NumberInput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int Num = 0, flag;

        do {
            flag = 1;
            try {
                System.out.println("숫자입력 = ");
                Num = sc.nextInt();
                System.out.println("입력받은 숫자는 = " + Num);
            }
            catch(InputMismatchException e) {
                flag=0;
                sc.nextLine();
                System.out.println("정상적인 숫자를 입력하세요");
            }
        } while(flag==0);
    }
}

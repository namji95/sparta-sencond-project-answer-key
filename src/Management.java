import console.Console;
import view.Input;

public class Management {

    // 실행할 때 해당 메서드를 중심으로 실행
    public void run() {
        while(true) {
            System.out.println("메인 화면입니다."); // 여기는 나중에 수정할 예정
            System.out.println("1. 등록하기    2. 수정하기    3. 조회하기    4. 나가기");
            int number = Integer.parseInt(Input.inputNumber());

            if(number==4)
                break;
        }

    }

    public void end() {
        System.out.println("종료되었습니다.");
    }

    private void switchCase(int number) {
        switch (number) {
            case 1:                 // 회원 등록하기
                break;

            case 2:                 // 회원 수정하기
                break;

            case 3:                 // 회원 조회하기
                break;

            default:
                break;
        }
    }
}

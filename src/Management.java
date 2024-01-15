import domain.StudentData;
import service.*;
import view.Input;

public class Management {

    private Register register;
    private Modify modify;
    private Information information;
    private Delete delete;
    private Avg_calculation avg_calculation;

    public Management() {
        register = new Register();
        modify = new Modify();
        information = new Information();
        delete = new Delete();
    }

    // 실행할 때 해당 메서드를 중심으로 실행
    public void run() {
        while(true) {
            System.out.println("메인 화면입니다."); // 여기는 나중에 수정할 예정
            System.out.println("1. 등록하기    2. 수정하기    3. 조회하기    4. 삭제하기    5. 등급조회    0. 나가기");
            int number = Integer.parseInt(Input.inputNumber());

            switchCase(number);

            if(number==0)
                break;
        }

    }

    public void end() {
        System.out.println("종료되었습니다.");
    }

    private void switchCase(int number) {
        try {
            switch (number) {
                case 1:
                    System.out.println("수강생을 등록하겠습니까?");
                    register.registerStudent(); // 수강생 등록하기
                    break;

                case 2:
                    System.out.println("수강생을 수정하시겠습니까?");
                    modify.modify();            // 수강생 수정하기
                    break;

                case 3:
                    System.out.println("수강생을 조회하시겠습니까?");
                    information.lookUpInformation(); // 수강생 조회하기
                    break;

                case 4:
                    System.out.println("수강생을 삭제하시겠습니까?");
                    delete.deleteStudent();
                    break;
                case 5:
                    avg_calculation.avg_screen(StudentData.getInstance().getStudents()); //예외처리
                    break;
                default:
                    throw new service.out_of_range();
            }
        }catch (NullPointerException e){
            System.out.println("현재 수강생이 없습니다.");
        }catch(out_of_range e){
            System.out.println("선택번호에 없는 값 입니다.");
        }
    }
}

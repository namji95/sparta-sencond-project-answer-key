import domain.student.StudentData;
import service.*;
import view.Input;

public class Management {
    private final Register register;
    private final Modify modify;
    private final Information information;
    private final Delete delete;
    private final AverageRank averageRank;
    private StudentData studentData = StudentData.getInstance();

    public Management() {
        register = new Register();
        modify = new Modify();
        information = new Information();
        delete = new Delete();
        averageRank = new AverageRank();
    }

    // 실행할 때 해당 메서드를 중심으로 실행
    public void run() {
        while(true) {
            System.out.println("스파르타 코딩 클럽 수강생 관리자 메뉴입니다.\n");
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
        switch (number) {
            case 1:
                System.out.println("수강생을 등록하겠습니까?");
                register.registerStudent();
                break;

            case 2:
                System.out.println("수강생을 수정하시겠습니까?");
                modify.modify();
                break;

            case 3:
                System.out.println("수강생을 조회하시겠습니까?");
                information.lookUpInformation(studentData);
                break;

            case 4:
                System.out.println("수강생을 삭제하시겠습니까?");
                delete.deleteStudent(studentData);
                break;

            case 5:
                averageRank.selectChoice(studentData);
                break;

            default:
                break;
        }
    }
}

import console.Console;
import domain.StudentData;
import service.Delete;
import service.Information;
import service.Modify;
import service.Register;
import view.Input;

public class Management {

    private Register register;
    private Modify modify;
    private Information information;
    private Delete delete;
    private StudentData studentData = StudentData.getInstance();

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
            System.out.println("1. 등록하기    2. 수정하기    3. 조회하기    0. 나가기");
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
                register.registerStudent(); // 수강생 등록하기
                break;

            case 2:
                System.out.println("수강생을 수정하시겠습니까?");
                modify.modify();            // 수강생 수정하기
                break;

            case 3:
                System.out.println("수강생을 조회하시겠습니까?");
                information.lookUpInformation(studentData.getStudents()); // 수강생 조회하기
                break;

            case 4:
                System.out.println("수강생을 삭제하시겠습니까?");
                delete.deleteStudent(studentData.getStudents());
                break;

            default:
                break;
        }
    }
}

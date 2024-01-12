package score_avg;

import console.Console;
import domain.CourseList;
import domain.Student;

import java.util.InputMismatchException;
import java.util.List;

public class choose {
    //번호를 입력받는 메서드
    public static int choose_num(){
        while (true) {
            try {
                System.out.println("번호를 입력해 주세요.");
                int choose = Console.inputInt();
                return choose;
            } catch (InputMismatchException e) {
                System.out.println("잘못된 값을 입력하셨습니다. 다시 입력해 주세요.");
            }
        }
    }
    //고유번호를 받는 메서드 고유번호가 없는 번호면 예외를 발생시킵니다.
    public static Student choose_idNumber(List<Student> students){
        while (true) {
            try {
                System.out.println("학생의 고유번호를 입력해 주세요.");
                int choose = Console.inputInt();
                if(find_student(students,choose)==null){
                    throw new InputMismatchException();
                }
                return find_student(students,choose);
            } catch (InputMismatchException e) {
                System.out.println("잘못된 값을 입력하셨습니다. 다시 입력해 주세요.");
            }
        }
    }
    //문자열을 입력받아 CourseList에 포함되는지 여부를 확인하는 메서드
    public static CourseList choose_course(){
        while (true) {
            try {
                System.out.println("과목명을 입력해주세요.");
                String choose = Console.inputString();
                return CourseList.getCourseList_search(choose);
            } catch (Exception e) {
                System.out.println("잘못된 값을 입력하셨습니다. 다시 입력해 주세요.");
            }
        }

    }
    //문자열을 입력받아 state리스트에 존재하는 상태라면 return 해주는 메서드
    public static String choose_state(){
        while (true){
            try{
                System.out.println("원하시는 상태를 입력해주세요.");
                String state = Console.inputString();
                //상태 리스트에 상태가 있으면의 이야기 현재는  없으므로 패쓰
                /* if(!state_test.contains(state)){
                    throw new InputMismatchException();
                }*/
                return state;
            } catch (Exception e){
                System.out.println("잘못된 값을 입력하셨습니다. 다시 입력해 주세요.");
            }
        }
    }
    //회차를 입력받는 메서드 회차번호가 올바르지 않다면 예외를 발생시킵니다.
    public static int choose_round(){
        while (true) {
            try {
                System.out.println("회차를 입력해 주세요.");
                int choose = Console.inputInt();
                if(choose>10||choose<1){
                    throw new InputMismatchException();
                }
                return choose-1;
            } catch (InputMismatchException e) {
                System.out.println("잘못된 값을 입력하셨습니다. 다시 입력해 주세요.");
            }
        }
    }
    public static Student find_student (List<Student> students,int accointID){
        Student find = null;
        for(Student student: students){
            if(student.getAccountId()==accointID){
                find = student;
            }
        }
        return find;
    }
}

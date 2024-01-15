package service;

import console.Console;
import domain.*;

import java.util.*;


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
                    throw new out_of_range();
                }
                return find_student(students,choose);
            } catch (InputMismatchException e) {
                System.out.println("잘못된 값을 입력하셨습니다. 다시 입력해 주세요.");
            } catch (out_of_range e){
                System.out.println("잘못된 고유번호 입니다. 다시 입력해 주세요.");
            }
        }
    }
    //문자열을 입력받아 CourseList에 포함되는지 여부를 확인하는 메서드
//    public static CourseList choose_course(){
//        while (true) {
//            try {
//                System.out.println("과목명을 입력해주세요.");
//                String choose = Console.inputString();
//                return CourseList.getCourseList_search(choose);
//            } catch (InputMismatchException e) {
//                System.out.println("잘못된 값을 입력하셨습니다. 다시 입력해 주세요.");
//            }
//        }
//
//    }
//    //수업을 선택할때 사용하는 메서드의 오버로딩 형태 특정학생을 기준으로 포함여부를 확인하는 메서드
//    public static CourseList choose_course(Student student){
//        while(true){
//            try {
//                CourseList courseList = choose_course();
//                if(student.course_check(courseList.getIdNumber())){
//                    return courseList;
//                }
//                else{
//                    throw new out_of_range();
//                }
//            }catch (out_of_range e){
//                System.out.printf("%s 학생이 수강하는 수업이 아닙니다. 다시 입력해 주세요.\n",student.getName());
//            }
//
//        }
//    }
    //문자열을 입력받아 state리스트에 존재하는 상태라면 return 해주는 메서드
    public static String choose_state() {
        while (true) {
            try {
                System.out.println("잘못된 값을 입력하셨습니다. 다시 입력해 주세요.");
                String state = Console.inputString();
                //상태 리스트에 상태가 있으면의 이야기 현재는  없으므로 패쓰
                /* if(!state_test.contains(state)){
                    throw new out_of_range();
                }*/
                return state;
            } catch (InputMismatchException e) {
                System.out.println("없는 상태 정보 입니다.");
           /* } catch (out_of_range e){
                System.out.println("없는 상태 정보 입니다.");
            }*/
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
                    throw new out_of_range();
                }
                return choose;
            } catch (InputMismatchException e) {
                System.out.println("잘못된 값을 입력하셨습니다. 다시 입력해 주세요.");
            }catch (out_of_range e){
                System.out.println("없는 회차번호 입니다.");
            }
        }
    }
    //학생아이디를 받아 학생을 찾는 메서드
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


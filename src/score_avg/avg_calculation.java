package score_avg;

import java.util.*;
import java.util.stream.Collectors;
import console.*;
import domain.*;

public class avg_calculation {
    List<String> state_test = new ArrayList<String>(Arrays.asList("red","green","blue"));
    //학생들의 여러 점수를 조회하는 스크린을 연결시켜주는 메서드
    public void avg_screen(List<Student> students){
        Notice();
        while (true) {
            switch (choose_num()){
                case 1:
                    course_all_round_avg_screen(students,choose_course());
                    break;
                case 2:
                    course_choose_round_avg_screen(students,choose_course(),choose_round());
                    break;
                case 3:
                    state_mandatory_course_all_round_avg(choose_student_state(students,choose_state()));
                    break;
                case 4:
                    state_optiona_course_all_round_avg(choose_student_state(students,choose_state()));
                    break;
                default:
                    continue;
            }
            System.out.println("메인화면으로 돌아갑니다...");
            break;
        }
    }
    //학생리스트,과목,회차를 받아와 과목의 고유번호를 대조해 학생들 중 매개변수로 받은 과목을 수강하는 학생이 있다면 점수를 추출하여
    // 평균을 구하는 메서드 return시 고유번호가 100보다 크면 선택 100이하면 필수 과목으로 계산합니다.
    public char course_round_avg(List<Student> students,CourseList courseList,int round){
        int score_sum = 0;
        int student_count = 0;
        for(Student student: students){
            for(Course c : student.getMyCourse()){
                if(courseList.getIdNumber()==c.getIdNumber()){
                    score_sum += c.getRoundScore(round);
                    student_count ++;
                }
            }
        }
        return courseList.getIdNumber()>100 ?  optiona_rank(score_sum/student_count): mandatory_rank(score_sum/student_count);
    }
    //학생리스트,과목을 받아와 특정 과목에 모든 회차의 평균을 출력하는 메서드
    public void course_all_round_avg_screen(List<Student> student,CourseList courseList){
        System.out.printf("%s 과목의 회차별 등급 평균 \n",courseList.getCourseName());
        for(int round =0; round<10; round++){
            System.out.printf("%d 회차: %c 등급 \n",round+1,course_round_avg(student,courseList,round));
        }
    }
    //학생리스트,과목,회차 정보를 받아와 특정 과목에 특정 회차의 평균 등급을 출력하는 메서드
    public void course_choose_round_avg_screen(List<Student> student,CourseList courseList,int round){
        System.out.printf("%s 과목의 %d회차 등급 평균 : %c 등급\n",courseList.getCourseName(),round,course_round_avg(student,courseList, round));
    }
    //학생,과목,회차 정보를 받아 학생안에 과목목록리스트에서 매개변수로 받아온 과목의 고유번호를 대조해 같다면 학생이 가지고 그 과목의 등급을 출력시켜주는 메서드
    //등급을 출력시킬 때 위에 메서드와 같이 묶어서 하나로 두고 점수를 받아 랭크를 얻는 것도 가능하지만 다양한 메서드를 만들어 보기 위해 서로 다른 방향에서
    //하나는 랭크 자체를 가져오고 하나는 점수를 가져와 바꾸는 메서드로 만드는 기능으로 나눴습니다.
    public char student_course_round_rank(Student student,CourseList courseList,int round){
        char rank = 'N';
        for(Course cor : student.getMyCourse()){
            if(cor.getIdNumber()==courseList.getIdNumber()){
                rank=cor.getRoundRank(round);
            }
        }
        return rank;
    }
    //특정 학생의 특정 과목 화차별 등급을 학생,과목 정보를 받아 출력시켜주는 메서드
    public void student_course_all_round_rank(Student student,CourseList courseList){
        System.out.printf("%s 학생의 %s 과목 회차별 등급 \n",student.getName(),courseList.getCourseName());
        for(int round =0;round<10;round++){
            System.out.printf("%d 회차: %c 등급\n",round+1,student_course_round_rank(student,courseList,round));
        }
    }
    //특정 학생의 특정 과목의 특정 회차의 등급을 학생,과목,회차 정보를 받아 출력시켜주는 메서드
    public void student_course_choose_round_rank(Student student,CourseList courseList,int round){
        System.out.printf("%s 학생의 %s 과목 %d회차 등급 : %c\n",student.getName(),courseList.getCourseName(),round+1,student_course_round_rank(student,courseList,round));
    }
    //학생 리스트와 원하는 상태를 받아서 상태에 맞는 학생 리스트를 뽑아 return 해주는 메서드
    public List<Student> choose_student_state(List<Student> students,String state){
        System.out.printf("%s 상태의 ",state);
        return students.stream().filter(s->s.getState().equals(state)).collect(Collectors.toList());
    }
    //상태로 나뉜 학생리스트를 받아 100이하의 고유번호 즉 필수 과목에 대한 특정 상태의 학생의 평균을 구하는 메서드
    public void state_mandatory_course_all_round_avg(List<Student> students){
        for(CourseList courseList:CourseList.values()){
            if(courseList.getIdNumber()<100){
                course_all_round_avg_screen(students,courseList);
            }
        }
    }
    //상태로 나뉜 학생리스트를 받아 100이상의 고유번호 즉 선택 과목에 대한 특정 상태의 학생의 평균을 구하는 메서드
    public void state_optiona_course_all_round_avg(List<Student> students){
        for(CourseList courseList:CourseList.values()){
            if(courseList.getIdNumber()>100){
                course_all_round_avg_screen(students,courseList);
            }
        }
    }
    //임시 필수과목 등급지정
    public char mandatory_rank(int score){
        switch (score/5){
            case 19,20:
                return 'A';
            case 18:
                return 'B';
            case 16,17:
                return 'C';
            case 14,15:
                return 'D';
            case 12,13:
                return 'F';
            default:
                return 'N';
        }
    }
    //임시 선택과목 등급 지정

    public char optiona_rank(int score) {
        switch (score / 10) {
            case 9, 10:
                return 'A';
            case 8:
                return 'B';
            case 7:
                return 'C';
            case 6:
                return 'D';
            case 5:
                return 'F';
            default:
                return 'N';
        }
    }
    //번호를 입력받는 메서드 번호가 범위내가 아니라면 예외를 발생시킵니다.(default로 다시 continue 되겠지만 피드백에서
    //실패했을 때 예외처리를 하는 것이 좋다는 이야기가 나왔기에 예외로 처리했습니다.)
    public int choose_num(){
        while (true) {
            try {
                System.out.println("번호를 입력해 주세요.");
                int choose = Console.inputInt();
                if(choose>6||choose<1){
                    throw new InputMismatchException();
                }
                return choose;
            } catch (InputMismatchException e) {
                System.out.println("잘못된 값을 입력하셨습니다. 다시 입력해 주세요.");
            }
        }
    }
    //고유번호를 받는 메서드 고유번호가 없는 번호면 예외를 발생시킵니다.
    public Student choose_idNumber(List<Student> students){
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
    public CourseList choose_course(){
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
    public String choose_state(){
        while (true){
            try{
                System.out.println("원하시는 상태를 입력해주세요.");
                String state = Console.inputString();
                if(!state_test.contains(state)){
                    throw new InputMismatchException();
                }
                return state;
            } catch (Exception e){
                System.out.println("잘못된 값을 입력하셨습니다. 다시 입력해 주세요.");
            }
        }
    }
    //회차를 입력받는 메서드 회차번호가 올바르지 않다면 예외를 발생시킵니다.
    public int choose_round(){
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
    //학생리스트에서 고유번호를 이용해 특정 학생을 찾아 return 해주는 메서드
    public Student find_student (List<Student> students,int accointID){
        Student find = null;
        for(Student student: students){
            if(student.getAccountId()==accointID){
                find = student;
            }
        }
        return find;
    }
    //가독성을 위한 안내문 메서드
    public void Notice(){
        System.out.println("[ 점수 관리 시스템 ]");
        System.out.println("1. 특정 과목 회차별 평균 등급");
        System.out.println("2. 특정 과목 특정 회차의 평균 등급");
        System.out.println("3. 특정 상태의 필수 과목 회차별 평균 등급");
        System.out.println("4. 특정 상태의 선택 과목 회차별 평균 등급");
    }
}

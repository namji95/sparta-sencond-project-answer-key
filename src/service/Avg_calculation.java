package service;

import java.util.*;
import java.util.stream.Collectors;
import domain.*;


public class Avg_calculation {
    //임시 상태확인
    List<String> state_test = new ArrayList<String>(Arrays.asList("red","green","blue"));
    //학생들의 여러 점수를 조회하는 스크린을 연결시켜주는 메서드
    public void avg_screen(List<Student> students){
        boolean run = true;
        while (run) {
            Notice();
            try{
            switch (choose.choose_num("원하시는 항목의 번호를 입력해주세요.")) {
                case 1:
                    course_all_round_avg_screen(students, choose.choose_course("조회하실 과목의 이름을 입력해주세요."));
                    break;
                case 2:
                    course_choose_round_avg_screen(students, choose.choose_course("조회하실 과목의 이름을 입력해주세요."), choose.choose_round("조회하실 회차를 입력해주세요."));
                    break;
                case 3:
                    state_mandatory_course_all_round_avg(choose_student_state(students,choose.choose_state("조회하실 그룹의 상태를 입력해주세요.")));
                    break;
                case 4:
                    state_optiona_course_all_round_avg(choose_student_state(students, choose.choose_state("조회하실 그룹의 상태를 입력해주세요.")));
                    break;
                case 5:
                    System.out.println("메인화면으로 돌아갑니다...");
                    run = false;
                    break;
                default:
                    throw new out_of_range();
            }
            }catch (out_of_range e){
                System.out.println("선택번호의 없는 값 입니다.");
            }catch (IndexOutOfBoundsException e){
                System.out.println("아직 점수를 입력받지 않은 학생이 있습니다.");
            }
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
        return courseList.getIdNumber()>=100 ?  domain.Grade.optionalRank(score_sum/student_count): domain.Grade.mandatoryRank(score_sum/student_count);
    }
    //학생리스트,과목을 받아와 특정 과목에 모든 회차의 평균을 출력하는 메서드
    public void course_all_round_avg_screen(List<Student> student,CourseList courseList){
        char error = course_round_avg(student,courseList,0);
        System.out.printf("%s 과목의 회차별 등급 평균 \n",courseList.getCourseName());
        for(int round =0; round<10; round++){
            System.out.printf("%d 회차: %c 등급 \n",round+1,course_round_avg(student,courseList,round));
        }
    }
    //학생리스트,과목,회차 정보를 받아와 특정 과목에 특정 회차의 평균 등급을 출력하는 메서드
    public void course_choose_round_avg_screen(List<Student> student,CourseList courseList,int round){
        System.out.printf("%s 과목의 %d회차 등급 평균 : %c 등급\n",courseList.getCourseName(),round,course_round_avg(student,courseList, round));
    }
    //학생 리스트와 원하는 상태를 받아서 상태에 맞는 학생 리스트를 뽑아 return 해주는 메서드
    public List<Student> choose_student_state(List<Student> students,String state){
        System.out.printf("[ %s 상태 그룹 ] \n",state);
        return students.stream().filter(s->s.getStatus().equals(state)).collect(Collectors.toList());
    }
    //상태로 나뉜 학생리스트를 받아 100이하의 고유번호 즉 필수 과목에 대한 특정 상태의 학생의 평균을 구하는 메서드
    public void state_mandatory_course_all_round_avg(List<Student> students){
        try{
            if(students.isEmpty()){
                throw new out_of_range();
            }
            for(CourseList courseList:CourseList.values()){
                if(courseList.getIdNumber()<100){
                    course_all_round_avg_screen(students,courseList);
                }
            }
        }catch (out_of_range e){
            System.out.println("현재 해당 그룹에 학생이 없습니다.");
        }
    }
    //상태로 나뉜 학생리스트를 받아 100이상의 고유번호 즉 선택 과목에 대한 특정 상태의 학생의 평균을 구하는 메서드
    public void state_optiona_course_all_round_avg(List<Student> students){
        try{
            if(students.isEmpty()){
                throw new out_of_range();
            }
            for(CourseList courseList:CourseList.values()){
                if(courseList.getIdNumber()>=100){
                    course_all_round_avg_screen(students,courseList);
                }
            }
        }catch (out_of_range e){
            System.out.println("현재 해당 그룹에 학생이 없습니다.");
        }
    }

    //학생리스트에서 고유번호를 이용해 특정 학생을 찾아 return 해주는 메서드

    //가독성을 위한 안내문 메서드
    public void Notice(){
        System.out.println("[ 점수 관리 시스템 ]");
        System.out.println("1. 특정 과목 회차별 평균 등급");
        System.out.println("2. 특정 과목 특정 회차의 평균 등급");
        System.out.println("3. 특정 상태의 필수 과목 회차별 평균 등급");
        System.out.println("4. 특정 상태의 선택 과목 회차별 평균 등급");
        System.out.println("5. 메인화면으로 돌아가기");
    }
}

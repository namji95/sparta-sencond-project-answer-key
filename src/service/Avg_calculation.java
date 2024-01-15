package service;

import java.util.*;
import java.util.stream.Collectors;
import domain.*;


public class Avg_calculation {
    //학생들의 여러 점수를 조회하는 스크린을 연결시켜주는 메서드
    public void avg_screen(List<Student> students){
        boolean run = true;
        Student student;
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
                    student = choose.choose_idNumber(students,"조회하실 학생의 고유번호를 입력해주세요.");
                    student_course_all_round_rank(student,choose.choose_course(student,"조회하실 과목의 이름을 입력해주세요."));
                    break;
                case 6:
                    student = choose.choose_idNumber(students,"조회하실 학생의 고유번호를 입력해주세요.");
                    student_course_choose_round_rank(student,choose.choose_course(student,"조회하실 과목의 이름을 입력해주세요."),choose.choose_round("조회하실 회차를 입력해주세요."));
                    break;
                case 7:
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
            }catch (NullPointerException e){
                System.out.println("아직 학생 정보가 없습니다.");
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
    //학생,과목,회차 정보를 받아 학생안에 과목목록리스트에서 매개변수로 받아온 과목의 고유번호를 대조해 같다면 학생이 가지고 그 과목의 등급을 출력시켜주는 메서드
    //등급을 출력시킬 때 위에 메서드와 같이 묶어서 하나로 두고 점수를 받아 랭크를 얻는 것도 가능하지만 다양한 메서드를 만들어 보기 위해 서로 다른 방향에서
    //하나는 랭크 자체를 가져오고 하나는 점수를 가져와 바꾸는 메서드로 만드는 기능으로 나눴습니다.
    public char student_course_round_rank(Student student, CourseList courseList, int round){
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


    //가독성을 위한 안내문 메서드
    public void Notice(){
        System.out.println("[ 점수 관리 시스템 ]");
        System.out.println("1. 특정 과목 회차별 평균 등급");
        System.out.println("2. 특정 과목 특정 회차의 평균 등급");
        System.out.println("3. 특정 상태의 필수 과목 회차별 평균 등급");
        System.out.println("4. 특정 상태의 선택 과목 회차별 평균 등급");
        System.out.println("5. 특정 학생의 특정 과목의 회차별 등급");
        System.out.println("6. 특정 학생의 특정 과목의 특정 회차의 등급");
        System.out.println("7. 메인화면으로 돌아가기");
    }

}

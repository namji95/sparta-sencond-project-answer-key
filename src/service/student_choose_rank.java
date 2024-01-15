package service;

import domain.Course;
import domain.CourseList;
import domain.Student;

//나중에 합칠것
public class student_choose_rank {
    public void screen(Student student){
        try {
            System.out.println("예시 1.입력시 ");
            student_course_all_round_rank(student, choose.choose_course(student,"조회하실 "+student.getName()+"학생의 과목을 입력해주세요."));
            System.out.println("예시 2 입력시");
            student_course_choose_round_rank(student, choose.choose_course(student,"조회하실 "+student.getName()+"학생의 과목을 입력해주세요."), choose.choose_round("조회하실 회차를 입력해주세요."));
        }catch (NullPointerException e){
            System.out.println("현재 학생의 랭크가 들어있지 않습니다.");
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
}

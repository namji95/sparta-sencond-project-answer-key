package score_avg;

import java.util.*;

import domain.*;

public class avg_calculation {
    Scanner sc = new Scanner(System.in);
    List<Student> 예시 = new ArrayList<Student>();
    //학생들의 여러 점수를 조회하는 스크린을 연결시켜주는 메서드 (미완성)
    public void avg_screen(List<Student> students){
        Notice();
        switch (choose_num()){
            case 1:
                course_all_round_avg_screen(students,choose_course());
        }
    }
    //학생리스트,과목,회차를 받아와 과목의 고유번호를 대조해 학생들 중 매개변수로 받은 과목을 수강하는 학생이 있다면 점수를 추출하여
    // 평균을 구하는 메서드 return시 고유번호가 100보다 크면 선택 100이하면 필수 과목으로 계산합니다.
    public char course_round_avg(List<Student> student,CourseList courseList,int round){
        int score_sum = 0;
        int student_count = 0;
        for(Student s: student){
            for(Course c : s.getMyCourse()){
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
        System.out.println("과목을 입력해 주세요.");
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
    public char student_course_rank_round(Student student,Course course,int round){
        char rank = 'N';
        for(Course cor : student.getMyCourse()){
            if(cor.getIdNumber()==course.getIdNumber()){
                rank=course.getRoundRank(round);
            }
        }
        return rank;
    }
    //특정 학생의 특정 과목 화차별 등급을 학생,과목 정보를 받아 출력시켜주는 메서드
    public void student_course_rank_all_round(Student student,Course course){
        System.out.printf("%s 학생의 %s 과목 회차별 등급 \n",student.getName(),course.getCourseName());
        for(int round =0;round<course.getGrades().size();round++){
            System.out.printf("%d 회차: %c 등급\n",round+1,student_course_rank_round(student,course,round));
        }
    }
    //특정 학생의 특정 과목의 특정 회차의 등급을 학생,과목,회차 정보를 받아 출력시켜주는 메서드
    public void student_course_rank_choose_round(Student student,Course course,int round){
        System.out.printf("%s 학생의 %s 과목 %d회차 등급 : %c\n",student.getName(),round+1,student_course_rank_round(student,course,round));
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
    //번호를 입력받는 메서드
    public int choose_num(){
        while (true) {
            try {
                int choose = sc.nextInt();
                sc.nextLine();
                return choose;
            } catch (InputMismatchException e) {
                sc = new Scanner(System.in);
                System.out.println("잘못된 값을 입력하셨습니다. 다시 입력해 주세요.");
            }
        }
    }
    //문자열을 입력받아 CourseList에 포함되는지 여부를 확인하는 메서드
    public CourseList choose_course(){
        while (true) {
            try {
                String choose = sc.nextLine();
                return CourseList.getCourseList(choose);
            } catch (Exception e) {
                sc = new Scanner(System.in);
                System.out.println("잘못된 값을 입력하셨습니다. 다시 입력해 주세요.");
            }
        }

    }
    //가독성을 위한 안내문 메서드
    public void Notice(){
        System.out.println("[ 점수 관리 시스템 ]");
        System.out.println("1. 특정 과목 회차별 평균 등급");
        System.out.println("2. 특정 과목 특정 회차의 등급");
        System.out.println("3. 특정 학생의 특정 과목 회차별 등급");
        System.out.println("4. 특정 학생의 특정 과목 특정 회차의 등급");
    }
}

package score_avg;

import java.util.*;

import domain.*;

public class avg_calculation {
    Scanner sc = new Scanner(System.in);
    public void avg_screen(List<Student> student,Course course){
        switch (choose_num()){
            case 1:
        }
    }
    public char course_round_avg(List<Student> student,Course course,int round){
        int score_sum = 0;
        int student_count = 0;
        for(Student s: student){
            for(Course c : s.getMyCourse()){
                if(course.getIdNumber()==c.getIdNumber()){
                    score_sum += c.getGrades().get(round).getScore();
                    student_count ++;
                }
            }
        }
        return course.getIdNumber()>100 ?  optiona_rank(score_sum/student_count): mandatory_rank(score_sum/student_count);
    }

    public void course_all_round_avg_screen(List<Student> student,Course course){
        System.out.printf("%s 과목의 회차별 등급 평균 \n",course.getCourseName());
        for(int i =0; i<10; i++){
            System.out.printf("%d 회차: %c 등급 \n",i+1,course_round_avg(student,course,i));
        }
    }

    public void course_choose_round_avg_screen(List<Student> student,Course course,int round){
        System.out.printf("%s 과목의 %d회차 등급 평균 : %c 등급\n",course.getCourseName(),round,course_round_avg(student, course, round));
    }

    public void student_course_rank_all_round(Student student,Course course){
        System.out.printf("%s 학생의 %s 과목 회차별 등급 \n",student.getName(),course.getCourseName());
        for(int round =0;round<10;round++){
            System.out.printf("%d 회차: %c 등급\n",round+1);
        }
    }

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
}

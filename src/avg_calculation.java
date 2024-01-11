import java.util.*;

import domain.*;

public class avg_calculation {

    public void avg_screen(){

    }
    public char course_round_avg(List<Student> st,long idNumber,int round){
        int score_sum = 0;
        int student_count = 0;
        for(Student s: st){
            for(Course c : s.getMyCourse()){
                if(idNumber==c.getIdNumber()){
                    score_sum += c.getGrades().get(round).getScore();
                    student_count ++;
                }
            }
        }
        return idNumber>100 ?  optiona_rank(score_sum/student_count): mandatory_rank(score_sum/student_count); //점수 카운트 초기화해야함
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

    public char optiona_rank(int score){
        switch (score/10){
            case 9,10:
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
}

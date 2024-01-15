package domain;
import domain.course.CourseType;

public class Grade {
    // 점수 안에 회차, 점수, 등급이 설정되어야한다.
    // 점수가 들어오면 등급을 설정할 수 있게 만들어야한다.
    private final CourseType type;        // 과목 타입 -> 타입별로 등급이 정해지는 기준이 다름
    private int score;              // 점수
    private char rank;              // 등급


    public Grade(int score,CourseType type) {
        this.score = score;
        this.type = type;
        setRank(score);

    }
    public int getScore() {
        return score;
    }

    public char getRank() {
        return rank;
    }

    // 점수를 세팅하는 메서드, 점수가 입력되자마자 등급이 자동으로 설정됨
    public void setScore(int score) {
        this.score = score;
        setRank(score);
    }

    private void setRank(int score) {
        if(type == CourseType.MANDATORY) {
            rank = mandatoryRank(score);
            return;
        }

        rank = optionalRank(score);
    }

    public static char mandatoryRank(int score) {
        char rank;

        if(score >=95 && score <=100)
            rank = 'A';
        else if(score >=90 && score <=94)
            rank = 'B';
        else if(score >=80 && score <=89)
            rank = 'C';
        else if(score >=70 && score <=79)
            rank = 'D';
        else if(score >=60 && score <=69)
            rank = 'F';
        else {
            rank = 'N';
        }
        return rank;
    }
    public static char optionalRank(int score) {
        char rank;

        if(score >=90&& score <=100)
            rank = 'A';
        else if(score >=80 && score <=89)
            rank = 'B';
        else if(score >=70 && score <=79)
            rank = 'C';
        else if(score >=60 && score <=69)
            rank = 'D';
        else if(score >=50 && score <=59)
            rank = 'F';
        else {
            rank = 'N';
        }
        return rank;
    }
}

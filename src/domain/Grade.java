package domain;

public class Grade {
    // 점수 안에 회차, 점수, 등급이 설정되어야한다.
    // 점수가 들어오면 등급을 설정할 수 있게 만들어야한다.
    private final CourseType type;          // 과목 타입 -> 타입별로 등급이 정해지는 기준이 다름
    private int score;                      // 점수
    private char rank;                      // 등급

    public Grade(int score,CourseType type) {
        this.score = score;
        this.type = type;
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
    }

}

package domain.course;

import domain.Grade;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private final long idNumber;
    private String courseName;
    private CourseType type;
    private List<Grade> grades = new ArrayList<>();

    public Course(long idNumber, String courseName, CourseType type) {
        this.idNumber = idNumber;
        this.courseName = courseName;
        this.type = type;

    }

    // 시험 횟수가 10회이면 true, 아니라면 false 반환
    public boolean fulfillSize() {
        return grades.size() == 10;
    }

    public long getIdNumber() {
        return idNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public CourseType getType() {
        return type;
    }

    // 회차에 대한 정보를 반환
    public List<Grade> getGrades() {
        return grades;
    }

    public int getTurn() {
        return grades.size();
    }

    public int getRoundScore(int round){
        return grades.get(round).getScore();
    }

    public char getRoundRank(int round){
        return grades.get(round).getRank();
    }

    public void insertScore(int score) {
        grades.add(new Grade(score,this.type));
    }

    // 시험 본 평균 점수를 반환
    public double avgScore() {
        if(grades.isEmpty())
            return 0;

        return (double)totalScore() / grades.size();
    }

    // 시험 본 전체 점수를 반환
    public int totalScore() {
        int total = 0;

        for(Grade grade : grades)
            total += grade.getScore();

        return total;
    }
}

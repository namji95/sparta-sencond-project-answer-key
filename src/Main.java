import domain.*;
import java.util.*;
import score_avg.*;

import static domain.CourseType.MANDATORY;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        Grade g10 = new Grade(80, MANDATORY);
        Grade g8 = new Grade(80, MANDATORY);
        Grade g9 = new Grade(80, MANDATORY);
        Grade g7 = new Grade(90, MANDATORY);
        Grade g6 = new Grade(90, MANDATORY);
        Grade g5 = new Grade(80, MANDATORY);
        Grade g4 = new Grade(10, MANDATORY);
        Grade g3 = new Grade(40, MANDATORY);
        Grade g2 = new Grade(50, MANDATORY);
        Grade g1 = new Grade(70, MANDATORY);

        List<Grade> li = new ArrayList<Grade>();
        List<Grade> li2 = new ArrayList<Grade>();
        li.add(g1);
        li.add(g2);
        li.add(g3);
        li.add(g4);
        li.add(g5);
        li.add(g6);
        li.add(g7);
        li.add(g8);
        li.add(g9);
        li.add(g10);

        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("6");
        a.add("2");
        a.add("3");
        a.add("4");
        a.add("5");
        String ans = "";
        for(int i=0;i<a.size();i++){
            ans = ans+a.get(i)+",";
        }
        System.out.println(ans);
        ans = ans.substring(0,ans.length()-1);
        System.out.println(ans);
        Course c1 = new Course(1, "Java", MANDATORY);
        Course c4 = new Course(1, "Java", MANDATORY);
        c4.setGrades(li2);
        c1.setGrades(li);
        Course c2 = new Course(2, "Spring", MANDATORY);
        c2.setGrades(li);
        Course c3 = new Course(3, "JPA", MANDATORY);
        List<Course> l = new ArrayList<Course>();
        l.add(c1);
        l.add(c2);
        l.add(c3);
        List<Course> l2 = new ArrayList<Course>();
        l2.add(c4);
        Student s1 = new Student(1,"실험체1",l);
        Student s2 = new Student(2,"실험체2",l2);
        List<Student> ss = new ArrayList<Student>();
        ss.add(s1);
        avg_calculation avv = new avg_calculation();
        student_choose_rank av = new student_choose_rank();
        avv.avg_screen(ss);
        StudentData.getInstance();
    }
}
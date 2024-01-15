package service;

import console.Console;
import domain.Student;
import domain.StudentData;

import java.util.List;

public class Delete {

    public void deleteStudent(List<Student> students) {
        System.out.println("\n 삭제할 수강생 번호를 입력하세요");
        long accountId = Console.inputInt();
        Student foundStudent = null;

        for(Student student : students) {
            if(student.getAccountId() == accountId ) {
                foundStudent = student;
                break;
            }
        }

        if (foundStudent != null) {
            students.remove(foundStudent);
            System.out.println("학생 정보가 삭제되었습니다.");
        } else {
            System.out.println("학생 정보가 없습니다. 다시 입력해주세요.");
        }


    }
}

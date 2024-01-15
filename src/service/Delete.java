package service;

import console.Console;
import domain.Student;
import domain.StudentData;
import invalidate.Invalidate;

public class Delete {

    public void deleteStudent(StudentData studentData) {
        while(true) {
            System.out.print("\n삭제할 수강생 번호를 입력하세요: ");
            String number = Console.inputString();
            System.out.println("수강생이 있는지 조회하고 있습니다...");

            if(!Invalidate.invalidateNumber(number) || Invalidate.invalidateStudent(number))
                continue;

            Student removeStudent = studentData.getStudentInfo(Long.parseLong(number));
            studentData.getStudents().remove(removeStudent);
            System.out.println("학생 정보가 삭제되었습니다!\n");
            break;
        }
    }
}

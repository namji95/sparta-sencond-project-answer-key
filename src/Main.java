import static domain.StudentCreateRead.StudentCreate;

public class Main {
    public static void main(String[] args) {
        StudentCreate();
        Management management = new Management();
        management.run();
        management.end();
    }
}
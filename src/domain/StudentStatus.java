package domain;

public enum StudentStatus {
    GREEN("매우 기분좋음"),
    YELLOW("평범함"),
    RED("매우 심기불편");
    private final String status;

    StudentStatus(String status) {
        this.status = status;
    }

    public String getStatus(){
        return  status;
    }

    public static StudentStatus getStudentStatus(String name) {
        long count = 1;
        for (StudentStatus studentStatus : StudentStatus.values()) {
            if (studentStatus.getStatus().equals(name))
                return studentStatus;

            count++;
        }
        throw new IllegalArgumentException();
    }
}
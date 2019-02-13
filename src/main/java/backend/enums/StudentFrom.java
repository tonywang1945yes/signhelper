package backend.enums;

public enum  StudentFrom {
    JUNIOR_PASSED(0),
    JUNIOR_FAILED(1),
    JUNIOR_ALL(2),
    SENIOR_PASSED(3),
    SENIOR_FAILED(4),
    SENIOR_ALL(5);


    private int state;

    StudentFrom(int state){
        this.state=state;
    }

    public int getState() {
        return state;
    }
}

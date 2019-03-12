package backend.parameter.emailVerification;

public class StuState {
    private int state;

    public int getFrom() {
        return state;
    }

    private void setState(int state) {
        this.state = state;
    }
}

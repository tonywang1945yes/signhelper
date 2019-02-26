package backend.parameter.SetMessage;


import backend.enums.StudentFrom;
import backend.enums.StudentState;

import static backend.enums.StudentState.*;
import static backend.enums.StudentState.JUNIOR_PASSED;

public class MessageParam {
    public String message;
    public int from;
    public StudentState state;


    public void setMessage(String message, int from) {
        this.message = message;
        this.from=from;
        setState();
    }

    public String getMessage() {
        return message;
    }

    public StudentState getState() {
        return state;
    }

    public void setState() {
        switch (from) {
            case 0:
                state = JUNIOR_PASSED;
                break;
            case 1:
                state = JUNIOR_FAILED;
                break;
            case 2:
                state = NULL;
                break;
            case 3:
                state = SENIOR_PASSED;
                break;
            case 4:
                state = SENIOR_FAILED;
                break;
            case 5:
                state = JUNIOR_PASSED;
                break;
        }
    }
}

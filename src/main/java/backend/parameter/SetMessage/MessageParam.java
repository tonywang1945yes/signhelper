package backend.parameter.SetMessage;


import backend.enums.StudentFrom;
import backend.enums.StudentState;

import static backend.enums.StudentState.*;
import static backend.enums.StudentState.JUNIOR_PASSED;

public class MessageParam {
    public String message;
    public int from;
//    public StudentState state;


//    public void setMessage(String message, int from) {
//        this.message = message;
//        this.from=from;
////        setState();
//    }

    public String getMessage() {
        return message;
    }

//    public StudentState getState() {
//        return state;
//    }

//    public void setState() {

//    }
}

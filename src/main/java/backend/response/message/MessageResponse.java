package backend.response.message;

public class MessageResponse {
    public String message;
    public boolean succ;
    public int from;
    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSucc() {
        return succ;
    }

    public int getFrom() {
        return from;
    }

    public void setSucc(boolean succ) {
        this.succ = succ;
    }

    public void setFrom(int from) {
        this.from = from;
    }
}

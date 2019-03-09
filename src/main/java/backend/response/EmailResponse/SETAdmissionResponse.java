package backend.response.EmailResponse;

public class SETAdmissionResponse {
    public String email;
    public String admission;

    public String getEmail() {
        return email;
    }

    public String getAdmission() {
        return admission;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    public SETAdmissionResponse(String email, String admission) {
        this.email = email;
        this.admission = admission;
    }
    public SETAdmissionResponse() {
    }
}

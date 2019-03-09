package backend.entity.application;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class PhoneNumbers {
    @Column(name = "home_phone_number", length = 50)
    private String homePhoneNumber;

    @Column(name = "mobile_phone_number", length = 50)
    private String mobilePhoneNumber;

    @Column(name = "fax_number", length = 50)
    private String faxNumber;
	

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String toString(){
        return homePhoneNumber + " / " + mobilePhoneNumber + " / " + faxNumber;
    }

    public PhoneNumbers() {
    }

    public PhoneNumbers(String homePhoneNumber, String mobilePhoneNumber, String faxNumber) {
        this.homePhoneNumber = homePhoneNumber;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.faxNumber = faxNumber;
    }
}

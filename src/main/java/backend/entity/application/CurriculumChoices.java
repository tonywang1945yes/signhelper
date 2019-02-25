package backend.entity.application;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class CurriculumChoices {
    private String firstChoice;

    private String secondChoice;

    private String thirdChoice;

    private String fourthChoice;

    public String getFirstChoice() {
        return firstChoice;
    }

    public void setFirstChoice(String firstChoice) {
        this.firstChoice = firstChoice;
    }

    public String getSecondChoice() {
        return secondChoice;
    }

    public void setSecondChoice(String secondChoice) {
        this.secondChoice = secondChoice;
    }

    public String getThirdChoice() {
        return thirdChoice;
    }

    public void setThirdChoice(String thirdChoice) {
        this.thirdChoice = thirdChoice;
    }

    public String getFourthChoice() {
        return fourthChoice;
    }

    public void setFourthChoice(String fourthChoice) {
        this.fourthChoice = fourthChoice;
    }

    public String toString(){
        return firstChoice + " / " + secondChoice + " / " + thirdChoice + " / " + firstChoice;
    }
}

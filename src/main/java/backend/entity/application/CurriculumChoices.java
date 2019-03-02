package backend.entity.application;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CurriculumChoices {
    @Column(length = 50)
    private String firstChoice;

    @Column(length = 50)
    private String secondChoice;

    @Column(length = 50)
    private String thirdChoice;

    @Column(length = 50)
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

    public CurriculumChoices(){
    }

    public CurriculumChoices(String firstChoice, String secondChoice, String thirdChoice, String fourthChoice) {
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
        this.thirdChoice = thirdChoice;
        this.fourthChoice = fourthChoice;
    }
}

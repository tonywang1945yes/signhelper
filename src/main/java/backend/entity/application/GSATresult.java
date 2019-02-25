package backend.entity.application;

import javax.persistence.Embeddable;

@Embeddable
public class GSATresult {
    private Integer chinese;
    private Integer math;
    private Integer english;
    private Integer socials;
    private Integer sciences;

    public Integer getChinese() {
        return chinese;
    }

    public void setChinese(Integer chinese) {
        this.chinese = chinese;
    }

    public Integer getMath() {
        return math;
    }

    public void setMath(Integer math) {
        this.math = math;
    }

    public Integer getEnglish() {
        return english;
    }

    public void setEnglish(Integer english) {
        this.english = english;
    }

    public Integer getSocials() {
        return socials;
    }

    public void setSocials(Integer socialogy) {
        this.socials = socialogy;
    }

    public Integer getSciences() {
        return sciences;
    }

    public void setSciences(Integer sciences) {
        this.sciences = sciences;
    }

    public String toString(){
        return chinese + " / " + math + " / " + english + " / " + socials + " / " + sciences;
    }
}

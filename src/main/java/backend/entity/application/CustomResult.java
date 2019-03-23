package backend.entity.application;

import javax.persistence.Embeddable;

import static backend.util.PdfUtil.EngToHan.switchToChinese;

@Embeddable
public class CustomResult<T> {
    private T chinese;
    private T math;
    private T english;
    private T socials;
    private T sciences;

    public T getChinese() {
        return chinese;
    }

    public void setChinese(T chinese) {
        this.chinese = chinese;
    }

    public T getMath() {
        return math;
    }

    public void setMath(T math) {
        this.math = math;
    }

    public T getEnglish() {
        return english;
    }

    public void setEnglish(T english) {
        this.english = english;
    }

    public T getSocials() {
        return socials;
    }

    public void setSocials(T socials) {
        this.socials = socials;
    }

    public T getSciences() {
        return sciences;
    }

    public void setSciences(T sciences) {
        this.sciences = sciences;
    }
    
    public CustomResult() {
    }

    public CustomResult(T chinese, T math, T english, T socials, T sciences) {
        this.chinese = chinese;
        this.math = math;
        this.english = english;
        this.socials = socials;
        this.sciences = sciences;
    }

    @Override
    public String toString() {
        return chinese.toString() + " / "
                + math.toString() + " / "
                + english.toString() + " / "
                + socials.toString() + " / "
                + sciences.toString();
    }
}

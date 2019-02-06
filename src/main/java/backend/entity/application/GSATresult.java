package backend.entity.application;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class GSATresult {
    @NotNull
    private Integer chinese;
    @NotNull
    private Integer math;
    @NotNull
    private Integer english;
    @NotNull
    private Integer socialogy;
    @NotNull
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

    public Integer getSocialogy() {
        return socialogy;
    }

    public void setSocialogy(Integer socialogy) {
        this.socialogy = socialogy;
    }

    public Integer getSciences() {
        return sciences;
    }

    public void setSciences(Integer sciences) {
        this.sciences = sciences;
    }
}

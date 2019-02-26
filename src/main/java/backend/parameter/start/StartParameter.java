package backend.parameter.start;

import java.util.Calendar;
import java.util.Map;

public class StartParameter {

    private Long id;

    private String name = "";

    private Integer stuNum = 0;

    private Boolean acceptArt;

    private Integer time;

    private String college;

    private Integer price;

    private String comment;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getStuNum() {
        return stuNum;
    }

    public Boolean getAcceptArt() {
        return acceptArt;
    }

    public Integer getTime() {
        return time;
    }

    public String getCollege() {
        return college;
    }

    public Integer getPrice() {
        return price;
    }

    public String getComment() {
        return comment;
    }

    public StartParameter() {
    }
}

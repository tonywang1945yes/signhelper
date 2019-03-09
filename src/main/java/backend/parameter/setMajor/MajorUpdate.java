package backend.parameter.setMajor;

public class MajorUpdate {
    private int majorid;
    private Integer majorNum;

    private String name;

    private Boolean acceptArt;

    private Integer time;

    private String college;

    private String comment;

    public int getId() {
        return majorid;
    }

    public String getName() {
        return name;
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

    public String getComment() {
        return comment;
    }
}

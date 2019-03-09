package backend.parameter.setMajor;

public class UpdateMajorParam {
    private int majorid = 0;

    private String name = "";

    private Boolean acceptArt = true;

    private Integer time = 0;

    private String college = "";

    private String comment = "";

    public int getMajorid() {
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

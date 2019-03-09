package backend.response.message;

import java.util.Calendar;

public class DDL {
    public Calendar ddl ;
    public boolean succ;
    public String msg;

    public void setDdl(Calendar ddl) {
        this.ddl = ddl;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

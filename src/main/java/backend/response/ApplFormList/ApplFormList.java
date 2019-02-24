package backend.response.ApplFormList;

import backend.entity.application.ApplForm;

import java.util.List;

public class ApplFormList {
    private List<ApplForm> list;
    private int total;

    public List<ApplForm> getList() {
        return list;
    }

    public void setList(List<ApplForm> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ApplFormList(List<ApplForm> list){
        this.setList(list);
        this.setTotal(list.size());
    }
}

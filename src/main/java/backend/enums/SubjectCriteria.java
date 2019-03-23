package backend.enums;

public enum SubjectCriteria {
    TOP_CRITERIA, HEAD_CRITERIA, AVERAGE_CRITERIA, BACK_CRITERIA, BOTTOM_CRITERIA;
    //頂標，前標，均標，後標，底標


    @Override
    public String toString() {
        switch (this){
            case TOP_CRITERIA:
                return "顶标";
            case BACK_CRITERIA:
                return "后标";
            case HEAD_CRITERIA:
                return "前标";
            case BOTTOM_CRITERIA:
                return "底标";
            case AVERAGE_CRITERIA:
                return "均标";
        }
        return "";
    }
}

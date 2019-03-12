package backend.util.PdfUtil;

import backend.enums.SubjectCriteria;

//    TOP_CRITERIA, HEAD_CRITERIA, AVERAGE_CRITERIA, BACK_CRITERIA, BOTTOM_CRITERIA
//    //頂標，前標，均標，後標，底標
public class EngToHan {
    public static String switchToChinese(SubjectCriteria tochange){
        switch (tochange){
            case TOP_CRITERIA:return new String("頂標");
            case HEAD_CRITERIA:return new String("前標");
            case AVERAGE_CRITERIA:return new String("均標");
            case BACK_CRITERIA:return new String("後標");
            case BOTTOM_CRITERIA:return new String("底標");
        }
        return null;
    }
}

package backend.util.PdfUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import backend.entity.application.ApplForm;
import backend.entity.application.CurriculumChoices;
import backend.entity.application.CustomResult;
import backend.enums.SubjectCriteria;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CreatePdfFile {

    @Value("${savingPdfPath}")
    String path;

    public void create(ApplForm applForm,String imagepath) throws FileNotFoundException,IOException,DocumentException{
        final String image_path = imagepath;
        final String path = this.path + "/"+applForm.getFirstName()+applForm.getLastName()+applForm.getIdentityNum() + ".pdf";

            Document document = new Document();
            document.setPageSize(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            BaseFont bfChinese = BaseFont.createFont( "STSongStd-Light" ,"UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);

//            标题字体
            Font titleFont = new Font(bfChinese, 36, Font.BOLD);
            titleFont.setColor(BaseColor.BLACK);

//            正文字体
            Font textFont = new Font(bfChinese, 20);
            textFont.setColor(BaseColor.BLACK);

//            注释字体
            Font infoFont = new Font(bfChinese, 15, Font.BOLD);
            infoFont.setColor(BaseColor.BLACK);

//            重要注释字体
            Font basicInfoFont = new Font(bfChinese, 20, Font.BOLD);
            infoFont.setColor(BaseColor.BLACK);

//            非重要注释字体
            Font nonBasicInfoFont = new Font(bfChinese, 15);
            infoFont.setColor(BaseColor.BLACK);


//            标题
            PdfPTable titleTable = new PdfPTable(1);
            titleTable.setWidthPercentage(100);
            float[] titleWidths = new float[]{2.6f};
            titleTable.setWidths(titleWidths);
            PdfPCell cell;

//            空行
            cell = new PdfPCell(new Phrase("", titleFont));
            cell.setMinimumHeight(40);
            cell.setBorder(0);
            titleTable.addCell(cell);

//            第一行
            cell = new PdfPCell(new Phrase("台湾免试生信息确认书", titleFont));
            cell.setMinimumHeight(40);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            cell.setBorder(0);
            titleTable.addCell(cell);

//            空行
            cell = new PdfPCell(new Phrase("", titleFont));
            cell.setMinimumHeight(20);
            cell.setBorder(0);
            titleTable.addCell(cell);

            document.add(titleTable);

            PdfPTable basicInfoTable = new PdfPTable(6);
            basicInfoTable.setWidthPercentage(80);
            float[] basicInfoWidths = new float[]{0.6f, 0.6f, 0.2f, 0.3f, 0.1f, 0.8f};
            basicInfoTable.setWidths(basicInfoWidths);

//            第一行
//            姓名
            cell = new PdfPCell(new Phrase("姓名：", infoFont));
            cell.setMinimumHeight(25);
            cell.setBorder(0);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_TOP); // 设置垂直居中
            cell.setColspan(2);
            basicInfoTable.addCell(cell);


//            性别
            cell = new PdfPCell(new Phrase("性别：", infoFont));
            cell.setMinimumHeight(25);
            cell.setBorder(0);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_TOP); // 设置垂直居中
            cell.setColspan(3);
            basicInfoTable.addCell(cell);

//            照片
            try {
                Image image = Image.getInstance(image_path);
                image.scaleAbsolute(120f, 155f);
                cell = new PdfPCell(image, false);
                cell.setMinimumHeight(25);
                cell.setUseAscender(true); // 设置可以居中
                cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平中对齐
                cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
                cell.setRowspan(8);
                basicInfoTable.addCell(cell);
            }catch (FileNotFoundException e){
                cell = new PdfPCell();
                cell.setMinimumHeight(25);
                cell.setUseAscender(true); // 设置可以居中
                cell.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平左对齐
                cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
                cell.setRowspan(8);
                basicInfoTable.addCell(cell);
            }

//            第二行
//            姓名
            cell = new PdfPCell(new Phrase(applForm.getFirstName() + " " + applForm.getLastName(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            basicInfoTable.addCell(cell);

//            GAP
            cell = new PdfPCell();
            cell.setMinimumHeight(25);
            cell.setBorder(0);
            basicInfoTable.addCell(cell);

//            性别
            String sex = "";
            if (applForm.getSex() == 1){
                sex = "男";
            }
            else {
                sex = "女";
            }
            cell = new PdfPCell(new Phrase(sex, textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            cell.setColspan(2);
            basicInfoTable.addCell(cell);

//            GAP
            cell = new PdfPCell();
            cell.setMinimumHeight(25);
            cell.setBorder(0);
            basicInfoTable.addCell(cell);

//            空行
            cell = new PdfPCell();
            cell.setMinimumHeight(10);
            cell.setBorder(0);
            cell.setColspan(5);
            basicInfoTable.addCell(cell);

//            第三行
//            出生日期
            cell = new PdfPCell(new Phrase("出生日期:", infoFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_TOP); // 设置垂直居中
            cell.setBorder(0);
            cell.setColspan(5);
            basicInfoTable.addCell(cell);

//            第四行
//            出生日期
            Calendar date = applForm.getBirthDate();

            cell = new PdfPCell(new Phrase(date.get(Calendar.YEAR) + "-" + date.get(Calendar.MONTH) + "-" + date.get(Calendar.DATE), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            cell.setColspan(2);
            basicInfoTable.addCell(cell);

//            GAP
            cell = new PdfPCell();
            cell.setMinimumHeight(25);
            cell.setBorder(0);
            cell.setColspan(3);
            basicInfoTable.addCell(cell);

//            空行
            cell = new PdfPCell();
            cell.setMinimumHeight(10);
            cell.setBorder(0);
            cell.setColspan(5);
            basicInfoTable.addCell(cell);

//            第五行
//            身份证号
            cell = new PdfPCell(new Phrase("身份证号:", infoFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_TOP); // 设置垂直居中
            cell.setBorder(0);
            cell.setColspan(5);
            basicInfoTable.addCell(cell);

//            第六行
//            身份证号
            cell = new PdfPCell(new Phrase(applForm.getIdentityNum(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            cell.setColspan(2);
            basicInfoTable.addCell(cell);

//            GAP
            cell = new PdfPCell();
            cell.setMinimumHeight(25);
            cell.setBorder(0);
            cell.setColspan(3);
            basicInfoTable.addCell(cell);

//            空行
            cell = new PdfPCell();
            cell.setMinimumHeight(10);
            cell.setBorder(0);
            cell.setColspan(6);
            basicInfoTable.addCell(cell);

//            第七行
//            通行证号
            cell = new PdfPCell(new Phrase("通行证号：", infoFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_TOP); // 设置垂直居中
            cell.setColspan(3);
            cell.setBorder(0);
            basicInfoTable.addCell(cell);

//            就读高中
            cell = new PdfPCell(new Phrase("就读高中：", infoFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_TOP); // 设置垂直居中
            cell.setBorder(0);
            cell.setColspan(3);
            basicInfoTable.addCell(cell);

//            第八行
//            通行证号
            cell = new PdfPCell(new Phrase(applForm.getVisaNum(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            cell.setColspan(2);
            basicInfoTable.addCell(cell);

//            GAP
            cell = new PdfPCell();
            cell.setMinimumHeight(25);
            cell.setBorder(0);
            basicInfoTable.addCell(cell);

//            就读高中
            cell = new PdfPCell(new Phrase(applForm.getHighSchool(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            cell.setColspan(3);
            basicInfoTable.addCell(cell);

            document.add(basicInfoTable);

            PdfPTable scoreTable = new PdfPTable(6);
            titleTable.setWidthPercentage(80);
            float[] scoreWidths = new float[]{0.3f, 0.6f, 0.6f, 0.6f, 0.6f, 0.6f};
            scoreTable.setWidths(scoreWidths);

//            空行
            cell = new PdfPCell();
            cell.setMinimumHeight(30);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_MIDDLE); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            cell.setColspan(6);
            cell.setBorder(0);
            scoreTable.addCell(cell);

//            主表头
            cell = new PdfPCell(new Phrase("成绩信息", basicInfoFont));
            cell.setMinimumHeight(35);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            cell.setColspan(6);
            cell.setBorderWidthTop(0);
            cell.setBorderWidthLeft(0);
            cell.setBorderWidthRight(0);
            scoreTable.addCell(cell);

//            空行
            cell = new PdfPCell();
            cell.setMinimumHeight(2);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_MIDDLE); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            cell.setColspan(6);
            cell.setBorder(0);
            scoreTable.addCell(cell);


//            空行
            cell = new PdfPCell();
            cell.setMinimumHeight(5);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_MIDDLE); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            cell.setColspan(6);
            cell.setBorderWidthBottom(0);
            cell.setBorderWidthLeft(0);
            cell.setBorderWidthRight(0);
            scoreTable.addCell(cell);

//            次表头

            cell = new PdfPCell();
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase("国文", nonBasicInfoFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase("数学", nonBasicInfoFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中

            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase("英文", nonBasicInfoFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase("自然", nonBasicInfoFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase("社会", nonBasicInfoFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            scoreTable.addCell(cell);

//            成绩
            cell = new PdfPCell(new Phrase("成绩", nonBasicInfoFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            scoreTable.addCell(cell);

            CustomResult<Double> scores = applForm.getResults();

            cell = new PdfPCell(new Phrase(scores.getChinese().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中
            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase(scores.getMath().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中

            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase(scores.getEnglish().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中
            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase(scores.getSciences().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中
            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase(scores.getSocials().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中
            scoreTable.addCell(cell);

//            实得级分
            cell = new PdfPCell(new Phrase("实得级分", nonBasicInfoFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            scoreTable.addCell(cell);

            CustomResult<Integer> actualLevelPoints = applForm.getActualLevelPoints();

            cell = new PdfPCell(new Phrase(actualLevelPoints.getChinese().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中
            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase(actualLevelPoints.getMath().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中

            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase(actualLevelPoints.getEnglish().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中
            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase(actualLevelPoints.getSciences().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中
            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase(actualLevelPoints.getSocials().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中
            scoreTable.addCell(cell);

//            级距
            cell = new PdfPCell(new Phrase("级距", nonBasicInfoFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            scoreTable.addCell(cell);

            CustomResult<Double> levelRange = applForm.getLevelRange();


            cell = new PdfPCell(new Phrase(levelRange.getChinese().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中
            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase(levelRange.getMath().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中

            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase(levelRange.getEnglish().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中
            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase(levelRange.getSciences().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中
            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase(levelRange.getSocials().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中
            scoreTable.addCell(cell);


//            单科标准
            cell = new PdfPCell(new Phrase("单科标准", nonBasicInfoFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            scoreTable.addCell(cell);

            CustomResult<SubjectCriteria> singleSubjectCriteria = applForm.getSingleSubjectCriteria();


            cell = new PdfPCell(new Phrase(singleSubjectCriteria.getChinese().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中
            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase(singleSubjectCriteria.getMath().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中

            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase(singleSubjectCriteria.getEnglish().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中
            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase(singleSubjectCriteria.getSciences().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中
            scoreTable.addCell(cell);

            cell = new PdfPCell(new Phrase(singleSubjectCriteria.getSocials().toString(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直居中
            scoreTable.addCell(cell);


            document.add(scoreTable);

            PdfPTable curriculumTable = new PdfPTable(4);
            curriculumTable.setWidthPercentage(80);
            float[] curriculumWidths = new float[]{0.6f, 0.6f, 0.6f, 0.6f};
            curriculumTable.setWidths(curriculumWidths);

            CurriculumChoices curriculums = applForm.getCurriculumChoices();

//            空行
            cell = new PdfPCell();
            cell.setMinimumHeight(30);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_MIDDLE); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            cell.setColspan(4);
            cell.setBorder(0);
            curriculumTable.addCell(cell);

//            主表头
            cell = new PdfPCell(new Phrase("志愿信息", basicInfoFont));
            cell.setMinimumHeight(35);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            cell.setColspan(4);
            cell.setBorderWidthTop(0);
            cell.setBorderWidthLeft(0);
            cell.setBorderWidthRight(0);
            curriculumTable.addCell(cell);

//            空行
            cell = new PdfPCell();
            cell.setMinimumHeight(2);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_MIDDLE); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            cell.setColspan(4);
            cell.setBorder(0);
            curriculumTable.addCell(cell);


//            空行
            cell = new PdfPCell();
            cell.setMinimumHeight(5);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_MIDDLE); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            cell.setColspan(4);
            cell.setBorderWidthBottom(0);
            cell.setBorderWidthLeft(0);
            cell.setBorderWidthRight(0);
            curriculumTable.addCell(cell);

            cell = new PdfPCell(new Phrase("第一志愿", nonBasicInfoFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            curriculumTable.addCell(cell);

            cell = new PdfPCell(new Phrase("第二志愿", nonBasicInfoFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            curriculumTable.addCell(cell);

            cell = new PdfPCell(new Phrase("第三志愿", nonBasicInfoFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            curriculumTable.addCell(cell);

            cell = new PdfPCell(new Phrase("第四志愿", nonBasicInfoFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            curriculumTable.addCell(cell);

            cell = new PdfPCell(new Phrase(curriculums.getFirstChoice(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            curriculumTable.addCell(cell);

            cell = new PdfPCell(new Phrase(curriculums.getSecondChoice(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            curriculumTable.addCell(cell);

            cell = new PdfPCell(new Phrase(curriculums.getThirdChoice(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            curriculumTable.addCell(cell);

            cell = new PdfPCell(new Phrase(curriculums.getFourthChoice(), textFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            curriculumTable.addCell(cell);

            document.add(curriculumTable);

            PdfPTable signTable = new PdfPTable(2);
            signTable.setWidthPercentage(80);
            float[] signWidths = new float[]{0.6f, 0.4f};
            signTable.setWidths(signWidths);

//            空行
            cell = new PdfPCell();
            cell.setMinimumHeight(60);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_MIDDLE); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            cell.setColspan(2);
            cell.setBorder(0);
            signTable.addCell(cell);

//            GAP
            cell = new PdfPCell();
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_MIDDLE); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
            cell.setBorder(0);
            cell.setRowspan(2);
            signTable.addCell(cell);

//            签名
            cell = new PdfPCell(new Phrase("本人签字：", basicInfoFont));
            cell.setMinimumHeight(25);
            cell.setUseAscender(true); // 设置可以居中
            cell.setHorizontalAlignment(Cell.ALIGN_MIDDLE); // 设置水平左对齐
            cell.setVerticalAlignment(Cell.ALIGN_TOP); // 设置垂直居中
            cell.setBorder(0);
            signTable.addCell(cell);

            cell = new PdfPCell();
            cell.setMinimumHeight(50);
            signTable.addCell(cell);

            document.add(signTable);

            document.close();
            writer.close();

    }
}

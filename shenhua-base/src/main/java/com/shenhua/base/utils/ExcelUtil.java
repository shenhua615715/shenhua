package com.shenhua.base.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:生成excel文件
 * @Author: liuye
 * @Date: 2019/11/15 17:58
 */
/**
 * 记录一个异常 java.io.FileNotFoundException:
 * C:\Users\javaloveiphone\Desktop\example.xls (另一个程序正在使用此文件，进程无法访问。) at
 * java.io.FileOutputStream.open(Native Method) at
 * java.io.FileOutputStream.<init>(FileOutputStream.java:194) at
 * java.io.FileOutputStream.<init>(FileOutputStream.java:84) at
 * com.write.excel.WriteExcel.writeExcel(WriteExcel.java:45) at
 * com.write.excel.WriteExcel.main(WriteExcel.java:148)
 */

public class ExcelUtil {

    private final static String excel2003L = ".xls";    //2003- 版本的excel
    private final static String excel2007U = ".xlsx";   //2007+ 版本的excel

    //格式化日期
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 标题字体
    private HSSFFont titleFont = null;
    private XSSFFont title2007Font = null; //2007格式

    // 标题样式
    private HSSFCellStyle titleStyle = null;
    private XSSFCellStyle title2007Style = null;//2007格式

    // 行信息内容样式
    private HSSFCellStyle contentStyle = null;
    private XSSFCellStyle content2007Style = null;//2007格式

    /** 样式初始化 */
    public void setExcelStyle(HSSFWorkbook workBook) {
        // 设置列标题字体，样式
        titleFont = workBook.createFont();
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 标题列样式
        titleStyle = workBook.createCellStyle();
        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 设置边框
        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleStyle.setAlignment(XSSFCellStyle.VERTICAL_CENTER);
        titleStyle.setFont(titleFont);
        // 内容列样式
        contentStyle = workBook.createCellStyle();
        contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        contentStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
    }

    /** 2007样式初始化 */
    public void set2007ExcelStyle(XSSFWorkbook workBook) {
        // 设置列标题字体，样式
        title2007Font = workBook.createFont();
        title2007Font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 标题列样式
        title2007Style = workBook.createCellStyle();
        title2007Style.setBorderTop(HSSFCellStyle.BORDER_THIN); // 设置边框
        title2007Style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        title2007Style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        title2007Style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        title2007Style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        title2007Style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        title2007Style.setFont(titleFont);
        // 内容列样式
        content2007Style = workBook.createCellStyle();
        content2007Style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        content2007Style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        content2007Style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        content2007Style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        content2007Style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        content2007Style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
    }

    /** 判断数据类型 */
    public String getValue(Object value) {
        String textValue = "";
        if (value == null)
            return textValue;

        if (value instanceof Boolean) {
            boolean bValue = (Boolean) value;
            textValue = "是";
            if (!bValue) {
                textValue = "否";
            }
        } else if (value instanceof Date) {
            Date date = (Date) value;

            textValue = sdf.format(date);
        } else
            textValue = value.toString();

        return textValue;
    }

    /**
     * 读excel文件
     * @param file CommonsMultipartFile
     * @return List<List<Object>>对象
     * @throws Exception
     */
    public static List<List<Object>> readList(CommonsMultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("上传文件不存在！");
        }
        InputStream in = file.getInputStream();
        String fileName = file.getOriginalFilename();
        return readList(in, fileName,-1);
    }
    /**
     * 读excel文件
     * @param file CommonsMultipartFile
     * @return List<List<Object>>对象
     * @throws Exception
     */
    public static List<List<Object>> readList(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("上传文件不存在！");
        }
        InputStream in = file.getInputStream();
        String fileName = file.getOriginalFilename();
        return readList(in, fileName,-1);
    }

    /**
     * 读excel文件
     * @param file CommonsMultipartFile
     * @return List<List<Object>>对象
     * @throws Exception
     */
    public static List<List<Object>> readListbyIndex(MultipartFile file, int sheetIdx) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("上传文件不存在！");
        }
        InputStream in = file.getInputStream();
        String fileName = file.getOriginalFilename();
        return readList(in, fileName,sheetIdx);
    }
    public static List<List<Object>> readList(MultipartFile file, int sheetIdx) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("上传文件不存在！");
        }
        InputStream in = file.getInputStream();
        String fileName = file.getOriginalFilename();
        return readList(in, fileName,sheetIdx);
    }

    /**
     * 读取IO流中的数据，组装成List<List<Object>>对象
     *
     * @param in,fileName
     * @return List<List<Object>>对象
     * @throws Exception
     */
    public static List<List<Object>> readList(InputStream in, String fileName,int sheetIdx) throws Exception {
        List<List<Object>> list = null;

        //创建Excel工作薄
        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        list = new ArrayList<List<Object>>();
        //遍历Excel中所有的sheet
        if(sheetIdx == -1){
            for (int i = 0; i < work.getNumberOfSheets(); i++) {
                sheet = work.getSheetAt(i);
                if (sheet == null) {
                    continue;
                }
                //遍历当前sheet中的所有行
                for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                    row = sheet.getRow(j);
                    if (row == null) {
                        continue;
                    }
                    //遍历所有的列
                    List<Object> li = new ArrayList<Object>();
                    for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                        cell = row.getCell(y);
                        li.add(getCellValue(cell));
                    }
                    list.add(li);
                }
            }
        }else{
            sheet = work.getSheetAt(sheetIdx);
            if (sheet != null) {
                //遍历当前sheet中的所有行
                for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                    row = sheet.getRow(j);
                    if (row == null) {
                        continue;
                    }
                    //遍历所有的列
                    List<Object> li = new ArrayList<Object>();
                    for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                        cell = row.getCell(y);
                        li.add(getCellValue(cell));
                    }
                    list.add(li);
                }
            }
        }
        work.close();
        return list;
    }

    /**
     * 根据文件后缀，自适应上传文件的版本
     *
     * @param inStr,fileName
     * @return Workbook
     * @throws Exception
     */
    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            wb = new HSSFWorkbook(inStr);  //2003-
        } else if (excel2007U.equals(fileType)) {
            wb = new XSSFWorkbook(inStr);  //2007+
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 对表格中数值进行格式化
     *
     * @param cell
     * @return
     */
    public static Object getCellValue(Cell cell) {
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        SimpleDateFormat sdfYMDHMS = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                System.out.println("cell.getCellStyle().getDataFormatString() = " + cell.getCellStyle().getDataFormatString());
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else if ("m/d/yy h:mm".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdfYMDHMS.format(cell.getDateCellValue());
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            case Cell.CELL_TYPE_FORMULA:
                try {
                    if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                        value = df.format(cell.getNumericCellValue());
                    } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                        value = sdf.format(cell.getDateCellValue());
                    } else if ("m/d/yy h:mm".equals(cell.getCellStyle().getDataFormatString())) {
                        value = sdfYMDHMS.format(cell.getDateCellValue());
                    } else {
                        value = df2.format(cell.getNumericCellValue());
                    }
                } catch (IllegalStateException e) {
                }
                break;
            default:
                break;
        }
        return value;
    }

    public HSSFFont getTitleFont() {
        return titleFont;
    }

    public void setTitleFont(HSSFFont titleFont) {
        this.titleFont = titleFont;
    }

    public XSSFFont getTitle2007Font() {
        return title2007Font;
    }

    public void setTitle2007Font(XSSFFont title2007Font) {
        this.title2007Font = title2007Font;
    }

    public HSSFCellStyle getTitleStyle() {
        return titleStyle;
    }

    public void setTitleStyle(HSSFCellStyle titleStyle) {
        this.titleStyle = titleStyle;
    }

    public XSSFCellStyle getTitle2007Style() {
        return title2007Style;
    }

    public void setTitle2007Style(XSSFCellStyle title2007Style) {
        this.title2007Style = title2007Style;
    }

    public HSSFCellStyle getContentStyle() {
        return contentStyle;
    }

    public void setContentStyle(HSSFCellStyle contentStyle) {
        this.contentStyle = contentStyle;
    }

    public XSSFCellStyle getContent2007Style() {
        return content2007Style;
    }

    public void setContent2007Style(XSSFCellStyle content2007Style) {
        this.content2007Style = content2007Style;
    }

    /** 测试 */
    public static void main(String[] args) {
        ExcelUtil we = new ExcelUtil();
    }
}

package com.test;

import com.test.entity.User;
import com.test.entity.Users;
import com.test.entity.WxzwZdyxqk;
import com.test.service.UserService;
import com.test.service.WxzwZdyxqkService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)//不加这个注解 userService 就无法注入
@SpringBootTest
public class PublicTest {

    @Autowired
    private UserService userService;

    @Autowired
    private WxzwZdyxqkService wxzwZdyxqkService;

    @Test
    public void test() {
        List<User> list = userService.selectAll();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("账号：" + list.get(i).getPassword() + "，密码：" + list.get(i).getPassword());
        }
    }

    @Test
    public void test01() {
        List<WxzwZdyxqk> list = wxzwZdyxqkService.queryAllByLimit(0,10);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getId() + ", " + list.get(i).getAreaName());
        }
    }

    //double 如何保留两位小数，不足位数补0，并且数据类型不变
    @Test
    public void test02() {
        double a = 56.8954654;
        DecimalFormat df = new DecimalFormat("#");
        String b = df.format(a);
        System.out.println("String -- b: " + b);
        double c = Double.parseDouble(b);
        System.out.println("double -- c: " + c);
    }

    @Test
    public void test03() {
        try {
            //设置输出到本地的excel文件的名字和路径
            FileOutputStream out = new FileOutputStream("f:\\test\\测试.xlsx");
            //生成excel文档对象
            HSSFWorkbook workBook = new HSSFWorkbook();
            //创建工作簿
            HSSFSheet mySheet = workBook.createSheet();
            //设置工作簿的名字
            workBook.setSheetName(0, "测试");
            //创建第一行，标题行
            int rowNomber=-1;
            HSSFRow myRow = mySheet.createRow(++rowNomber);
            HSSFCellStyle style = workBook.createCellStyle();
            style.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index); // 前景色设置 ①
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 填充模式 设置 ② 备注：① + ② 两步才能设置背景颜色
            //设置字体样式
            HSSFFont font = workBook.createFont();
            font.setFontName("宋体");
            font.setFontHeightInPoints((short) 10);
            style.setFont(font);

            //设置标题行，每一列的标题
            HSSFCell cell = myRow.createCell((short) 0);
            cell.setCellStyle(style);
            cell.setCellValue("姓名");

            cell = myRow.createCell((short) 1);
            cell.setCellStyle(style);
            cell.setCellValue("性别");

            cell = myRow.createCell((short) 2);
            cell.setCellStyle(style);
            cell.setCellValue("爱好");

            //自己造数据，将数据填充到excel中
            List<Users> users= new ArrayList<Users>();
            Users u = new Users();
            u.setHobby("登山");
            u.setName("Lisa");
            u.setSex("女");
            Users u1 =new Users();
            u1.setHobby("游泳");
            u1.setName("Tom");
            u1.setSex("男");
            Users u2 =new Users();
            u2.setHobby("看电影");
            u2.setName("Harry");
            u2.setSex("男");
            Users u3 =new Users();
            u3.setHobby("看电影");
            u3.setName("Tina");
            u3.setSex("男");
            Users u4 =new Users();
            u4.setHobby("跑步");
            u4.setName("obla");
            u4.setSex("男");
            users.add(u);
            users.add(u1);
            users.add(u2);
            users.add(u3);
            users.add(u4);

            System.out.println("==="+users.size());
            for(int i = 1; i <= users.size(); i++){
                //创建行
                HSSFRow  Row = mySheet.createRow(++rowNomber);
                Users user = users.get(i-1);
                //创建行中的列，并赋值
                HSSFCell  cellfirst = Row.createCell((short) 0);
                cellfirst.setCellValue(user.getName());
                HSSFCell  cellsecond  = Row.createCell((short) 1);
                cellsecond.setCellValue(user.getSex());
                HSSFCell  cellthrid = Row.createCell((short) 2);
                cellthrid.setCellValue(user.getHobby());
            }
            System.out.println("===1"+rowNomber);
            //写文件到本地
            workBook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void test04() {
//        HSSFWorkbook workbook = new HSSFWorkbook();// 创建一个Excel文件
//        HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet
//        sheet.createFreezePane(1, 3);// 冻结
//        // 设置列宽
//        sheet.setColumnWidth(0, 1000);
//        sheet.setColumnWidth(1, 3500);
//        sheet.setColumnWidth(2, 3500);
//        sheet.setColumnWidth(3, 6500);
//        sheet.setColumnWidth(4, 6500);
//        sheet.setColumnWidth(5, 6500);
//        sheet.setColumnWidth(6, 6500);
//        sheet.setColumnWidth(7, 2500);
//        // Sheet样式
//        HSSFCellStyle sheetStyle = workbook.createCellStyle();
//        // 背景色的设定
//        sheetStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
//        // 前景色的设定
//        sheetStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
//        // 填充模式
////        sheetStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);
//        sheetStyle.setFillPattern(FillPatternType.FINE_DOTS);
//
//        // 设置列的样式
//        for (int i = 0; i <= 14; i++) {
//            sheet.setDefaultColumnStyle((short) i, sheetStyle);
//        }
//        // 设置字体
//        HSSFFont headfont = workbook.createFont();
//        headfont.setFontName("黑体");
//        headfont.setFontHeightInPoints((short) 22);// 字体大小
//
////        headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
//        headfont.setBold(true);
//
//        // 另一个样式
//        HSSFCellStyle headstyle = workbook.createCellStyle();
//        headstyle.setFont(headfont);
//
////        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
//        headstyle.setAlignment(HorizontalAlignment.CENTER);
//
////        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
//        headstyle.setVerticalAlignment(VerticalAlignment.CENTER);
//
//        headstyle.setLocked(true);
//        headstyle.setWrapText(true);// 自动换行
//        // 另一个字体样式
//        HSSFFont columnHeadFont = workbook.createFont();
//        columnHeadFont.setFontName("宋体");
//        columnHeadFont.setFontHeightInPoints((short) 10);
////        columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        columnHeadFont.setBold(true);
//        // 列头的样式
//        HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
//        columnHeadStyle.setFont(columnHeadFont);
//
////        columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
//        columnHeadStyle.setAlignment(HorizontalAlignment.CENTER);
//
////        columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
//        columnHeadStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//
//        columnHeadStyle.setLocked(true);
//        columnHeadStyle.setWrapText(true);
//        columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
//
////        columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
//        columnHeadStyle.setBorderLeft(BorderStyle.DASH_DOT);
//
//        columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
//
////        columnHeadStyle.setBorderRight((short) 1);// 边框的大小
//        columnHeadStyle.setBorderRight(BorderStyle.DASH_DOT);
//
////        columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
//        columnHeadStyle.setBorderBottom(BorderStyle.DASH_DOT);
//
//        columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
//        // 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
//        columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);
//
//        HSSFFont font = workbook.createFont();
//        font.setFontName("宋体");
//        font.setFontHeightInPoints((short) 10);
//        // 普通单元格样式
//        HSSFCellStyle style = workbook.createCellStyle();
//        style.setFont(font);
//
////        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中
//        style.setAlignment(HorizontalAlignment.CENTER);
//
////        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);// 上下居中
//        style.setVerticalAlignment(VerticalAlignment.CENTER);
//
//        style.setWrapText(true);
//        style.setLeftBorderColor(HSSFColor.BLACK.index);
//
////        style.setBorderLeft((short) 1);
//        style.setBorderLeft(BorderStyle.DASH_DOT);
//
//        style.setRightBorderColor(HSSFColor.BLACK.index);
//
////        style.setBorderRight((short) 1);
//        style.setBorderRight(BorderStyle.DASH_DOT);
//
////        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
//        style.setBorderBottom(BorderStyle.DASH_DOT);
//
//        style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
//        style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．
//        // 另一个样式
//        HSSFCellStyle centerstyle = workbook.createCellStyle();
//        centerstyle.setFont(font);
//
////        centerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
//        centerstyle.setAlignment(HorizontalAlignment.CENTER);
//
////        centerstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
//        centerstyle.setVerticalAlignment(VerticalAlignment.CENTER);
//
//        centerstyle.setWrapText(true);
//        centerstyle.setLeftBorderColor(HSSFColor.BLACK.index);
//
////        centerstyle.setBorderLeft((short) 1);
//        centerstyle.setBorderLeft(BorderStyle.DASH_DOT);
//
//        centerstyle.setRightBorderColor(HSSFColor.BLACK.index);
//
////        centerstyle.setBorderRight((short) 1);
//        centerstyle.setBorderRight(BorderStyle.DASH_DOT);
//
////        centerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
//        centerstyle.setBorderBottom(BorderStyle.DASH_DOT);
//
//        centerstyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
//        centerstyle.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．
//
//        try {
//            // 创建第一行
//            HSSFRow row0 = sheet.createRow(0);
//            // 设置行高
//            row0.setHeight((short) 900);
//            // 创建第一列
//            HSSFCell cell0 = row0.createCell(0);
//            cell0.setCellValue(new HSSFRichTextString("中非发展基金投资项目调度会工作落实情况对照表"));
//            cell0.setCellStyle(headstyle);
//            /**
//             * 合并单元格
//             *    第一个参数：第一个单元格的行数（从0开始）
//             *    第二个参数：第二个单元格的行数（从0开始）
//             *    第三个参数：第一个单元格的列数（从0开始）
//             *    第四个参数：第二个单元格的列数（从0开始）
//             */
//            CellRangeAddress range = new CellRangeAddress(0, 0, 0, 7);
//            sheet.addMergedRegion(range);
//            // 创建第二行
//            HSSFRow row1 = sheet.createRow(1);
//            HSSFCell cell1 = row1.createCell(0);
//            cell1.setCellValue(new HSSFRichTextString("本次会议时间：2009年8月31日       前次会议时间：2009年8月24日"));
//            cell1.setCellStyle(centerstyle);
//            // 合并单元格
//            range = new CellRangeAddress(1, 2, 0, 7);
//            sheet.addMergedRegion(range);
//            // 第三行
//            HSSFRow row2 = sheet.createRow(3);
//            row2.setHeight((short) 750);
//            HSSFCell cell = row2.createCell(0);
//            cell.setCellValue(new HSSFRichTextString("责任者"));
//            cell.setCellStyle(columnHeadStyle);
//            cell = row2.createCell(1);
//            cell.setCellValue(new HSSFRichTextString("成熟度排序"));
//            cell.setCellStyle(columnHeadStyle);
//            cell = row2.createCell(2);
//            cell.setCellValue(new HSSFRichTextString("事项"));
//            cell.setCellStyle(columnHeadStyle);
//            cell = row2.createCell(3);
//            cell.setCellValue(new HSSFRichTextString("前次会议要求/n/新项目的项目概要"));
//            cell.setCellStyle(columnHeadStyle);
//            cell = row2.createCell(4);
//            cell.setCellValue(new HSSFRichTextString("上周工作进展"));
//            cell.setCellStyle(columnHeadStyle);
//            cell = row2.createCell(5);
//            cell.setCellValue(new HSSFRichTextString("本周工作计划"));
//            cell.setCellStyle(columnHeadStyle);
//            cell = row2.createCell(6);
//            cell.setCellValue(new HSSFRichTextString("问题和建议"));
//            cell.setCellStyle(columnHeadStyle);
//            cell = row2.createCell(7);
//            cell.setCellValue(new HSSFRichTextString("备 注"));
//            cell.setCellStyle(columnHeadStyle);
//            // 访问数据库，得到数据集
//            List<DeitelVO> deitelVOList = getEntityManager().queryDeitelVOList();
//            int m = 4;
//            int k = 4;
//            for (int i = 0; i < deitelVOList.size(); i++) {
//                DeitelVO vo = deitelVOList.get(i);
//                String dname = vo.getDname();
//                List<Workinfo> workList = vo.getWorkInfoList();
//                HSSFRow row = sheet.createRow(m);
//                cell = row.createCell(0);
//                cell.setCellValue(new HSSFRichTextString(dname));
//                cell.setCellStyle(centerstyle);
//                // 合并单元格
//                range = new CellRangeAddress(m, m + workList.size() - 1, 0, 0);
//                sheet.addMergedRegion(range);
//                m = m + workList.size();
//
//                for (int j = 0; j < workList.size(); j++) {
//                    Workinfo w = workList.get(j);
//                    // 遍历数据集创建Excel的行
//                    row = sheet.getRow(k + j);
//                    if (null == row) {
//                        row = sheet.createRow(k + j);
//                    }
//                    cell = row.createCell(1);
//                    cell.setCellValue(w.getWnumber());
//                    cell.setCellStyle(centerstyle);
//                    cell = row.createCell(2);
//                    cell.setCellValue(new HSSFRichTextString(w.getWitem()));
//                    cell.setCellStyle(style);
//                    cell = row.createCell(3);
//                    cell.setCellValue(new HSSFRichTextString(w.getWmeting()));
//                    cell.setCellStyle(style);
//                    cell = row.createCell(4);
//                    cell.setCellValue(new HSSFRichTextString(w.getWbweek()));
//                    cell.setCellStyle(style);
//                    cell = row.createCell(5);
//                    cell.setCellValue(new HSSFRichTextString(w.getWtweek()));
//                    cell.setCellStyle(style);
//                    cell = row.createCell(6);
//                    cell.setCellValue(new HSSFRichTextString(w.getWproblem()));
//                    cell.setCellStyle(style);
//                    cell = row.createCell(7);
//                    cell.setCellValue(new HSSFRichTextString(w.getWremark()));
//                    cell.setCellStyle(style);
//                }
//                k = k + workList.size();
//            }
//            // 列尾
//            int footRownumber = sheet.getLastRowNum();
//            HSSFRow footRow = sheet.createRow(footRownumber + 1);
//            HSSFCell footRowcell = footRow.createCell(0);
//            footRowcell.setCellValue(new HSSFRichTextString("                    审  定：XXX      审  核：XXX     汇  总：XX"));
//            footRowcell.setCellStyle(centerstyle);
//            range = new CellRangeAddress(footRownumber + 1, footRownumber + 1, 0, 7);
//            sheet.addMergedRegion(range);
//
//            HttpServletResponse response = getResponse();
//            HttpServletRequest request = getRequest();
//            String filename = "未命名.xls";//设置下载时客户端Excel的名称
//            filename = Util.encodeFilename(filename, request);
//            response.setContentType("application/vnd.ms-excel");
//            response.setHeader("Content-disposition", "attachment;filename=" + filename);
//            OutputStream ouputStream = response.getOutputStream();
//            workbook.write(ouputStream);
//            ouputStream.flush();
//            ouputStream.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}

package com.test.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 数据导出工具类
 * @param <T>   数据集的泛型，可以是任何对象的集合
 */
@Component
public class DataExportUtils<T> {

    /**
     * 导出数据到excel，并通过浏览器下载
     * @param response
     * @param sheetName sheet页的名称
     * @param filePath  文件的存储路径
     * @param fileName  导出的文件名称
     * @param tableHeader   导出的表格的头部，每一列的名称
     * @param data  所有的数据集
     */
    public void exportToExcel(HttpServletResponse response,String sheetName,String filePath,String fileName,List<String> tableHeader,Collection<T> data) {
        //生成excel文档对象
        HSSFWorkbook workBook = new HSSFWorkbook();
        //创建工作簿
        HSSFSheet mySheet = workBook.createSheet();
        mySheet.setDefaultColumnWidth(15);//设置单元格的默认宽度
        mySheet.createFreezePane(1,2,1,2);//冻结单元格.第一个参数表示要冻结的列数；第二个参数表示要冻结的行数，这里只冻结列所以为0; 第三个参数表示右边区域可见的首列序号，从1开始计算；第四个参数表示下边区域可见的首行序号，也是从1开始计算，这里是冻结列，所以为0；
        //设置工作簿的名字
        workBook.setSheetName(0, sheetName);
        //创建第一行，标题行
        int rowNomber = -1;
        HSSFRow myRow = mySheet.createRow(++rowNomber);
        HSSFCellStyle headStyle = workBook.createCellStyle();//表头样式
        headStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index); // 前景色设置 ①
        headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 填充模式 设置 ② 备注：① + ② 两步才能设置背景颜色
        headStyle.setWrapText(true);// 自动换行
        headStyle.setAlignment(HorizontalAlignment.CENTER);// 左右居中
        headStyle.setVerticalAlignment(VerticalAlignment.CENTER);//单元格上下居中
        headStyle.setBorderLeft(BorderStyle.THIN);//设置表格的左边框
        headStyle.setBorderRight(BorderStyle.THIN);//设置表格的左边框
        headStyle.setBorderTop(BorderStyle.THIN);//设置表格的上边框
        headStyle.setBorderBottom(BorderStyle.THIN);//设置表格的下边框
        HSSFCellStyle bodyStyle = workBook.createCellStyle();//表格内容部分样式
        bodyStyle.setWrapText(true);// 自动换行
        bodyStyle.setAlignment(HorizontalAlignment.CENTER);// 左右居中
        bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);//单元格上下居中
        bodyStyle.setBorderLeft(BorderStyle.THIN);//设置表格的左边框
        bodyStyle.setBorderRight(BorderStyle.THIN);//设置表格的左边框
        bodyStyle.setBorderTop(BorderStyle.THIN);//设置表格的上边框
        bodyStyle.setBorderBottom(BorderStyle.THIN);//设置表格的下边框
        //设置字体样式
        HSSFFont headFont = workBook.createFont();
        headFont.setFontName("宋体");//设置字体
        headFont.setFontHeightInPoints((short) 10);//设置字体大小
        headFont.setBold(true);//设置字体是否加粗
        HSSFFont bodyFont = workBook.createFont();
        bodyFont.setFontName("宋体");//设置字体
        bodyFont.setFontHeightInPoints((short) 10);//设置字体大小
        headStyle.setFont(headFont);
        bodyStyle.setFont(bodyFont);

        FileOutputStream fos = null;
        FileInputStream in = null;
        OutputStream out = null;

        try {
            //设置标题行，每一列的标题
            HSSFCell cell = null;
            if (tableHeader != null && tableHeader.size() > 0) {
                for (int i = 0; i < tableHeader.size(); i++) {
                    cell = myRow.createCell((short) i);
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(tableHeader.get(i));
                }
            }

            // 遍历集合数据，产生数据行
            Iterator<T> it = data.iterator();
            //添加数据
            while (it.hasNext()) {
                T t = (T) it.next();
                // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
                Field[] fields = t.getClass().getDeclaredFields();
                //创建行
                HSSFRow  Row = mySheet.createRow(++rowNomber);
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    String fieldName = field.getName();
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    try
                    {
                        Class tCls = t.getClass();
                        Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
                        Object value = getMethod.invoke(t, new Object[] {});
                        // 判断值的类型后进行强制类型转换
                        String textValue = value == null ? "" : value.toString();
                        HSSFCell hssfCell = Row.createCell((short) i);
                        hssfCell.setCellStyle(bodyStyle);
                        hssfCell.setCellValue(textValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            File newPath = new File(filePath);
            newPath.mkdirs();
            // 删除临时文件
            boolean success = fileDelete(newPath);
            if (success) {
                newPath.mkdirs();

                File file = new File(filePath + "\\" + fileName);
                fos = new FileOutputStream(file);
                workBook.write(fos);// 写文件
                // 设置响应头，控制浏览器下载该文件
                response.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes(),"iso-8859-1"));
                // 读取要下载的文件，保存到文件输入流
                in = new FileInputStream(filePath + "\\" + fileName);
                // 创建输出流
                out = response.getOutputStream();
                // 创建缓冲区
                byte buffer[] = new byte[1024];
                int len = 0;
                // 循环将输入流中的内容读取到缓冲区当中
                while ((len = in.read(buffer)) > 0) {
                    // 输出缓冲区的内容到浏览器，实现文件下载
                    out.write(buffer, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fos);
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
    }

    private boolean fileDelete(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()){
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    this.fileDelete(files[i]);
                }
                file.delete();
            }
            return true;
        } else {
            System.out.println("所删除的文件不存在");
            return false;
        }
    }

}

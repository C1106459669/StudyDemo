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
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    //生成随机数的几种方式和区别
    @Test
    public void test04() {
        //1.java.util.Random 的 nextInt()方法生成随机数
        //备注：java.util.Random 的 nextInt()方法生成的随机数是伪随机数，具有一定的可预测性
        Random random = new Random();
        int value1 = random.nextInt(99);//生成0-99的随机数
        System.out.println("java.util.Random 的 nextInt() 方法生成的随机数：" + value1);
        //2.java.security.SecureRandom 的 nextInt() 方法生成随机数
        //备注：java.security.SecureRandom 的 nextInt() 方法生成的随机数是真随机数，其输出结果较难预测，安全性更高
        SecureRandom sr = new SecureRandom();
        sr.setSeed(System.currentTimeMillis());//设置种子
        int value2 = sr.nextInt(100);//生成0-99的随机数
        System.out.println("java.security.SecureRandom 的 nextInt() 方法生成的随机数：" + value2);
    }

}

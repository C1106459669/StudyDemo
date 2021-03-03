package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)//不加这个注解 userService 就无法注入
@SpringBootTest
public class JavaProgramTest {

    //古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
    @Test
    public void test1() {
        System.out.println("第1个月兔子的对数：" + 1);
        System.out.println("第2个月兔子的对数：" + 1);
        int a = 1;//每月新增的兔子对数
        int b = 1;//当前月兔子总对数
        int c;//
        int d = 24;//未来多少个月
        for (int i = 3; i <= d; i++) {
            c = b;
            b = a + b;
            a = c;
            System.out.println("第" + i + "个月兔子的对数：" + b);
        }
    }

    //判断101-200之间有多少个素数，并输出所有素数
    //只能被 1和自身 整除的数叫做素数，也叫做质数
    //Math.sqrt(i) 获取一个数的正平方根
    @Test
    public void test2() {
        int count = 0;
        for(int i = 101; i < 200; i += 2) {
            boolean b = false;
            double sqrt = Math.sqrt(i);
            for(int j = 2; j <= Math.sqrt(i); j++) {
                if(i % j == 0) {
                    b = false;
                    break;
                } else {
                    b = true;
                }
            }
            if(b == true) {
                count ++;
                System.out.println(i);
            }
        }
        System.out.println( "素数个数是: " + count);
    }

}
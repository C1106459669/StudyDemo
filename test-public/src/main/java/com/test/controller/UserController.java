package com.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.test.entity.User;
import com.test.service.UserService;
import com.test.utils.DataExportUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DataExportUtils exportUtils;

    //请求http://localhost:8080/user/getAll报错Public Key Retrieval is not allowed的时候，使用请求http://localhost:8080/user/getAll?allowPublicKeyRetrieval=true即可
    @GetMapping("/getAll")
    public List<User> getAll() {
        return userService.selectAll();
    }

    @GetMapping("/exportAll")
    public void exportAll(HttpServletResponse response) {
        List<User> data = userService.selectAll();
        List<String> tableHead = new ArrayList<>();
        tableHead.add("id");
        tableHead.add("username");
        tableHead.add("password");

        // 写数据到Excel文件
        exportUtils.exportToExcel(response,"测试", "F:\\test","用户清单.xls", tableHead, data);
    }

}

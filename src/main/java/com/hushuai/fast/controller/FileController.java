package com.hushuai.fast.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hushuai.fast.vo.MemberVo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2019/7/29
 * @Interface: FileController
 * @Description:
 */
@Controller
@RequestMapping("/file")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "/upload")
    @ResponseBody
    public JSONObject upload(@RequestParam("file") MultipartFile file) {
        JSONObject result = new JSONObject();
        result.put("code",1);
        if (file.isEmpty()) {
            result.put("msg","文件为空");
            return result;
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = "D://test//";
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-mm-ss");
        String curTime = sdf.format(new Date());
        fileName = curTime+fileName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            boolean mkdirs = dest.getParentFile().mkdirs();
            if (!mkdirs){
                throw new IllegalStateException();
            }
        }
        try {
            file.transferTo(dest);
            result.put("code",0);
            result.put("msg","上传成功！");
            return result;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.put("msg","上传失败！");
        return result;
    }

    // 文件下载
    @RequestMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response, String fileName) {
        if (fileName != null) {
            // 当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
            // String realPath =
            // request.getServletContext().getRealPath("//WEB-INF//");
            String realPath = "D://test//";
            File file = new File(realPath, fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    // excel文件解析
    public void importMemberList(String filePath){
        filePath = "D://test//"+"2019-07-31-52-47会员信息表.xls";
        List<MemberVo> memberList = new ArrayList<>();
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));
            HSSFSheet memberListTable = workbook.getSheet("Table");
            int lastRowNum = memberListTable.getLastRowNum();
            for (int i = 1; i < lastRowNum; i++) {
                HSSFRow row = memberListTable.getRow(i);
                // 创建会员实体
                MemberVo memberVo = new MemberVo();
                // 1会员名字
                memberVo.setName(row.getCell(0).getStringCellValue());
                // 2电话号码
                memberVo.setTelephone(row.getCell(1).getStringCellValue());
                // 3会员折扣
//                row.getCell(2).getNumericCellValue()

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package com.ming.springbootfile.controller;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/4/21 9:53 上午
 */
@RestController
@Slf4j
public class FileDownloadController {
    @Value("${file_path}")
    private String filePath;
    @Value("${login_user}")
    private String loginUser;
    @Value("${login_pwd}")
    private String loginPwd;

    @GetMapping("/download")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        Map<String,Object> mapResult = new HashMap<>();
//        mapResult.put("success",true);

        String fileName = request.getParameter("fileName");
        if (StrUtil.isBlank(fileName)){
            response.sendError(403,"文件名不能为空");
            return;
//            mapResult.put("success",false);
//            mapResult.put("errMsg","文件名不能为空");
//            return mapResult;
        }
        //设置文件路径
        File file = new File(filePath+fileName);
        if (!file.exists()) {
            response.sendError(403,"文件不存在");
            return;
//            mapResult.put("success",false);
//            mapResult.put("errMsg","文件不存在");
//            return mapResult;

        }
        if (!isAuth(request)){
            response.sendError(403,"权限认证失败");
            return;
//            mapResult.put("success",false);
//            mapResult.put("errMsg","权限认证失败");
//            return mapResult;

        }



        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName="
                + fileName);
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
            //return mapResult;
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(403,"权限认证失败");
        } finally {
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


//        mapResult.put("success",false);
//        mapResult.put("errMsg","下载失败");
//        return mapResult;
    }

    private boolean isAuth(HttpServletRequest request){
        String userName = request.getParameter("userName");
        String pwd = request.getParameter("pwd");
        if (userName == null || pwd == null){
            return false;
        }
        if (loginUser.equals(userName) && loginPwd.equals(pwd)){
            return true;
        }
        return false;
    }
}

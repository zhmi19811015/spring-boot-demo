package com.ming.springbootresourcesfile.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 最笨方式
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-11-18 20:37
 */
@RestController
public class StaticResourceController {
    @RequestMapping("/static/**")
    public void getHtml(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        String[] arr = uri.split("static/");
        String resourceName = "index.html";
        if (arr.length > 1) {
            resourceName = arr[1];
        }
        String url = StaticResourceController.class.getResource("/").getPath() +
                "html/" + resourceName;
        try {
            FileReader reader = new FileReader(new File(url));
            BufferedReader br = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            response.getOutputStream().write(sb.toString().getBytes());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

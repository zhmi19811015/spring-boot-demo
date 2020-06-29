package com.ming.mingspringbootstarter.demo1;

import lombok.Data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/5/29 10:13 下午
 */
@Data
public class HttpClient {
    private String url;

    // 根据url获取网页数据
    public String getHtml() {
        try {
            URL url = new URL(this.url);
            URLConnection urlConnection = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }
}

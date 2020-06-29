package com.ming.rabbitmqpdemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.micrometer.core.instrument.util.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-12-09 17:37
 */
public class Test {
    public static void main(String[] args) throws Exception {
        String result = getApiMessage();
        if(StringUtils.isNotBlank(result)){
            JSONArray jsonArray = JSON.parseArray(result);
            for(int i = 0; i < jsonArray.size(); i++){
                JSONObject jsonObject = JSONObject.parseObject(jsonArray.getString(i));
                String name = jsonObject.getString("name");
                System.out.println("队列名称："+name);
            }
        }
    }

    public static String getApiMessage() throws IOException {
        //发送一个GET请求
        HttpURLConnection httpConn = null;
        BufferedReader in = null;

        String urlString = "http://192.168.10.230:15672/api/queues";
        URL url = new URL(urlString);
        httpConn = (HttpURLConnection) url.openConnection();
        //设置用户名密码
        String auth = "guest:guest";
        BASE64Encoder enc = new BASE64Encoder();
        String encoding = enc.encode(auth.getBytes());
        httpConn.setDoOutput(true);
        httpConn.setRequestProperty("Authorization", "Basic " + encoding);
        // 建立实际的连接
        httpConn.connect();
        //读取响应
        if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            StringBuilder content = new StringBuilder();
            String tempStr = "";
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            while ((tempStr = in.readLine()) != null) {
                content.append(tempStr);
            }
            in.close();
            httpConn.disconnect();
            return content.toString();
        } else {
            httpConn.disconnect();
            return "";
        }
    }

}

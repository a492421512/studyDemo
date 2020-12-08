package com.xykj.juc.crawler;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 爬虫入门
 * @Author: wm
 * @Date: 2020-12-02  17:18
 * @Version 1.0
 */
public class TestCrawler {
    public static void main(String[] args) throws IOException {
        // 打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 发起Get请求
        HttpGet httpGet = new HttpGet("https://www.jianshu.com/");
        // 获取相应的相应
        CloseableHttpResponse response = httpClient.execute(httpGet);
        // 判断状态码
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = response.getEntity(); // 获取响应实体
            String content = EntityUtils.toString(httpEntity, "utf8");
            System.out.println("输出相应的结果：" + content);
        }

    }
}

package com.ruoyi.web.controller.business;

import org.springframework.stereotype.Component;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.net.URL;

@Component
public class TestD {


    public String test() throws IOException {
        URL url =  new URL("http://192.168.2.85:85/A2?a=6&b=5");
        String initParam = getInitParam();
        HttpClient httpClient = new HttpClient(url,initParam,1);

        return initParam;
    }



    public String getInitParam(){
        String json = "{\n" +
                "\"componentId\": \"image-processor\",\n" +
                "\"endpoint\": {\n" +
                "\"host\": \"192.168.1.100\",\n" +
                "\"port\": 8080,\n" +
                "\"protocol\": \"http\"\n" +
                "},\n" +
                "\"capacity\": {\n" +
                "\"maxTasks\": 10,\n" +
                "\"maxMemory\": 1024\n" +
                "},\n" +
                "\"metadata\": {\n" +
                "\"version\": \"1.0.0\",\n" +
                "\"features\": [\"gpu\", \"cuda\"]\n" +
                "}\n" +
                "}";
        return json;
    }
}
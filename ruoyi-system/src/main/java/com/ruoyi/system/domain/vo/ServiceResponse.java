package com.ruoyi.system.domain.vo;


public class ServiceResponse {
    private String nodeName;
    private String response;

    public ServiceResponse(String nodeName, String response) {
        this.nodeName = nodeName;
        this.response = response;
    }

    public String getNodeName() {
        return nodeName;
    }

    public String getResponse() {
        return response;
    }
}

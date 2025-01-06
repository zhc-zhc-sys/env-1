package com.ruoyi.system.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.http.HttpUtils;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.slot.DefaultContext;

import java.util.Map;

@LiteflowComponent("A1C3D3")
public class A1C3D3 extends NodeComponent {
    @Override
    public void process() throws Exception {
        DefaultContext context = this.getContextBean(DefaultContext.class);
        Integer Y1 = context.getData("Y1");
        Integer R2 = context.getData("R2");
        Integer R3 = context.getData("R3");

        String ip="http://127.0.0.1:11909/system/businessService/callA1C3D3";
        //调用controler的get请求
        String param="a=8&Y1="+Y1+"&R2="+R2+"&R3="+R3;
        String rs= HttpUtils.sendGet(ip,param);
        ObjectMapper objectMapper = new ObjectMapper();
        // 将 JSON 转换为 Map
        Map<String, Object> map = objectMapper.readValue(rs, Map.class);
        Map<String, Object> dataMap = (Map<String, Object>) map.get("data");
        int T1 = ((Number) dataMap.get("T1")).intValue();

        context.setData("Q1",T1);
    }
}

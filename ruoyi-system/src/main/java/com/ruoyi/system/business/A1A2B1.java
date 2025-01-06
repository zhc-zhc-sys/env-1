/*
package com.ruoyi.system.business;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.http.HttpUtils;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.slot.DefaultContext;

import java.util.Map;

@LiteflowComponent("A1A2B1")
public class A1A2B1 extends NodeComponent {
    @Override
    public void process() throws Exception {
        DefaultContext context = this.getContextBean(DefaultContext.class);


        Integer X1 = context.getData("X1");
        Integer Y1 = context.getData("Y1");

        String ip="http://127.0.0.1:11909/system/businessService/callA1A2B1";
        //调用controler的get请求
        String param="a=1&b=3&X1="+X1+"&Y1="+Y1;
        String rs= HttpUtils.sendGet(ip,param);
        ObjectMapper objectMapper = new ObjectMapper();
        // 将 JSON 转换为 Map
        Map<String, Object> map = objectMapper.readValue(rs, Map.class);
        Map<String, Object> dataMap = (Map<String, Object>) map.get("data");
        int O1 = ((Number) dataMap.get("O1")).intValue();
        int O2 = ((Number) dataMap.get("O2")).intValue();
        int O3 = ((Number) dataMap.get("O3")).intValue();

        context.setData("O1",O1);
        context.setData("O2",O2);
        context.setData("O3",O3);
    }
}
*/

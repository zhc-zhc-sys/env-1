/*
package com.ruoyi.system.business;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.http.HttpUtils;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.slot.DefaultContext;

import java.util.Map;

@LiteflowComponent("A1B1C3")
public class A1B1C3 extends NodeComponent {
    @Override
    public void process() throws Exception {
        DefaultContext context = this.getContextBean(DefaultContext.class);
        Integer Z1 = context.getData("Z1");
        Integer Y2 = context.getData("Y2");

        String ip="http://127.0.0.1:11909/system/businessService/callA1B1C3";
        //调用controler的get请求
        String param="a=1&b=4&c=5&d=6&Z1="+Z1+"&Y2="+Y2;
        String rs= HttpUtils.sendGet(ip,param);
        ObjectMapper objectMapper = new ObjectMapper();
        // 将 JSON 转换为 Map
        Map<String, Object> map = objectMapper.readValue(rs, Map.class);
        Map<String, Object> dataMap = (Map<String, Object>) map.get("data");

        int R1 = ((Number) dataMap.get("R1")).intValue();
        int R2 = ((Number) dataMap.get("R2")).intValue();
        int R3 = ((Number) dataMap.get("R3")).intValue();

        context.setData("R1",R1);
        context.setData("R2",R2);
        context.setData("R3",R3);
    }
}
*/

/*
package com.ruoyi.system.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.http.HttpUtils;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.slot.DefaultContext;

import java.util.Map;

@LiteflowComponent("B1B2B3C3D2")
public class B1B2B3C3D2 extends NodeComponent {
    @Override
    public void process() throws Exception {
        DefaultContext context = this.getContextBean(DefaultContext.class);
        Integer O3 = context.getData("O3");
        Integer Z1 = context.getData("Z1");
        Integer R1 = context.getData("R1");

        String ip="http://127.0.0.1:11909/system/businessService/callB1B2B3C3D2";
        //调用controler的get请求
        String param="a=7&O3="+O3+"&Z1="+Z1+"&R1="+R1;
        String rs= HttpUtils.sendGet(ip,param);
        ObjectMapper objectMapper = new ObjectMapper();
        // 将 JSON 转换为 Map
        Map<String, Object> map = objectMapper.readValue(rs, Map.class);
        Map<String, Object> dataMap = (Map<String, Object>) map.get("data");
        int S1 = ((Number) dataMap.get("S1")).intValue();

        context.setData("S1",S1);

    }
}
*/

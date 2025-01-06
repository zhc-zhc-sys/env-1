/*
package com.ruoyi.system.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.http.HttpUtils;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.slot.DefaultContext;

import java.util.Map;

@LiteflowComponent("B1C1")
public class B1C1 extends NodeComponent {
    @Override
    public void process() throws Exception {
        DefaultContext context = this.getContextBean(DefaultContext.class);
        Integer O1 = context.getData("O1");

        String ip="http://127.0.0.1:11909/system/businessService/callB1C1";
        //调用controler的get请求
        String param="a=6&O1="+O1;
        String rs= HttpUtils.sendGet(ip,param);
        ObjectMapper objectMapper = new ObjectMapper();
        // 将 JSON 转换为 Map
        Map<String, Object> map = objectMapper.readValue(rs, Map.class);
        Map<String, Object> dataMap = (Map<String, Object>) map.get("data");
        int Z2 = ((Number) dataMap.get("Z2")).intValue();

        context.setData("Z2",Z2);

    }
}
*/

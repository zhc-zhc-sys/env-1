/*
package com.ruoyi.system.business;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.http.HttpUtils;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.slot.DefaultContext;

import java.util.Map;

@LiteflowComponent("B1C2")
public class B1C2 extends NodeComponent {
    @Override
    public void process() throws Exception {
        DefaultContext context = this.getContextBean(DefaultContext.class);
        Integer O1 = context.getData("O1");
        Integer O2 = context.getData("O2");

        String ip = "http://127.0.0.1:11909/system/businessService/callB1C2";
        //调用controler的get请求
        String param = "a=1&b=4&c=5&d=8&e=9&O1=" + O1 + "&O2=" + O2;
        String rs = HttpUtils.sendGet(ip, param);
        ObjectMapper objectMapper = new ObjectMapper();
        // 将 JSON 转换为 Map
        Map<String, Object> map = objectMapper.readValue(rs, Map.class);
        Map<String, Object> dataMap = (Map<String, Object>) map.get("data");

        int P1 = ((Number) dataMap.get("P1")).intValue();

        context.setData("P1", P1);
    }
}
*/

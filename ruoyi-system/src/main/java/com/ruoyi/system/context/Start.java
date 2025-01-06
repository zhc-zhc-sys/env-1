package com.ruoyi.system.context;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.http.HttpUtils;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.slot.DefaultContext;

import java.util.Map;

@LiteflowComponent("Start")
public class Start extends NodeComponent {

    @Override
    public void process() throws Exception {

        DefaultContext context =this.getContextBean(DefaultContext.class);

        String ip="http://127.0.0.1:11909/system/businessService/callA2";
        //调用controler的get请求
        String param="a=6&b=5";
        String rs= HttpUtils.sendGet(ip,param);
        ObjectMapper objectMapper = new ObjectMapper();
        // 将 JSON 转换为 Map
        Map<String, Object> map = objectMapper.readValue(rs, Map.class);
        Map<String, Object> dataMap = (Map<String, Object>) map.get("data");
        int X1 = ((Number) dataMap.get("X1")).intValue();
        System.out.println("X1: " + X1);
        System.out.println(rs);
        context.setData("X1",X1);

        String jsonString = "{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"Y1\":3,\"Y2\":7}}";


    }
}



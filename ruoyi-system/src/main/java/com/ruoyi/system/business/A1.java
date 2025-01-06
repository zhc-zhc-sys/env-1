/*
package com.ruoyi.system.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.http.HttpUtils;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.slot.DefaultContext;

import java.util.Map;

@LiteflowComponent("A1")
public class A1 extends NodeComponent {

    @Override
    public void process() throws Exception {
        System.out.println("ACmp executed!");

        //查询当前任务节点所选择的属性getContextBean
        DefaultContext context =this.getContextBean(DefaultContext.class);
        System.out.println(context);

        String ip="http://127.0.0.1:11909/system/businessService/callA1";
        //调用controler的get请求
        String param="a=3&b=4&c=1527";
        String rs= HttpUtils.sendGet(ip,param);
        ObjectMapper objectMapper = new ObjectMapper();
        // 将 JSON 转换为 Map
        Map<String, Object> map = objectMapper.readValue(rs, Map.class);
        Map<String, Object> dataMap = (Map<String, Object>) map.get("data");
        int Y1 = ((Number) dataMap.get("Y1")).intValue();
        int Y2 = ((Number) dataMap.get("Y2")).intValue();
        System.out.println("Y1: " + Y1);
        System.out.println("Y2: " + Y2);
        System.out.println(rs);
        context.setData("Y1",Y1);
        context.setData("Y2",Y2);

    }
}
*/

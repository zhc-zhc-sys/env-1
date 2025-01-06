package com.ruoyi.web.controller.liteflow;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.slot.Slot;

public class LiteflowNode extends NodeComponent {

    @Override
    public void process() throws Exception {
        JSONObject requestData = (JSONObject)this.getRequestData();
        System.out.println(JSON.toJSONString(requestData));
        int v1 = requestData.getInteger("a");
        int v2 = 3;
        System.out.println("s1 = " +   v1 * v2);
        Slot slot = this.getSlot();
        slot.setOutput("S1", v1 * v2);
        Object s1 = slot.getOutput("S1");
        System.out.println(JSON.toJSONString(s1));
        JSONObject startParam = new JSONObject();
        startParam.put("a", v1);
        startParam.put("b", 4);
        slot.setRequestData(startParam);
    }
}

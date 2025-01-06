package com.ruoyi.web.controller.business;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.LiteFlowChain;
import com.yomahub.liteflow.builder.LiteFlowNodeBuilder;
import com.yomahub.liteflow.builder.el.LiteFlowChainELBuilder;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.core.FlowExecutorHolder;
import com.yomahub.liteflow.flow.FlowBus;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class LiteflowBusinessController  extends BaseController {


    @GetMapping("/start")
    public AjaxResult startProcess() {


        createNumberNode();

        LiteFlowChainELBuilder.createChain()
                .setChainId("myChain")
                .setEL("THEN(addNumbersNode1, addNumbersNode3)") // 假设otherNodeId是另一个节点的ID
                .build();

        // 创建Liteflow执行器
        FlowExecutor flowExecutor = FlowExecutorHolder.loadInstance();
//        // 执行流程
        JSONObject startParam = new JSONObject();
        startParam.put("a", 3);
        startParam.put("b", 4);
        LiteflowResponse response = flowExecutor.execute2Resp("myChain", startParam);
        System.out.println(response);
        return error();
    }


    public static void createNumberNode() {

        // 创建一个脚本节点，假设节点名为"addNumbersNode"
        LiteFlowNodeBuilder.createCommonNode()
                .setId("addNumbersNode1")
                .setName("节点")
                .setClazz("com.ruoyi.web.controller.liteflow.LiteflowNode")
                .build();

        // 创建一个脚本节点，假设节点名为"addNumbersNode"
//        LiteFlowNodeBuilder.createScriptNode()
//                .setId("addNumbersNode2")
//                .setName("javascript")
//                .setScript("import java.lang.System;\n" +"System.out.println(\"addNumbersNode2\");")
//                .build();

        LiteFlowNodeBuilder.createCommonNode()
                .setId("addNumbersNode3")
                .setName("节点3")
                .setClazz("com.ruoyi.web.controller.liteflow.LiteflowNode")
                .build();
    }

}

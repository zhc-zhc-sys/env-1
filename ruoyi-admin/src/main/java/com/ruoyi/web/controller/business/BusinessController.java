package com.ruoyi.web.controller.business;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.LiteFlowChain;
import com.ruoyi.system.domain.ServiceNode;
import com.ruoyi.system.service.ILiteFlowChainService;
import com.ruoyi.system.service.IServiceNodeService;
import com.ruoyi.web.controller.Node.ProcessDTO;
import com.ruoyi.web.controller.Node.ProcessLineDto;
import com.yomahub.liteflow.builder.el.ELBus;
import com.yomahub.liteflow.builder.el.LiteFlowChainELBuilder;
import com.yomahub.liteflow.builder.el.ThenELWrapper;
import com.yomahub.liteflow.builder.el.WhenELWrapper;
import com.yomahub.liteflow.common.entity.ValidationResp;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import com.yomahub.liteflow.flow.element.Chain;
import com.yomahub.liteflow.flow.entity.CmpStep;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Queue;

/**
 * 流程处理
 */
@RestController
@RequestMapping("/test/system/business")
public class BusinessController {

    @Resource
    private FlowExecutor flowExecutor;

    @Resource
    private IServiceNodeService serviceNodeService;

    @Resource
    private ILiteFlowChainService liteFlowChainService;

    /**
     * 获取算子列表
     */
    @GetMapping("/nodeList")
    public AjaxResult nodeList() {
        LambdaQueryWrapper<ServiceNode> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ServiceNode::getDelFlag, 0);
        return AjaxResult.success(serviceNodeService.list());
    }

    /**
     * 注册算子
     */
    @GetMapping("/nodeAdd")
    public AjaxResult nodeAdd(@RequestParam ServiceNode serviceNode) {
        boolean save = false;
        if(serviceNode.getId()!=null){
            save = serviceNodeService.updateById(serviceNode);
        }else{
            save = serviceNodeService.save(serviceNode);
        }
        if(!save){
            return AjaxResult.error("注册或修改算子失败");
        }
        return AjaxResult.success();
    }


//    /**
//     * 保存或者更新流程
//     */
//    @PostMapping("/updateProcess")
//    public AjaxResult updateProcess(@RequestBody UpdateProcessVo updateProcessVo) {
//
//        liteFlowChainService.updateProcess(updateProcessVo.getJson(), updateProcessVo.getEvaluateId());
//        flowExecutor.reloadRule();
//        return AjaxResult.success();
//    }

    @PostMapping("/startProcess")
    public AjaxResult startProcess() {
//        ThenELWrapper el = ELBus.then("node1",
//                "node2",
//                "node3");
//        ValidationResp validationResp = LiteFlowChainELBuilder.validateWithEx(el.toEL());
//        boolean success = validationResp.isSuccess();
//        System.out.println("----------------el规则验证结果"+success);



        Chain chain = new Chain();
        chain.setChainId("chain1");

        ProcessDTO processDTO = new ProcessDTO();
        processDTO.setNodeId("node1");
        processDTO.setNodeName("node1111111");
        chain.setId("chain1");

//        String s = "THEN(node(\"node1\").data('" + JSON.toJSONString(processDTO) + "'),node(\"node2\").data('" + JSON.toJSONString(processDTO) + "'), node(\"node3\"))";
//        chain.setEl(s);
//        System.out.println(s);
        chain.setEl("THEN(node(\"node1\").data('{\"nodeId\":\"id-node-npqr3m3n\"}'),node(\"node2\").data('{\"nodeId\":\"id-node-4mlrgtsd\"}'),WHEN(node(\"node6\").data('{\"nodeId\":\"id-node-ug2xv7uf\"}'),node(\"node5\").data('{\"nodeId\":\"id-node-46gq9w5k\"}')))");
//        System.out.println("----------------el表达式"+el.toEL());
        LiteFlowChainELBuilder.buildUnCompileChain(chain);
        LiteflowResponse response = flowExecutor.execute2Resp("chain1", null, chain);
        Queue<CmpStep> stepQueue = response.getExecuteStepQueue();

        for (CmpStep cmpStep : stepQueue) {
            JSONObject jsonObject = JSON.parseObject(cmpStep.getRefNode().getCmpData());
            String nodeId = jsonObject.get("nodeId").toString();
            System.out.println("nodeId:+++++++++++++"+nodeId);
            //打印执行日志
            System.out.println("执行节点：" + cmpStep.getNodeId() + "是否成功："+ cmpStep.isSuccess() + "节点名称："
                    + cmpStep.getNodeName() + "，执行时间："
                    + cmpStep.getStartTime() + "，执行类型：" + cmpStep.getStepType() + "，执行结束时间："
                    + cmpStep.getEndTime() + "，执行耗时：" + cmpStep.getTimeSpent() + "执行实例"
                    + cmpStep.getInstance().getName() + "，执行参数RefNode：" + cmpStep.getRefNode() + "Tag:"
                    + cmpStep.getTag() + "，执行参数getRollbackTimeSpent：" + cmpStep.getRollbackTimeSpent()
                    + "Exception" + cmpStep.getException());
        }
        System.out.println("返回结果:{getExecuteSteps}::"+response.getExecuteSteps());
        System.out.println("返回结果:{getExecuteStepQueue}::"+response.getExecuteStepQueue());

        return AjaxResult.success();
    }

//    @PostMapping("/startProcess")
//    public AjaxResult startProcess(@RequestBody ProcessDTO processDTO) {
//        LiteFlowChain flowChain = liteFlowChainService.getById(processDTO.getChainId());
//
////        ThenELWrapper el = ELBus.then("node1",
////                "node2",
////                "node3");
//        //校验
//        ValidationResp validationResp = LiteFlowChainELBuilder.validateWithEx(el.toEL());
//        boolean success = validationResp.isSuccess();
//        if(!success){
//            throw new RuntimeException("EL表达式不符合规则！！！");
//        }
//        System.out.println("el规则验证结果是--------------"+success);
//        Chain chain = new Chain();
//        chain.setChainId("chain1");
//        String chainId = "flow_" + flowChain.getId();
//        chain.setId(chainId);
//        chain.setEl(flowChain.getElData());
////        chain.setId("chain1");
////        chain.setEl(el.toEL());
////        System.out.println("el表达式--------------"+el.toEL());
//        System.out.println("el表达式--------------"+flowChain.getElData());
//        LiteFlowChainELBuilder.buildUnCompileChain(chain);
//        flowExecutor.execute2Resp(chainId, null, chain);
//        return AjaxResult.success();
//    }

    @PostMapping("/saveProcess")
    public AjaxResult saveProcess(@RequestParam String json) {
        Test test = new Test();
        String s = test.parseJsonToEl5(json);
        System.out.println("-el------------------"+s);
        return null;
    }

}
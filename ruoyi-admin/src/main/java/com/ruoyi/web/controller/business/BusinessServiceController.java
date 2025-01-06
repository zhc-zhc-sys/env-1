package com.ruoyi.web.controller.business;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.ServiceNode;
import com.ruoyi.system.service.IServiceNodeService;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import com.yomahub.liteflow.slot.DefaultContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程处理
 */
@RestController
@RequestMapping("/system/businessService")
public class BusinessServiceController extends BaseController {

    @Resource
    private FlowExecutor flowExecutor;

    @Resource
    private IServiceNodeService serviceNodeService;

    /**
     * 获取节点列表
     */
    @GetMapping("/list")
    public AjaxResult list() {
        LambdaQueryWrapper<ServiceNode> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ServiceNode::getDelFlag, 0);
        List<ServiceNode> list = serviceNodeService.list();
        return AjaxResult.success(list);
    }


    /**
     * 获取全流程数据
     */
    @GetMapping("/getAllProcessData")
    public AjaxResult getAllProcessData(SysUser user) {
        return new AjaxResult();
    }


    /**
     * 保存流程
     *
     * @param response
     * @param user
     */
    @PostMapping("/saveOrUpdateProcess")
    public void saveOrUpdateProcess(HttpServletResponse response, SysUser user) {
        /**
         * 保存前端相关参数到DB
         */
    }


    /**
     * 启动流程
     */
    @GetMapping("/startProcess")
    public AjaxResult startProcess() {
        /**
         * 直接调用LiteFlow
         */
        LiteflowResponse response = flowExecutor.execute2Resp("mainChain", DefaultContext.class);
        System.out.println("返回结果:{}"+response);
        response.getContextBean(DefaultContext.class).getData("res");

        boolean isSuccess = response.isSuccess();


    /*    for (String key : dataMap.keySet()) {
            System.out.println("Key: " + key + ", Value: " + dataMap.get(key));
        }*/
        // System.out.println(context.toString());

        return AjaxResult.success(response.isSuccess());
  /*      Map<String, List<CmpStep>> stepMap = response.getExecuteSteps();

        //通过上下文实现每个节点执行情况
        return AjaxResult.success(stepMap.toString());*/
    }


    /**
     * 调用服务A1
     */
    @GetMapping("/callA1")
    public AjaxResult A1(Integer a, Integer b, Integer c) {
        Map map = new HashMap<>();
        //数据库查询A1所选择的属性 a=3,b=4,c=1527

        map.put("Y1", a + b);
        map.put("Y2", b + c);
        return AjaxResult.success(map);
    }


    /**
     * 调用服务A2
     */
    @GetMapping("/callA2")
    public AjaxResult callA2(Integer a, Integer b) {
        Map map = new HashMap<>();
        //数据库查询A2所选择的属性


        map.put("X1", a + b);
        return AjaxResult.success(map);
    }


    /**
     * 调用服务A1A2B1
     */
    @GetMapping("/callA1A2B1")
    public AjaxResult callA1A2B1(Integer a, Integer b, Integer X1, Integer Y1) {
        Map map = new HashMap<>();
        //数据库查询A1A2B1所选择的属性 a=6,b=5,输入参数X1,输入参数Y1 O1=X1+a,O2=b+Y1,O3=a+b

        map.put("O1", X1 + a);
        map.put("O2", b + Y1);
        map.put("O3", a + b);
        return AjaxResult.success(map);
    }


    /**
     * 调用服务A1B2
     */
    @GetMapping("/callA1B2")
    public AjaxResult callA1B2(Integer a, Integer b, Integer Y2) {
        Map map = new HashMap<>();
        //数据库查询A1B2所选择的属性  a=1,b=4,输入参数Y2 Z1=a+b+Y2

        map.put("Z1", a + b + Y2);
        return AjaxResult.success(map);
    }

    /**
     * 调用服务B1C1
     */
    @GetMapping("/callB1C1")
    public AjaxResult callB1C1(Integer a, Integer O1) {
        Map map = new HashMap<>();

        map.put("Z2", a + O1);
        return AjaxResult.success(map);
    }

    /**
     * 调用服务B1C2
     */
    @GetMapping("/callB1C2")
    public AjaxResult callB1C2(Integer a, Integer b, Integer c, Integer d, Integer e, Integer O1, Integer O2) {
        Map map = new HashMap<>();
        //数据库查询A1B2所选择的属性  a=1,b=4,输入参数Y2 Z1=a+b+Y2

        map.put("P1", a + b + c + d + e + O1 + O2);
        return AjaxResult.success(map);
    }

    /**
     * 调用服务A1B1C3
     */

    @GetMapping("/callA1B1C3")
    public AjaxResult callA1B1C3(Integer a, Integer b, Integer c, Integer d, Integer Z1, Integer Y2) {
        Map map = new HashMap<>();
        //R1=a+b+Z1,R2=b+c+Y2,R3=c+d+Y2

        map.put("R1", a + b + Z1);
        map.put("R2", b + c + Y2);
        map.put("R3", c + d + Y2);

        return AjaxResult.success(map);
    }

    /**
     * 调用服务B1C2D1
     */

    @GetMapping("/callB1C2D1")
    public AjaxResult callB1C2D1(Integer a, Integer O1, Integer P1) {
        Map map = new HashMap<>();

        map.put("Q1", a + O1 + P1);
        return AjaxResult.success(map);
    }

    /**
     * 调用服务B1B2B3C3D2
     */
    @GetMapping("/callB1B2B3C3D2")
    public AjaxResult callB1B2B3C3D2(Integer a, Integer O3, Integer Z1, Integer R1) {
        Map map = new HashMap<>();

        map.put("S1", a + O3 + Z1 + R1);
        return AjaxResult.success(map);
    }


    /**
     * 调用结束服务A1C3D3
     */
    @GetMapping("/callA1C3D3")
    public AjaxResult callA1C3D3(Integer a, Integer Y1, Integer R2, Integer R3) {
        Map map = new HashMap<>();

        map.put("T1", a + Y1 + R2 + R3);
        return AjaxResult.success(map);
    }


}
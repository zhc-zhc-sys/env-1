package com.ruoyi.web.controller.Node;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.system.domain.LiteFlowScript;
import com.ruoyi.system.service.ILiteFlowScriptService;
import groovy.lang.GroovyShell;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class GroovyScriptExecutor {

    @Resource
    private ILiteFlowScriptService liteFlowScriptService;

    public Object executeGroovyScript() {
        GroovyShell groovyShell = new GroovyShell();
        LiteFlowScript lite = liteFlowScriptService.getById(129L);
        try {
            groovyShell.setVariable("script", lite.getScriptData());
            System.out.println("setVariable-----------------"+lite.getScriptData());
            // 将外部参数绑定到脚本中
        //        bindings.forEach(groovyShell::setVariable);

        //        groovyShell.evaluate("");
            Object evaluate = groovyShell.evaluate(lite.getScriptData());
            System.out.println("evaluate:--------------" + evaluate);
            return evaluate;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
package com.ruoyi.web.controller.Node;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

@Component("node5")
public class Node5 extends NodeComponent {

    @Override
    public void process(){
        System.out.println("执行了Node5");
    }
}
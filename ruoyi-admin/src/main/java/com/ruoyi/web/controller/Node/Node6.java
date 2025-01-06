package com.ruoyi.web.controller.Node;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

@Component("node6")
public class Node6 extends NodeComponent {

    @Override
    public void process(){
        System.out.println("执行了Node6");
    }
}
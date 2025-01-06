package com.ruoyi.web.controller.Node;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

@Component("node3")
public class Node3 extends NodeComponent {

    @Override
    public void process(){
        ProcessDTO a = this.getCmpData(ProcessDTO.class);

        System.out.println("bbb:"+a.getNodeId());
        System.out.println("执行了Node3");
    }
}
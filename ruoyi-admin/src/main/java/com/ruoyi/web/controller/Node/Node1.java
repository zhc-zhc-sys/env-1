package com.ruoyi.web.controller.Node;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

@Component("node1")
public class Node1 extends NodeComponent {

    @Override
    public void process(){
        ProcessDTO a = this.getCmpData(ProcessDTO.class);
        System.out.println("bbb:"+a.getNodeId());
        System.out.println("执行了Node1");
    }
}
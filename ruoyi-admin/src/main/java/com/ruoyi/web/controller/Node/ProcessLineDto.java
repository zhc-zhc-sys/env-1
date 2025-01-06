package com.ruoyi.web.controller.Node;

import lombok.Data;

@Data
public class ProcessLineDto {
    private String id;
    private String sourceId;//连线起始点节点Id
    private String sourcePort;//连线起始点参数名称
    private String sourcePortType;//连线起始点参数名称

    private String targetId;//连线结束点节点Id
    private String targetPort;//连线结束点参数名称
    private String targetPortType;//连线起始点参数名称


}

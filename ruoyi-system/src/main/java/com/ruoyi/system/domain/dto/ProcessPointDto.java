package com.ruoyi.system.domain.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProcessPointDto {
    private String id;
    private String name;//节点名称
    private String dbId;//数据库ID
    private String url;//节点名称
    private String code;//节点名称

    private Integer markNumber;//
    private Boolean isDiscover;//

    private List<ProcessPointItemDto> inputParam;//输入参数
    private List<ProcessPointItemDto> outputParam;//输出参数

    private Map<String,String> attributeParamJson;//属性参数
}

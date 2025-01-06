package com.ruoyi.system.domain.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ProcessPointItemDto {
    private String name;//参数名称
    private String type;//参数类型
    private String group;
    private String value;//参数值
}

package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.core.CommonEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * 算子节点类 -- node
 */
@Getter
@Setter
@TableName("service_node")
public class ServiceNode extends CommonEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 操作名称
     */
    @TableField(value = "operator_name")
    private String operatorName;

    /** 操作类型 */
    @TableField(value = "operator_type")
    private String operatorType;

    /** 操作自定义code */
    @TableField(value = "operator_code")
    private String operatorCode;

    /** 自定义背景色 */
    @TableField(value = "bg_color")
    private String bgColor;

    /** 操作地址 ----  根据类型判断的操作项 */
    @TableField(value = "operator_address")
    private String operatorAddress;

    /** 操作命令 ----  根据类型判断的操作项 */
    @TableField(value = "operator_command")
    private String operatorCommand;

}

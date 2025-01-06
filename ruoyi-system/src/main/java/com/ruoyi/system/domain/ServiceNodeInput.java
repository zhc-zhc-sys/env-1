package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.CommonEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 算子节点类 -- node
 */
@Getter
@Setter
@TableName("service_node_input")
public class ServiceNodeInput extends CommonEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 操作名称
     */
    @TableField(value = "node_id")
    private Long nodeId;

    /** 输入代号 */
    @TableField(value = "input_code")
    private String inputCode;

    /** 输入名称 */
    @TableField(value = "input_name")
    private String inputName;

    /** 数据类型 file 文件  str字符串 number 数字等 */
    @TableField(value = "datatype")
    private String datatype;

    /** 操作方式 */
    @TableField(value = "inputoper")
    private String inputoper;

    /** 描述 */
    @TableField(value = "description")
    private String description;

}

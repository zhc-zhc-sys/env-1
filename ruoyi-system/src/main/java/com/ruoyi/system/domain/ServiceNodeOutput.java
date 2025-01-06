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
@TableName("service_node_output")
public class ServiceNodeOutput extends CommonEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 操作名称
     */
    @TableField(value = "node_id")
    private Long nodeId;

    /** 输入代号 */
    @TableField(value = "output_code")
    private String outputCode;

    /** 输入名称 */
    @TableField(value = "output_name")
    private String outputName;

    /** 数据输出路径 */
    @TableField(value = "path")
    private String path;

    /** 来源 */
    @TableField(value = "from")
    private String from;

}

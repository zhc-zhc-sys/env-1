package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
@Getter
@Setter
@TableName("service_process_node")
public class ServiceProcessNode implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long processId;
    private Long nodeId;
    private String operatorName;
    private String operatorAttribute;
    private String delFlag;
    private Long createUserId;
    private Long updateUserId;
    private Timestamp createTime;
    private Timestamp updateTime;

    // 省略 getter 和 setter 方法
}

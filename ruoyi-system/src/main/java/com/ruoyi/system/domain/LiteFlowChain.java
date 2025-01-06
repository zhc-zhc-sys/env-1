package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@TableName("liteflow_chain")
public class LiteFlowChain implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String applicationName;
    private String chainName;
    private String chainDesc;
    private Long status;
    private String elData;
    private String processJson;
    private Boolean enable;
    private Character delFlag;
    private Long createUserId;
    private Long updateUserId;
    private Date createTime;
    private Date updateTime;
}

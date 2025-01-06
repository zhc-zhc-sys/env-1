package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@TableName("liteflow_script")
public class LiteFlowScript implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String applicationName;
    private String scriptId;
    private String scriptName;
    private String scriptData;
    private String scriptType;
    private String scriptLanguage;
    private Boolean enable;
    private Character delFlag;
    private Long createUserId;
    private Long updateUserId;
    private Date createTime;
    private Date updateTime;
}

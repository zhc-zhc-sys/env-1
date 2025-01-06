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
@TableName("pt_evaluate_info")
public class PtEvaluateInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 工程名称 */
    private String evaluateName;

    /** 评估类型 - 大类 */
    private String evaluateType;

    /** 负责人 */
    private String leader;

    /** 开发架构 */
    private String framework;

    /** 文件地址 */
    private String evaluateFileUrl;

    /** 状态 0.未开始 1.执行中 2.已完成 3.执行出错 */
    private Integer status;

    private String evaluateDesc;

    /** 创建人 - 评论人 */
    private String createBy;

    /** 创建时间 */
    private Date createTime;

    private String updateBy;

    private Date updateTime;

    /** 是否删除 */
    private String delFlag;

}

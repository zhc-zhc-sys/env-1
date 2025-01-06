package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.LiteFlowScript;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface LiteFlowScriptMapper extends BaseMapper<LiteFlowScript> {
    @Update("delete from liteflow_script")
    void deleteAll();
}

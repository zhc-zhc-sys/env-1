package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.LiteFlowChain;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface LiteFlowChainMapper extends BaseMapper<LiteFlowChain> {
    @Update("delete from liteflow_chain")
    void deleteAll();
}

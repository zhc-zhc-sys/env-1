package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.LiteFlowChain;
import com.ruoyi.system.domain.dto.ProcessDTO;

public interface ILiteFlowChainService extends IService<LiteFlowChain> {

    void updateProcess(String json);


}

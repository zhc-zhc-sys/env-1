package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.domain.ServiceNode;
import com.ruoyi.system.mapper.ServiceNodeMapper;
import com.ruoyi.system.service.IServiceNodeService;
import org.springframework.stereotype.Service;

@Service
public class ServiceNodeServiceImpl extends ServiceImpl<ServiceNodeMapper, ServiceNode> implements IServiceNodeService {
}

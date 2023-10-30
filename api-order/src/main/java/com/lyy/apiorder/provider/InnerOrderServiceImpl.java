package com.lyy.apiorder.provider;


import com.lyy.apicommon.entity.Order;
import com.lyy.apicommon.service.InnerOrderService;
import com.lyy.apiorder.service.TOrderService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;


@DubboService
public class InnerOrderServiceImpl implements InnerOrderService {
    @Resource
    TOrderService orderService;
    @Override
    public List<Order> listTopBuyInterfaceInfo(int limit) {
        return orderService.listTopBuyInterfaceInfo(limit);
    }
}

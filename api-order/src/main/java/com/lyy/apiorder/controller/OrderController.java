package com.lyy.apiorder.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyy.apicommon.common.BaseResponse;
import com.lyy.apicommon.common.ResultUtils;
import com.lyy.apicommon.vo.OrderVO;
import com.lyy.apiorder.model.dto.OrderAddRequest;

import com.lyy.apiorder.model.dto.OrderQueryRequest;
import com.lyy.apiorder.service.TOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class OrderController {

    @Resource
    private TOrderService orderService;


    @PostMapping("/addOrder")
    public BaseResponse<OrderVO> interfaceTOrder(@RequestBody OrderAddRequest orderAddRequest,HttpServletRequest request){
        OrderVO order = orderService.addOrder(orderAddRequest,request);
        return ResultUtils.success(order);
    }


    @GetMapping("/list")
    public BaseResponse<Page<OrderVO>> listPageOrder(OrderQueryRequest orderQueryRequest, HttpServletRequest request){
        Page<OrderVO> orderPage = orderService.listPageOrder(orderQueryRequest, request);
        return ResultUtils.success(orderPage);
    }


}

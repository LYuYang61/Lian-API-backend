package com.lyy.springbootinit.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lyy.apicommon.entity.InterfaceCharging;
import com.lyy.apicommon.entity.InterfaceInfo;
import com.lyy.apicommon.entity.User;
import com.lyy.apicommon.service.ApiBackendService;
import com.lyy.springbootinit.common.ErrorCode;
import com.lyy.springbootinit.exception.BusinessException;
import com.lyy.springbootinit.model.dto.userinterface.UpdateUserInterfaceInfoDTO;
import com.lyy.springbootinit.mapper.InterfaceInfoMapper;
import com.lyy.springbootinit.mapper.UserInterfaceInfoMapper;
import com.lyy.springbootinit.mapper.UserMapper;
import com.lyy.springbootinit.service.InterfaceChargingService;
import com.lyy.springbootinit.service.UserInterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;


import javax.annotation.Resource;

/**
 * 作为服务提供方，提供远程调用接口
 */
@DubboService
public class ApiBackendServiceImpl implements ApiBackendService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Resource
    private InterfaceChargingService interfaceChargingService;


    /**
     * 根据accessKey和secretKey获取接口调用用户的信息，判断其是否有权限
     *
     * @param accessKey
     * @return
     */
    @Override
    public User getInvokeUser(String accessKey) {

        if (StringUtils.isBlank(accessKey)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accessKey", accessKey);
        return userMapper.selectOne(queryWrapper);
    }

    /**
     * 根据接口的url和接口的请求方式获取调用接口详情
     *
     * @param url
     * @param method
     * @return
     */
    @Override
    public InterfaceInfo getInterFaceInfo(String url, String method) {
        if (StringUtils.isBlank(url) || StringUtils.isBlank(method)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url", url);
        queryWrapper.eq("method", method);
        return interfaceInfoMapper.selectOne(queryWrapper);
    }

    /**
     * 统计接口调用次数
     *
     * @param userId
     * @param interfaceInfoId
     * @return
     */
    @Override
    public boolean invokeCount(long userId, long interfaceInfoId) {
        return userInterfaceInfoService.invokeCount(userId, interfaceInfoId);
    }

    /**
     * 获取接口的剩余调用次数
     *
     * @param userId
     * @param interfaceInfoId
     * @return
     */
    @Override
    public int getLeftInvokeCount(long userId, long interfaceInfoId) {
        return userInterfaceInfoService.getLeftInvokeCount(userId, interfaceInfoId);
    }

    /**
     * 根据id 获取接口信息
     *
     * @param interfaceId
     * @return
     */
    @Override
    public InterfaceInfo getInterfaceById(long interfaceId) {
        return interfaceInfoMapper.selectById(interfaceId);
    }

    /**
     * 根据id 获取接口的库存
     *
     * @param interfaceId
     * @return
     */
    @Override
    public int getInterfaceStockById(long interfaceId) {
        QueryWrapper<InterfaceCharging> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("interfaceId", interfaceId);
        InterfaceCharging interfaceCharging = interfaceChargingService.getOne(queryWrapper);
        if (interfaceCharging == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "接口不存在");
        }
        return Integer.parseInt(interfaceCharging.getAvailablePieces());
    }

    /**
     * 扣减库存
     *
     * @param interfaceId
     * @param num
     * @return
     */
    @Override
    public boolean updateInterfaceStock(long interfaceId, Integer num) {
        UpdateWrapper<InterfaceCharging> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("availablePieces = availablePieces - " + num)
                .eq("interfaceId", interfaceId).gt("availablePieces", num);

        return interfaceChargingService.update(updateWrapper);
    }

    /**
     * 订单支付超时，回滚库存
     *
     * @param interfaceId
     * @param num
     * @return
     */
    @Override
    public boolean recoverInterfaceStock(long interfaceId, Integer num) {
        UpdateWrapper<InterfaceCharging> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("availablePieces = availablePieces + " + num)
                .eq("interfaceId", interfaceId);
        return interfaceChargingService.update(updateWrapper);
    }

    /**
     * 给指定用户分配接口调用次数
     *
     * @param userId
     * @param interfaceId
     * @param num
     * @return
     */
    @Override
    public boolean updateUserInterfaceInvokeCount(long userId, long interfaceId, int num) {
        UpdateUserInterfaceInfoDTO userInterfaceInfoDTO = new UpdateUserInterfaceInfoDTO();
        userInterfaceInfoDTO.setUserId(userId);
        userInterfaceInfoDTO.setInterfaceId(interfaceId);
        userInterfaceInfoDTO.setLockNum((long) num);
        return userInterfaceInfoService.updateUserInterfaceInfo(userInterfaceInfoDTO);
    }


}

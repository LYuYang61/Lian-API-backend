package com.lyy.springbootinit.provider;

import com.lyy.apicommon.entity.User;
import com.lyy.apicommon.service.InnerUserService;
import com.lyy.springbootinit.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    private UserService userService;

    /**
     * 根据用户id获取用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public User getUserById(Long userId) {
        return userService.getById(userId);
    }
}

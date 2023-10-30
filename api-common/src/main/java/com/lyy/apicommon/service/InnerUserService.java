package com.lyy.apicommon.service;

import com.lyy.apicommon.entity.User;

public interface InnerUserService {


    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
    User getUserById(Long userId);
}

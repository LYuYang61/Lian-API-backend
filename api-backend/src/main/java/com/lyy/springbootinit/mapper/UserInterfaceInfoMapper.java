package com.lyy.springbootinit.mapper;

import com.lyy.apicommon.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyy.springbootinit.model.vo.UserInterfaceInfoAnalysisVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.lyy.springbootinit.model.entity.UserInterfaceInfo
 */
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {


    List<UserInterfaceInfoAnalysisVo> listTopInterfaceInfo(@Param("size") int size);

    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);
}





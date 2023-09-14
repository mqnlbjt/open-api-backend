package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.mapper.UserInterfaceInfoMapper;
import com.yupi.springbootinit.model.entity.UserInterfaceInfo;
import com.yupi.springbootinit.service.UserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;
import service.innerUserInterfaceInfoService;

import javax.annotation.Resource;

@DubboService
public class innerUserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
        implements innerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Override
    public void validUserInterfaceInfo(model.entity.UserInterfaceInfo userInterfaceInfo, boolean b) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long userId = userInterfaceInfo.getUserId();
        Long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();
        Integer totalNum = userInterfaceInfo.getTotalNum();
        // 创建时，参数不能为空
        if (b) {
            if (interfaceInfoId <= 0 || userId <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口或用户不存在");
            }
        }

        if (totalNum < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口调用次数不能为0");
        }

    }

    @Override
    public synchronized boolean invokeCount(Long interfaceInfoId, Long userId) {
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("interfaceInfoId",interfaceInfoId);
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoMapper.selectOne(queryWrapper);
        Integer leftNum = userInterfaceInfo.getLeftNum();
        if (leftNum <= 0){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR,"接口调用次数不足，请充值");
        }
        return userInterfaceInfoService.invokeCount(interfaceInfoId,userId);
    }
}

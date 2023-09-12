package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.mapper.InterfaceInfoMapper;
import com.yupi.springbootinit.model.entity.InterfaceInfo;
import com.yupi.springbootinit.service.InterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 接口信息(InterfaceInfo)表服务实现类
 *
 * @author makejava
 * @since 2023-09-04 16:37:13
 */
@Service("interfaceInfoService")
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo> implements InterfaceInfoService {

    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;
    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String url = interfaceInfo.getUrl();
        String name = interfaceInfo.getName();
        String description = interfaceInfo.getDescription();
        String requestHeader = interfaceInfo.getRequestHeader();
        String responseHeader = interfaceInfo.getResponseHeader();
        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(url, name, description, requestHeader, responseHeader), ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验
        if (StringUtils.isNotBlank(url) && url.length() > 512) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "url过长");
        }

        if (StringUtils.isNotBlank(name) && name.length() > 256) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口名字过长");
        }

        if (StringUtils.isNotBlank(description) && description.length() > 256) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口描述过长");
        }

    }

    @Override
    public model.entity.InterfaceInfo getInterfaceInfo(String url, String method) {
        if (StringUtils.isBlank(url) || StringUtils.isBlank(method)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Path",url);
        queryWrapper.eq("method",method);
        model.entity.InterfaceInfo interfaceInfoRes = new model.entity.InterfaceInfo();
        InterfaceInfo interfaceInfo = interfaceInfoMapper.selectOne(queryWrapper);
        BeanUtils.copyProperties(interfaceInfo,interfaceInfoRes);
        return interfaceInfoRes;
    }


}

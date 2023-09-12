package com.yupi.springbootinit.service.impl;

import com.yupi.springbootinit.service.InterfaceInfoService;
import model.entity.InterfaceInfo;
import org.apache.dubbo.config.annotation.DubboService;
import service.innerInterfaceInfoService;

import javax.annotation.Resource;



public class innerInterfaceInfoServiceImpl implements innerInterfaceInfoService {
    @Resource
    private InterfaceInfoService interfaceInfoService;
    @Override
    public InterfaceInfo getInterfaceInfo(String s, String s1) {
        return interfaceInfoService.getInterfaceInfo(s,s1);
    }
}

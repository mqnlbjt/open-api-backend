package com.yupi.springbootinit.service.impl;

import com.yupi.springbootinit.service.UserService;
import model.entity.User;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import service.InnerUserService;

import javax.annotation.Resource;


@DubboService
public class InnerUserServiceImpl implements InnerUserService {
    @Resource
    private UserService userService;

    @Override
    public User getInvokeUser(String ak, String sk) {
        com.yupi.springbootinit.model.entity.User invokeUser = userService.getInvokeUser(ak);
        User user = new User();
        BeanUtils.copyProperties(invokeUser,user);
        return user;
    }
}

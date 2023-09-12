package com.yupi.springbootinit.service;

import com.yupi.springbootinit.service.impl.InnerUserServiceImpl;
import model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class InnerUserServiceTest {
    @Resource
    InnerUserServiceImpl innerUserService;
    @Test
    public void test(){
        String sk = "quan";
        String ak = "quan";
        User invokeUser = innerUserService.getInvokeUser(ak, sk);
        System.out.println(invokeUser);
    }

}

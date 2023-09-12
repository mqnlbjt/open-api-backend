package com.yupi.springbootinit.service;

import com.yupi.springbootinit.service.impl.innerInterfaceInfoServiceImpl;
import model.entity.InterfaceInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import service.innerInterfaceInfoService;

import javax.annotation.Resource;

@SpringBootTest
public class innerInterfaceInfoServiceTest {
    @Resource
    innerInterfaceInfoServiceImpl innerInterfaceInfoService;
    @Test
    public void test(){
        String url = "www.wesley-trantow.com";
        String method = "黄雪松";
        InterfaceInfo interfaceInfo = innerInterfaceInfoService.getInterfaceInfo(url, method);
        System.out.println(interfaceInfo);
    }
}

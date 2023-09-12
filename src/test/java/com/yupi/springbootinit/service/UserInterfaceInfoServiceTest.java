package com.yupi.springbootinit.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import service.innerUserInterfaceInfoService;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserInterfaceInfoServiceTest {
    @Resource
    private innerUserInterfaceInfoService userInterfaceInfoService;
    @Test
    void invokeCount() {
        boolean res = userInterfaceInfoService.invokeCount(1L, 1L);
        Assertions.assertTrue(res);
    }
}
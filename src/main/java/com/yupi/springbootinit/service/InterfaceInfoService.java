package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.entity.InterfaceInfo;



/**
 * 接口信息(InterfaceInfo)表服务接口
 *
 * @author makejava
 * @since 2023-09-04 16:37:13
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
    model.entity.InterfaceInfo getInterfaceInfo(String path, String method);
}

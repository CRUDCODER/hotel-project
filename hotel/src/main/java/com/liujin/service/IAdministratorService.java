package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.Administrator;

/**
 * @author liujin
 * @date created in 2020/1/28 13:45
 */
public interface IAdministratorService extends IService<Administrator> {
    Integer login(Administrator administrator);
}

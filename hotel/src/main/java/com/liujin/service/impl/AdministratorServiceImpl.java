package com.liujin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.Administrator;
import com.liujin.mapper.AdministratorMapper;
import com.liujin.service.IAdministratorService;
import org.springframework.stereotype.Service;

/**
 * @author liujin
 * @date created in 2020/1/28 13:45
 */
@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements IAdministratorService {
    @Override
    public Integer login(Administrator administrator) {
        return getBaseMapper().login(administrator);
    }
}

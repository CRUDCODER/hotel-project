package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.Administrator;
import org.apache.ibatis.annotations.Select;

/**
 * @author liujin
 * @date created in 2020/1/28 13:44
 */
public interface AdministratorMapper extends BaseMapper<Administrator> {
    @Select("select count(*) from administrator where user_account=#{userAccount} and user_password=#{userPassword} ")
    int login(Administrator administrator);
}

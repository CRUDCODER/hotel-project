package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.Reception;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/31 10:28
 */
public interface IReceptionService extends IService<Reception> {
    List<Reception> fetchReception(Reception reception);


    Integer guestReception(Reception reception);

    Integer memberReception(Reception reception);
}

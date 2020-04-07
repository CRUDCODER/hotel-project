package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.Room;
import com.liujin.entity.Special;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/2/2 13:03
 */
public interface ISpecialService extends IService<Special> {

    List<Special> fetchSpecial();


    /*******添加特价房*******/
    boolean increaseSpecialRoom(Special special);

    List<Room> selectTodayIsSpecial();
}

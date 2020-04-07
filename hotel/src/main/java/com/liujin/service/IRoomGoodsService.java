package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.RoomGoods;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/29 19:18
 */
public interface IRoomGoodsService extends IService<RoomGoods> {
    List<RoomGoods> fetchRoomGoods();

    boolean increaseRoomGoods(RoomGoods roomGoods);

    String fetchGoodsByRoomNumber(RoomGoods roomGoods);
}

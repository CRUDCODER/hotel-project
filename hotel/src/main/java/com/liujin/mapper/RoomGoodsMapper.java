package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.RoomGoods;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/29 19:18
 */
public interface RoomGoodsMapper extends BaseMapper<RoomGoods> {

    List<RoomGoods> fetchRoomGoods();

    List<RoomGoods> fetchGoodsByRoomNumber(RoomGoods roomGoods);
}

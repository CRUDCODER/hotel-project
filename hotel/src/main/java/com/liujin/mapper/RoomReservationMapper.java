package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.RoomReservation;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/30 13:36
 */
public interface RoomReservationMapper extends BaseMapper<RoomReservation> {

    List<RoomReservation> fetchRoomReservation(RoomReservation roomReservation);
    Integer selectMaxReservationNumber();

    /**
     * 统计预定之后的实际入住情况
     * @return
     */
    List<RoomReservation> statistical();
}

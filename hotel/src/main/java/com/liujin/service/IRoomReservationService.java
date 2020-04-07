package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.RoomReservation;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/30 13:37
 */
public interface IRoomReservationService extends IService<RoomReservation> {
    List<RoomReservation> fetchRoomReservation(RoomReservation roomReservation);

    boolean reservationRoom(RoomReservation roomReservation);

    Integer selectMaxReservationNumber();

    List<RoomReservation> statistical();
}

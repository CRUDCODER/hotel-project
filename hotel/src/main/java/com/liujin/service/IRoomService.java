package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.Room;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/29 11:58
 */
public interface IRoomService  extends IService<Room> {
    List<Room> fetchRooms(Room room);

    boolean modifyRoomFlag();
}

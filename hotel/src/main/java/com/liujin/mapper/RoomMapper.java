package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.Room;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/29 11:58
 */
public interface RoomMapper extends BaseMapper<Room> {

    List<Room> fetchRooms(Room room);
}

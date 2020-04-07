package com.liujin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.Room;
import com.liujin.entity.RoomReservation;
import com.liujin.mapper.RoomMapper;
import com.liujin.service.IRoomReservationService;
import com.liujin.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/29 11:58
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {
    @Autowired
    private IRoomReservationService roomReservationService;

    @Override
    public List<Room> fetchRooms(Room room) {
        return getBaseMapper().fetchRooms(room);
    }



    /**
     * 查询预定记录 首先查询明天是否有房间已经被预定  如果有则将房间状态改为已预定
     * 也可以将此步骤改为定时器  每天12点定时更新查询
     * 只查询明天是否已经被预定  不查询后天及以后的预定记录
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean modifyRoomFlag() {
        Date date = new Date();
        date.setDate(date.getDate()+1);
        String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
        List<RoomReservation> reservation_date = roomReservationService.getBaseMapper().selectList(new QueryWrapper<RoomReservation>().eq("reservation_date", format));
        if (reservation_date!=null){
            for (RoomReservation roomReservation : reservation_date) {
                update(new UpdateWrapper<Room>().set("room_flag",2).eq("room_id",roomReservation.getRoomId()));
            }
        }
        return true;
    }
}

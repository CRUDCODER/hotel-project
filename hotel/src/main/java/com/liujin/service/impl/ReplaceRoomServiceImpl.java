package com.liujin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.Live;
import com.liujin.entity.Reception;
import com.liujin.entity.ReplaceRoom;
import com.liujin.entity.Room;
import com.liujin.mapper.ReplaceRoomMapper;
import com.liujin.service.ILiveService;
import com.liujin.service.IReceptionService;
import com.liujin.service.IReplaceRoomService;
import com.liujin.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author liujin
 * @date created in 2020/2/1 13:31
 */
@Service
public class ReplaceRoomServiceImpl
        extends ServiceImpl<ReplaceRoomMapper, ReplaceRoom>
        implements IReplaceRoomService
{
    @Autowired
    private IRoomService roomService;
    @Autowired
    private ILiveService liveService;
    @Autowired
    private IReceptionService receptionService;
    @Override
    public List<ReplaceRoom> fetchReplaceRoom(ReplaceRoom replaceRoom) {
        return getBaseMapper().fetchReplaceRoom(replaceRoom);
    }

    /**
     * 客户更换房间
     * 需要更改用户入住的房间编号
     * 更改原房间状态
     * 更改更换房间状态
     * @param replaceRoom
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean replaceRoom(ReplaceRoom replaceRoom) {
        replaceRoom.setReplaceDate(new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(new Date()));
        if (replaceRoom.insert()){
            //更改更换房间的状态
            roomService.update(new UpdateWrapper<Room>().set("room_flag",1).eq("room_id",replaceRoom.getRoomId()));
            //首先根据入住编号查出入住信息
            //然后根据入住信息中的接待编号查询入住信息
            Live live = liveService.getById(replaceRoom.getLiveId());
            Reception reception = receptionService.getById(live.getReceptionId());
            //更改原房间状态
            roomService.update(new UpdateWrapper<Room>()
                    .set("room_flag",0).
                    eq("room_id",reception.getRoomId()));
            //更改入住信息中的房间编号  实际更改前台接待中的房间编号
            reception.setRoomId(replaceRoom.getRoomId());
            reception.updateById();
            return  true;
        }
        return false;
    }
}

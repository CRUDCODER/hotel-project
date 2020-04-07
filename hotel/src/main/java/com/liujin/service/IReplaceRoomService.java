package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.ReplaceRoom;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/2/1 13:31
 */
public interface IReplaceRoomService extends IService<ReplaceRoom> {

    List<ReplaceRoom> fetchReplaceRoom(ReplaceRoom replaceRoom);


    boolean replaceRoom(ReplaceRoom replaceRoom);
}

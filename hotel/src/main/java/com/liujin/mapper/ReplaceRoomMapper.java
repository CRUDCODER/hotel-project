package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.ReplaceRoom;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/2/1 13:30
 */
public interface ReplaceRoomMapper extends BaseMapper<ReplaceRoom> {

    List<ReplaceRoom> fetchReplaceRoom(ReplaceRoom replaceRoom);
}

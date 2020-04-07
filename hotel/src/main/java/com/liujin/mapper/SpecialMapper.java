package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.Room;
import com.liujin.entity.Special;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/2/2 13:02
 */
public interface SpecialMapper extends BaseMapper<Special> {

    List<Special> fetchSpecial();

    /**
     * 查询不是今天特价的编号
     * @return
     */
    List<Special> notIsTodaySpecial(@Param("date") String date);

    /**
     * 查询今天特价房
     * 要求查出特价后的价格
     * 条件:今日特价 并且为未出租状态
     * @return
     */
    List<Room> selectTodayIsSpecial();
}

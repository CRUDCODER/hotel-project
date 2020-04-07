package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.Live;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/31 10:29
 */
public interface LiveMapper extends BaseMapper<Live> {

    List<Live> fetchLive(Live live);



    /********客户换房  需要查出今天已经登记入住的客户**********/
    List<Live> selectLiveToday();
    /*******客户续住  需要查出所有正在居中的客户*******/
    List<Live> selectLiveAll();

    /**************查出所有已经过了退房日期的客户********************/
    List<Live> selectAllExpire(@Param("date")String date);
}

package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.Live;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/31 10:30
 */
public interface ILiveService extends IService<Live> {

    boolean guestLive(Live live);

    boolean memberLive(Live live);

    List<Live> fetchLive(Live live);


    List<Live> selectLiveToday();

    List<Live> selectLiveAll();

    /**************客户退房查询接口******************/

    float queryLiveMoneyByLiveId(Integer liveId);


    /**************查出所有已经过了退房日期的客户********************/
    List<Live> selectAllExpire(@Param("date")String date);
}

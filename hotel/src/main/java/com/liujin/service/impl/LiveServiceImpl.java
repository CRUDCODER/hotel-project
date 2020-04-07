package com.liujin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.*;
import com.liujin.mapper.LiveMapper;
import com.liujin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/31 10:30
 */
@Service
public class LiveServiceImpl extends ServiceImpl<LiveMapper, Live> implements ILiveService {
    @Autowired
    private IRoomService roomService;
    @Autowired
    private IReceptionService receptionService;
    @Autowired
    private IRoomReservationService roomReservationService;
    @Autowired
    private IPayFeeService payFeeService;
    /**
     * 散客入住接口
     * 入住日期为当前系统时间
     * 入住成功之后需要修改房间状态为已租出
     * 还需要根据接待编号查出房间编号
     *
     * 还需要插入一条交费记录
     * @param live
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean guestLive(Live live) {
        //设置退房日期 明天12点钟整为退房日期
        Date date=new Date();
        date.setDate(date.getDate()+1);
        date.setHours(12);
        date.setSeconds(00);
        date.setMinutes(00);
        live.setEndDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        live.setLiveDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        live.setLiveFlag(1);
        if (live.insert()){
            //根据接待编号查出接待信息
            Reception reception = receptionService.getById(live.getReceptionId());
            Integer roomId = reception.getRoomId();
            //修改房间状态
            roomService.update(new UpdateWrapper<Room>().set("room_flag",1).eq("room_id",roomId));
            //插入交费记录
            PayFee payFee=new PayFee();
            payFee.setPayFeeCategory("散客登记入住缴纳房间价格+押金");
            payFee.setPayFeeMoney(live.getLiveMoney());
            payFee.setPayFeeName(live.getGuestName());
            payFee.setPayFeePhone(live.getGuestPhone());
            payFee.setPayFeeDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            payFee.insert();
            return true;
        }
        return false;
    }

    /**
     * 会员入住接口
     * 入住日期为系统时间
     * 入住成功之后修改房间状态
     * 修改预定状态为已入住
     * 插入一条交费记录
     * @param live
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean memberLive(Live live) {
        //设置退房日期 明天12点钟整为退房日期
        Date date=new Date();
        date.setDate(date.getDate()+1);
        date.setHours(12);
        date.setSeconds(00);
        date.setMinutes(00);
        live.setEndDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        live.setLiveDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        live.setLiveFlag(1);
        if(live.insert()){
            //修改房间状态
            Reception reception = receptionService.getById(live.getReceptionId());
            Integer roomId = reception.getRoomId();
            roomService.update(new UpdateWrapper<Room>().set("room_flag",1).eq("room_id",roomId));
            //修改预定已入住
            roomReservationService.update(new UpdateWrapper<RoomReservation>().set("reservation_flag",1).eq("reservation_id",reception.getReservationId()));
            //插入交费记录
            PayFee payFee=new PayFee();
            payFee.setPayFeeCategory("会员缴纳房间押金");
            payFee.setPayFeeMoney(live.getLiveMoney());
            payFee.setPayFeeDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            payFee.setPayFeeName(reception.getGuestName());
            payFee.setPayFeePhone(reception.getGuestPhone());
            payFee.insert();
            return true;
        }
        return false;
    }

    @Override
    public List<Live> fetchLive(Live live) {
        return getBaseMapper().fetchLive(live);
    }

    @Override
    public List<Live> selectLiveToday() {
        return getBaseMapper().selectLiveToday();
    }

    @Override
    public List<Live> selectLiveAll() {
        return getBaseMapper().selectLiveAll();
    }

    /**
     * 此接口功能为 :
     * 根据入住编号查询出客户缴纳的押金
     * 缴纳的押金为：入住缴纳金额-房间价格=缴纳押金
     * 此上为散客算法
     *
     * 如果客户为会员则 入住缴纳押金即为押金 因为房间价格已经在预约时付过
     * 入住时只需缴纳押金 无需再次缴纳房间价格
     *
     * 所以需要根据 入住编号查询接待信息 看接待信息中该客户是否为会员  然后根据是否为会员计算押金
     * @param liveId
     * @return
     */
    @Override
    public float queryLiveMoneyByLiveId(Integer liveId) {
        float liveMoney=0;
        //1.根据入住编号查询出入住信息
        Live live = getById(liveId);
        //2.根据入住信息中的接待编号 查询出接待信息
        Reception reception = receptionService.getById(live.getReceptionId());
        //3.判断用户是否为会员 即判断接待信息中的预定编号是否为空 为空说明是散客   不为空说明是会员
        if (reception.getReservationId()!=null){
            //接待编号不为空 说明此接待为会员 直接返回押金金额
            liveMoney=live.getLiveMoney();
        }else {
            //否则接待的为散客   散客缴纳的押金为房间价格+押金  首先需要扣除房间价格  剩下的即为押金
            //根据接待编号中的房间编号查出房间价格
            Room room = roomService.getById(reception.getRoomId());
            //剩余押金=入住缴纳押金-房间价格
            liveMoney=live.getLiveMoney()-room.getRoomMoney();
        }
        return liveMoney;
    }

    @Override
    public List<Live> selectAllExpire(String date) {
        return getBaseMapper().selectAllExpire(date);
    }
}

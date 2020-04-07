package com.liujin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.*;
import com.liujin.mapper.LeaveMapper;
import com.liujin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author liujin
 * @date created in 2020/2/2 9:23
 */
@Service
public class LeaveServiceImpl
    extends ServiceImpl<LeaveMapper, Leave>
    implements ILeaveService
{
    @Autowired
    private IRoomService roomService;
    @Autowired
    private ILiveService liveService;
    @Autowired
    private IReceptionService receptionService;
    @Autowired
    private ICheckoutService checkoutService;
    @Override
    public List<Leave> fetchLeave(Leave leave) {
        return getBaseMapper().fetchLeave(leave);
    }

    /***
     * 客户办理离店
     * 1.新增一条离店记录
     * 2.改变入住状态为已退房
     * 3.改变房间状态为未出租
     * 4.新增一条退房结算记录(保留)
     *
     *  @Transactional事务回滚  如果出错 则全部回滚
     * @param leave
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean guestLeave(Leave leave) {
        //1.新增一条离店记录
        //1.1离店时间为当前系统时间
        leave.setLeaveDate(new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(new Date()));
        if (getBaseMapper().insertLeave(leave)){
           //新增记录成功 进行以下操作
            //2.改变入住状态为已退房
            //2.1根据入住编号查出入住信息 改变入住状态 然后修改
            Live live = liveService.getById(leave.getLiveId());
            live.setLiveFlag(0);
            live.updateById();
            //3.修改房间状态为未出租
            //3.1根据入住信息的接待编号查出接待信息
            Reception reception = receptionService.getById(live.getReceptionId());
            //3.2根据接待信息中的房间编号查出房间信息
            Room room = roomService.getById(reception.getRoomId());
            room.setRoomFlag(0);
            room.updateById();
            //4.新增一条退房结算记录
            Checkout checkout =new Checkout();
            checkout.setCheckoutDate(new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(new Date()));
            checkout.setCheckoutGuest(reception.getGuestName());
            checkout.setCheckoutPhone(reception.getGuestPhone());
            checkout.setCheckoutMoney(leave.getRefundMoney());
            checkoutService.save(checkout);
            return true;
        }
        return false;
    }
}

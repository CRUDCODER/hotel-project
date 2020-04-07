package com.liujin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.*;
import com.liujin.mapper.RoomReservationMapper;
import com.liujin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/30 13:37
 */
@Service
public class RoomReservationServiceImpl extends ServiceImpl<RoomReservationMapper, RoomReservation> implements IRoomReservationService {
    @Autowired
    private IRoomService roomService;
    @Autowired
    private IMemberConsumeService memberConsumeService;
    @Autowired
    private IMemberService memberService;
    @Autowired
    private IMemberExchangeService memberExchangeService;
    @Autowired
    private IPayFeeService payFeeService;
    @Override
    public List<RoomReservation> fetchRoomReservation(RoomReservation roomReservation) {
        return getBaseMapper().fetchRoomReservation(roomReservation);
    }

    /**
     * 会员预定房间
     * 首先根据房号查出房间编号
     * 设置房间预定信息的房间编号(查出的房间编号) 支付时间(当前时间) 入住状态(0 未入住)
     * 预定成功之后 改变房间状态为已出租 防止其他客户再次预定
     *
     * 成功之后还需要判断会员余额是否小于需要支付的金额 如果小于则抛出异常回滚 否则减少对应的余额
     *
     * 还需判断用户是否使用了优惠卷 如果使用则需要让优惠卷变为已使用状态
     *
     * 预定成功之后需要在会员消费记录插入一条记录
     *108 2-3  2条记录 房间预定
     * 还需要往收银管理的客户交费记录插入一条记录
     * @param roomReservation
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean reservationRoom(RoomReservation roomReservation) {
        Integer integer = selectMaxReservationNumber();
        roomReservation.setReservationNumber(integer+1);
        Room room = roomService.getBaseMapper().selectOne(new QueryWrapper<Room>().eq("room_number", roomReservation.getRoomNumber()));
        roomReservation.setRoomId(room.getRoomId());
        roomReservation.setPayDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        roomReservation.setReservationFlag(0);

        //判断用户是否使用了优惠卷 需要判断优惠卷名称是否为空 为空说明没有使用 不为空说明使用了
        MemberExchange memberExchange=null;
        if (roomReservation.getCouponsName()!=""){
             memberExchange = memberExchangeService.queryMemberEchangeByCouponsName(roomReservation.getCouponsName());
            roomReservation.setExchangeId(memberExchange.getExchangeId());
        }
        if (roomReservation.insert()){
            //减少会员余额  首先查出会员当前余额  如果余额小于需要支付的金额 则抛出异常 回滚
            Member member1 = memberService.getById(roomReservation.getMemberId());
            if (member1.getMemberMoney()<roomReservation.getPayMoney()){
                throw  new RuntimeException("余额不足,支付失败!");
            }
            Member member=new Member();
            member.setMemberId(roomReservation.getMemberId());
            member.setMemberMoney(roomReservation.getPayMoney());
            memberService.reduceMemberMoney(member);
            //修改房间状态为已出租
            roomService.update(new UpdateWrapper<Room>().set("room_flag",2).eq("room_id",room.getRoomId()));
            //修改优惠卷使用状态为已使用
            memberExchange.setExchangeFlag(0);
            memberExchangeService.updateById(memberExchange);
            //插入一条会员消费记录
            //需要会员编号 消费类型  消费金额  消费时间
            MemberConsume memberConsume=new MemberConsume();
            memberConsume.setMemberId(roomReservation.getMemberId());
            memberConsume.setConsumeCategory("预定房间");
            memberConsume.setConsumeMoney(roomReservation.getPayMoney());
            memberConsume.setConsumeDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            memberConsumeService.save(memberConsume);
            //插入一条交费记录
            PayFee payFee=new PayFee();
            payFee.setPayFeeName(member1.getMemberName());
            payFee.setPayFeePhone(member1.getMemberPhone());
            payFee.setPayFeeMoney(roomReservation.getPayMoney());
            payFee.setPayFeeCategory("预定房间");
            payFee.setPayFeeDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            payFee.insert();
            return true;
        }
        return false;
    }

    @Override
    public Integer selectMaxReservationNumber() {
        return getBaseMapper().selectMaxReservationNumber();
    }

    @Override
    public List<RoomReservation> statistical() {
        List<RoomReservation> statistical = getBaseMapper().statistical();
        for (RoomReservation roomReservation : statistical) {
            if (roomReservation.getReservationFlag()==0){
                roomReservation.setName("未入住");
            }else if (roomReservation.getReservationFlag()==1){
                roomReservation.setName("已入住");
            }else if (roomReservation.getReservationFlag()==2){
                roomReservation.setName("已取消");
            }
        }
        return statistical;
    }
}

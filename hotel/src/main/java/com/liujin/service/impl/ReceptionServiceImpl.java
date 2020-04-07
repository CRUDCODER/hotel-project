package com.liujin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.Member;
import com.liujin.entity.Reception;
import com.liujin.entity.Room;
import com.liujin.entity.RoomReservation;
import com.liujin.mapper.ReceptionMapper;
import com.liujin.service.IMemberService;
import com.liujin.service.IReceptionService;
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
 * @date created in 2020/1/31 10:28
 */
@Service
public class ReceptionServiceImpl extends ServiceImpl<ReceptionMapper,Reception> implements IReceptionService {
    @Autowired
    private IRoomReservationService roomReservationService;
    @Autowired
    private IMemberService memberService;
    @Autowired
    private IRoomService roomService;
    @Override
    public List<Reception> fetchReception(Reception reception) {
        return getBaseMapper().fetchReception(reception);
    }

    /**
     * 散客接待和入住
     * 首先设置flag为0 表示为散客
     * 接待日期为当前系统时间
     *
     * @param reception
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer guestReception(Reception reception) {
        reception.setGuestFlag(0);
        reception.setReceptionDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        Integer receptionId=0;
        if (reception.insert()){
             receptionId = reception.getReceptionId();
        }
        return receptionId;
    }

    /**
     * 会员接待
     * 会员是预定过的
     * 首先根据预定编号查出会员编号和房间编号
     * 然后把会员信息放进接待信息实体
     * @param reception
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer memberReception(Reception reception) {
        reception.setGuestFlag(1);
        reception.setReceptionDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        RoomReservation reservation = roomReservationService.getById(reception.getReservationId());
        Member member = memberService.getById(reservation.getMemberId());
        Room room = roomService.getById(reservation.getRoomId());
        reception.setGuestName(member.getMemberName());
        reception.setGuestCard(member.getMemberCard());
        reception.setGuestPhone(member.getMemberPhone());
        reception.setRoomId(room.getRoomId());
        Integer receptionId=0;
        if (reception.insert()){
            receptionId=reception.getReceptionId();
            return receptionId;
        }
        return receptionId;
    }
}

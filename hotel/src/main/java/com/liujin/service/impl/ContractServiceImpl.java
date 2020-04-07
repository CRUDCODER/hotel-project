package com.liujin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.*;
import com.liujin.mapper.ContractMapper;
import com.liujin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author liujin
 * @date created in 2020/2/1 17:45
 */
@Service
public class ContractServiceImpl
    extends ServiceImpl<ContractMapper, Contract>
    implements IContractService
{
    @Autowired
    private ILiveService liveService;
    @Autowired
    private IRoomService roomService;
    @Autowired
    private IReceptionService receptionService;
    @Autowired
    private IPayFeeService payFeeService;
    @Override
    public List<Contract> fetchContract(Contract contract) {
        return getBaseMapper().fetchContract(contract);
    }

    /**
     * 客户续住接口
     *
     * 该接口主要实现:
     * 1.新增一条客户续住记录  续住日期为当前系统时间
     * 2.更改客户入住信息的退房日期,需要查出客户退房时间
     *    然年在退房日期的基础上延迟一天
     * 3.更改房间状态为已出租，不管当前房间状态是否已经是已出租状态，
     *   防止过了退房时间 房屋状态变为已延期，续住时状态还是已延期
     * 4.插入一条交费记录
     * @param contract
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean guestContract(Contract contract) {
        //续住日期为当前系统时间
        contract.setContractDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //插入续住记录
        if (contract.insert()){
            //如果插入记录成功 则进行以下操作:
            //更改客户入住信息的退房日期,退房日期在原本退房日期的基础上延迟一天
            Live live = liveService.getById(contract.getLiveId());
            String endDate=live.getEndDate();
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=null;
            try {
                date=formatter.parse(endDate);
                date.setDate(date.getDate()+1);
                String newDate=formatter.format(date);
                liveService.update(new UpdateWrapper<Live>().set("end_date",newDate).eq("live_id",live.getLiveId()));
            } catch (ParseException e) {
                throw new RuntimeException("时间格式异常");
            }
            //更改房间状态为已出租，不管当前房间状态是否已经是已出租状态，防止过了退房时间 房屋状态变为已延期，续住时状态还是已延期
            //首先需要获取房间编号 只有根据入住编号 查询接待信息  获取接待信息中的房间编号
            Reception reception = receptionService.getById(live.getReceptionId());
            roomService.update(new UpdateWrapper<Room>().set("room_flag",1).eq("room_id",reception.getRoomId()));
            //插入交费记录
            PayFee payFee=new PayFee();
            payFee.setPayFeeCategory("客户续住");
            payFee.setPayFeeMoney(contract.getContractMoney());
            payFee.setPayFeeDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            payFee.setPayFeeName(reception.getGuestName());
            payFee.setPayFeePhone(reception.getGuestPhone());
            payFeeService.save(payFee);
            return true;
        }
        return false;
    }
}

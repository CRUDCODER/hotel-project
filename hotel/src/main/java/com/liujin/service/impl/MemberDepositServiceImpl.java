package com.liujin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.Member;
import com.liujin.entity.MemberDeposit;
import com.liujin.mapper.MemberDepositMapper;
import com.liujin.service.IMemberDepositService;
import com.liujin.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/30 12:37
 */
@Service
public class MemberDepositServiceImpl extends ServiceImpl<MemberDepositMapper, MemberDeposit> implements IMemberDepositService {
    @Autowired
    private IMemberService memberService;

    @Override
    public List<MemberDeposit> fetchMemberDeposit(MemberDeposit memberDeposit) {
        return getBaseMapper().fetchMemberDeposit(memberDeposit);
    }

    /**
     * 会员充值功能
     * 首先获取充值时间   充值时间为当前系统时间  获取到时分秒
     * 如果充值成功 则需要修改会员卡上余额  此时余额为卡上余额+充值金额
     * 如果充值失败则回滚
     * @param memberDeposit
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean increaseMemberDeposit(MemberDeposit memberDeposit) {
        memberDeposit.setDepositDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if (memberDeposit.insert()){
            Member member=new Member();
            member.setMemberId(memberDeposit.getMemberId());
            member.setMemberMoney(memberDeposit.getDepositMoney());
            memberService.increaseMemberMoney(member);
            return true;
        }
        return false;
    }
}

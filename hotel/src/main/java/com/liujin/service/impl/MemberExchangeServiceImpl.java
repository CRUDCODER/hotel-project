package com.liujin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.Member;
import com.liujin.entity.MemberExchange;
import com.liujin.mapper.MemberExchangeMapper;
import com.liujin.service.IMemberExchangeService;
import com.liujin.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author liujin
 * @date created in 2020/2/1 11:00
 */
@Service
public class MemberExchangeServiceImpl
        extends ServiceImpl<MemberExchangeMapper, MemberExchange>
        implements IMemberExchangeService
{
    @Autowired
    private IMemberService memberService;
    @Override
    public List<MemberExchange> fetchMemberExchange(MemberExchange memberExchange) {
        return getBaseMapper().fetchMemberExchange(memberExchange);
    }

    /**
     * 会员兑换优惠卷
     * 兑换日期为系统时间
     * 是否使用为未使用 1：未使用  0：已使用
     * 要求增加一条兑换记录
     * 减少会员对应的积分
     * @param memberExchange
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean exchangeCoupons(MemberExchange memberExchange) {
        memberExchange.setExchangeDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        memberExchange.setExchangeFlag(1);
        if (memberExchange.insert()){
            Member member=new Member();
            member.setMemberId(memberExchange.getMemberId());
            member.setMemberScore(memberExchange.getConsumeScore());
            memberService.reduceMemberScore(member);
            return true;
        }
        return false;
    }

    @Override
    public MemberExchange queryMemberEchangeByCouponsName(String couponsName) {
        return getBaseMapper().queryMemberEchangeByCouponsName(couponsName);
    }
}

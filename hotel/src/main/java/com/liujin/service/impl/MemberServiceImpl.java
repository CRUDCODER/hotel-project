package com.liujin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.Member;
import com.liujin.mapper.MemberMapper;
import com.liujin.service.IMemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/30 12:01
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {
    @Override
    public List<Member> fetchMember(Member member) {
        return getBaseMapper().fetchMember(member);
    }

    @Override
    public boolean increaseMemberMoney(Member member) {
        return getBaseMapper().increaseMemberMoney(member);
    }

    @Override
    public boolean reduceMemberMoney(Member member) {
        return getBaseMapper().reduceMemberMoney(member);
    }

    /**
     * 注册会员账号 首先查出此时会员卡号最大值 然后+1就为当前注册会员的卡号 由于刚注册所以余额和积分都为0
     * @param member
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer registeredMember(Member member) {
        Integer memberNumber=null;
        Integer maxMemberNumber = getBaseMapper().queryMaxMemberNumber();
        member.setMemberNumber(maxMemberNumber+1);
        member.setMemberMoney(0);
        member.setMemberScore(0);
        if (member.insert()){
            return member.getMemberNumber();
        }
        return memberNumber;
    }

    @Override
    public Member fetchMemberByNumberAndPassword(Member member) {
        return getBaseMapper().fetchMemberByNumberAndPassword(member);
    }

    @Override
    public boolean reduceMemberScore(Member member) {
        return getBaseMapper().reduceMemberScore(member);
    }

}

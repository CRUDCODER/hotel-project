package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.Member;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/30 12:01
 */
public interface MemberMapper extends BaseMapper<Member> {

    List<Member> fetchMember(Member member);


    Integer queryMaxMemberNumber();

    /**
     * 充值成功之后 增加卡上余额
     * @param member
     * @return
     */
    boolean increaseMemberMoney(Member member);
    /**
     * 消费成功之后 减少卡余额
     * @param member
     * @return
     */
    boolean reduceMemberMoney(Member member);

    Member fetchMemberByNumberAndPassword(Member member);

    /**
     * 兑换成功 减少积分
     * @param member
     * @return
     */
    boolean reduceMemberScore(Member member);

}

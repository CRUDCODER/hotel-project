package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.Member;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/30 12:01
 */
public interface IMemberService extends IService<Member> {
    List<Member> fetchMember(Member member);

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
    Integer registeredMember(Member member);

    Member fetchMemberByNumberAndPassword(Member member);


    boolean reduceMemberScore(Member member);

}

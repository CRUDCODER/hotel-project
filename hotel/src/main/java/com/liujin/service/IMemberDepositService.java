package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.MemberDeposit;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/30 12:37
 */
public interface IMemberDepositService extends IService<MemberDeposit> {
    List<MemberDeposit> fetchMemberDeposit(MemberDeposit memberDeposit);

    boolean increaseMemberDeposit(MemberDeposit memberDeposit);
}

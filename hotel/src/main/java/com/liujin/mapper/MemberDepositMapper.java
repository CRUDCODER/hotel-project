package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.MemberDeposit;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/30 12:37
 */
public interface MemberDepositMapper extends BaseMapper<MemberDeposit> {

    List<MemberDeposit> fetchMemberDeposit(MemberDeposit memberDeposit);
}

package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.MemberExchange;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/2/1 11:00
 */
public interface MemberExchangeMapper extends BaseMapper<MemberExchange> {

    List<MemberExchange> fetchMemberExchange(MemberExchange memberExchange);


    MemberExchange queryMemberEchangeByCouponsName(@Param("couponsName") String couponsName);
}

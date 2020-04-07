package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.MemberExchange;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/2/1 11:00
 */
public interface IMemberExchangeService extends IService<MemberExchange> {
    List<MemberExchange> fetchMemberExchange(MemberExchange memberExchange);


    boolean exchangeCoupons(MemberExchange memberExchange);

    MemberExchange queryMemberEchangeByCouponsName(@Param("couponsName") String couponsName);
}

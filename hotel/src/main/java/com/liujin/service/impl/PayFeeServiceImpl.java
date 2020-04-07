package com.liujin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.PayFee;
import com.liujin.mapper.PayFeeMapper;
import com.liujin.service.IPayFeeService;
import org.springframework.stereotype.Service;

/**
 * @author liujin
 * @date created in 2020/2/2 11:28
 */
@Service
public class PayFeeServiceImpl
    extends ServiceImpl<PayFeeMapper, PayFee>
    implements IPayFeeService {
}

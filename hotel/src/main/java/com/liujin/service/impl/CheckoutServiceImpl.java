package com.liujin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.Checkout;
import com.liujin.mapper.CheckoutMapper;
import com.liujin.service.ICheckoutService;
import org.springframework.stereotype.Service;

/**
 * @author liujin
 * @date created in 2020/2/2 11:32
 */
@Service
public class CheckoutServiceImpl
    extends ServiceImpl<CheckoutMapper, Checkout>
    implements ICheckoutService {
}

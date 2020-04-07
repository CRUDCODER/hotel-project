package com.liujin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.Coupons;
import com.liujin.mapper.CouponsMapper;
import com.liujin.service.ICouponsService;
import org.springframework.stereotype.Service;

/**
 * @author liujin
 * @date created in 2020/2/1 9:44
 */
@Service
public class CouponsServiceImpl extends ServiceImpl<CouponsMapper, Coupons> implements ICouponsService {
}

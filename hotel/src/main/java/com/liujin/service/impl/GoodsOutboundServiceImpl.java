package com.liujin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.GoodsOutbound;
import com.liujin.mapper.GoodsOutboundMapper;
import com.liujin.service.IGoodsOutboundService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/30 10:22
 */
@Service
public class GoodsOutboundServiceImpl extends ServiceImpl<GoodsOutboundMapper, GoodsOutbound> implements IGoodsOutboundService {
    @Override
    public List<GoodsOutbound> fetchGoodsOutbound(GoodsOutbound goodsOutbound) {
        return getBaseMapper().fetchGoodsOutbound(goodsOutbound);
    }

    @Override
    public boolean increaseGoodsOutbound(GoodsOutbound goodsOutbound) {
        return goodsOutbound.insert();
    }
}

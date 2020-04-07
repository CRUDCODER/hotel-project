package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.GoodsOutbound;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/30 10:22
 */
public interface IGoodsOutboundService extends IService<GoodsOutbound> {
    List<GoodsOutbound> fetchGoodsOutbound(GoodsOutbound goodsOutbound);

    boolean increaseGoodsOutbound(GoodsOutbound goodsOutbound);
}
